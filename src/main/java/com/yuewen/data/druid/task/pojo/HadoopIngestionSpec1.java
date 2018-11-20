package com.yuewen.data.druid.task.pojo;

import io.druid.common.utils.UUIDUtils;
import io.druid.indexer.HadoopIOConfig;
import io.druid.indexer.HadoopTuningConfig;
import io.druid.indexer.hadoop.DatasourceIngestionSpec;
import io.druid.indexer.hadoop.WindowedDataSegment;
import io.druid.indexer.path.UsedSegmentLister;
import io.druid.segment.indexing.DataSchema;
import io.druid.segment.indexing.IngestionSpec;
import io.druid.timeline.DataSegment;
import io.druid.timeline.TimelineObjectHolder;
import io.druid.timeline.VersionedIntervalTimeline;
import io.druid.timeline.partition.PartitionChunk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.Interval;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class HadoopIngestionSpec1 extends IngestionSpec<HadoopIOConfig, HadoopTuningConfig>{
	  private DataSchema dataSchema;
	  private HadoopIOConfig ioConfig;
	  private HadoopTuningConfig tuningConfig;

	  //this is used in the temporary paths on the hdfs unique to an hadoop indexing task
	  private final String uniqueId;

	  public HadoopIngestionSpec1(
		  DataSchema dataSchema,
		  HadoopIOConfig ioConfig,
		  HadoopTuningConfig tuningConfig,
		  String uniqueId
	  )
	  {
	    super(dataSchema, ioConfig, tuningConfig);

	    this.dataSchema = dataSchema;
	    this.ioConfig = ioConfig;
	    this.tuningConfig = tuningConfig == null ? HadoopTuningConfig.makeDefaultTuningConfig() : tuningConfig;
	    this.uniqueId = uniqueId == null ? UUIDUtils.generateUuid() : uniqueId;
	  }

	  public HadoopIOConfig getIoConfig() {
		return ioConfig;
	}

	public void setIoConfig(HadoopIOConfig ioConfig) {
		this.ioConfig = ioConfig;
	}

	public void setDataSchema(DataSchema dataSchema) {
		this.dataSchema = dataSchema;
	}

	public void setTuningConfig(HadoopTuningConfig tuningConfig) {
		this.tuningConfig = tuningConfig;
	}

	//for unit tests
	  public HadoopIngestionSpec1(
	      DataSchema dataSchema,
	      HadoopIOConfig ioConfig,
	      HadoopTuningConfig tuningConfig
	  )
	  {
	    this(dataSchema, ioConfig, tuningConfig, null);
	  }
	  @Override
	  public DataSchema getDataSchema()
	  {
	    return dataSchema;
	  }
	  
	  @Override
	  public HadoopIOConfig getIOConfig()
	  {
	    return ioConfig;
	  }
	  @Override
	  public HadoopTuningConfig getTuningConfig()
	  {
	    return tuningConfig;
	  }
	  public String getUniqueId()
	  {
	    return uniqueId;
	  }

	  public HadoopIngestionSpec1 withDataSchema(DataSchema schema)
	  {
	    return new HadoopIngestionSpec1(
	        schema,
	        ioConfig,
	        tuningConfig,
	        uniqueId
	    );
	  }

	  public HadoopIngestionSpec1 withIOConfig(HadoopIOConfig config)
	  {
	    return new HadoopIngestionSpec1(
	        dataSchema,
	        config,
	        tuningConfig,
	        uniqueId
	    );
	  }

	  public HadoopIngestionSpec1 withTuningConfig(HadoopTuningConfig config)
	  {
	    return new HadoopIngestionSpec1(
	        dataSchema,
	        ioConfig,
	        config,
	        uniqueId
	    );
	  }

	  public static HadoopIngestionSpec1 updateSegmentListIfDatasourcePathSpecIsUsed(
	      HadoopIngestionSpec1 spec,
	      ObjectMapper jsonMapper,
	      UsedSegmentLister segmentLister
	  )
	      throws IOException
	  {
	    String dataSource = "dataSource";
	    String type = "type";
	    String multi = "multi";
	    String children = "children";
	    String segments = "segments";
	    String ingestionSpec = "ingestionSpec";

	    Map<String, Object> pathSpec = spec.getIOConfig().getPathSpec();
	    List<Map<String, Object>> datasourcePathSpecs = new ArrayList<>();
	    if (pathSpec.get(type).equals(dataSource)) {
	      datasourcePathSpecs.add(pathSpec);
	    } else if (pathSpec.get(type).equals(multi)) {
	      List<Map<String, Object>> childPathSpecs = (List<Map<String, Object>>) pathSpec.get(children);
	      for (Map<String, Object> childPathSpec : childPathSpecs) {
	        if (childPathSpec.get(type).equals(dataSource)) {
	          datasourcePathSpecs.add(childPathSpec);
	        }
	      }
	    }

	    for (Map<String, Object> datasourcePathSpec : datasourcePathSpecs) {
	      Map<String, Object> ingestionSpecMap = (Map<String, Object>) datasourcePathSpec.get(ingestionSpec);
	      DatasourceIngestionSpec ingestionSpecObj = jsonMapper.convertValue(
	          ingestionSpecMap,
	          DatasourceIngestionSpec.class
	      );

	      List<DataSegment> segmentsList = segmentLister.getUsedSegmentsForIntervals(
	          ingestionSpecObj.getDataSource(),
	          ingestionSpecObj.getIntervals()
	      );

	      if (ingestionSpecObj.getSegments() != null) {
	        //ensure that user supplied segment list matches with the segmentsList obtained from db
	        //this safety check lets users do test-n-set kind of batch delta ingestion where the delta
	        //ingestion task would only run if current state of the system is same as when they submitted
	        //the task.
	        List<DataSegment> userSuppliedSegmentsList = ingestionSpecObj.getSegments();

	        if (segmentsList.size() == userSuppliedSegmentsList.size()) {
	          Set<DataSegment> segmentsSet = new HashSet<>(segmentsList);

	          for (DataSegment userSegment : userSuppliedSegmentsList) {
	            if (!segmentsSet.contains(userSegment)) {
	              throw new IOException("user supplied segments list did not match with segments list obtained from db");
	            }
	          }
	        } else {
	          throw new IOException("user supplied segments list did not match with segments list obtained from db");
	        }
	      }

	      VersionedIntervalTimeline<String, DataSegment> timeline = new VersionedIntervalTimeline<>(Ordering.natural());
	      for (DataSegment segment : segmentsList) {
	        timeline.add(segment.getInterval(), segment.getVersion(), segment.getShardSpec().createChunk(segment));
	      }

	      final List<WindowedDataSegment> windowedSegments = Lists.newArrayList();
	      for (Interval interval : ingestionSpecObj.getIntervals()) {
	        final List<TimelineObjectHolder<String, DataSegment>> timeLineSegments = timeline.lookup(interval);

	        for (TimelineObjectHolder<String, DataSegment> holder : timeLineSegments) {
	          for (PartitionChunk<DataSegment> chunk : holder.getObject()) {
	            windowedSegments.add(new WindowedDataSegment(chunk.getObject(), holder.getInterval()));
	          }
	        }
	        datasourcePathSpec.put(segments, windowedSegments);
	      }
	    }
	    return spec;
	  }
}
