package com.yuewen.data.druid.task.pojo;

import io.druid.indexer.HadoopyShardSpec;
import io.druid.indexer.partitions.HashedPartitionsSpec;
import io.druid.indexer.partitions.PartitionsSpec;
import io.druid.java.util.common.DateTimes;
import io.druid.segment.IndexSpec;

import java.util.List;
import java.util.Map;

import org.apache.curator.shaded.com.google.common.base.Preconditions;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yuewen.data.druid.task.pojo.itf.TuningConfig;

public class HadoopTuningConfig1 implements TuningConfig{
  private static final PartitionsSpec DEFAULT_PARTITIONS_SPEC = HashedPartitionsSpec.makeDefaultHashedPartitionsSpec();
  private static final Map<Long, List<HadoopyShardSpec>> DEFAULT_SHARD_SPECS = ImmutableMap.of();
  private static final IndexSpec DEFAULT_INDEX_SPEC = new IndexSpec();
  private static final int DEFAULT_ROW_FLUSH_BOUNDARY = 75000;
  private static final boolean DEFAULT_USE_COMBINER = false;
  private static final int DEFAULT_NUM_BACKGROUND_PERSIST_THREADS = 0;

  public static HadoopTuningConfig1 makeDefaultTuningConfig(){
    return new HadoopTuningConfig1(
        null,
        DateTimes.nowUtc().toString(),
        DEFAULT_PARTITIONS_SPEC,
        DEFAULT_SHARD_SPECS,
        DEFAULT_INDEX_SPEC,
        DEFAULT_ROW_FLUSH_BOUNDARY,
        false,
        true,
        false,
        false,
        null,
        false,
        false,
        null,
        true,
        DEFAULT_NUM_BACKGROUND_PERSIST_THREADS,
        false,
        false,
        null
    );
  }

  private final String workingPath;
  private final String version;
  private final PartitionsSpec partitionsSpec;
  private final Map<Long, List<HadoopyShardSpec>> shardSpecs;
  private final IndexSpec indexSpec;
  private final int rowFlushBoundary;
  private final boolean leaveIntermediate;
  private final Boolean cleanupOnFailure;
  private final boolean overwriteFiles;
  private final boolean ignoreInvalidRows;
  private final Map<String, String> jobProperties;
  private final boolean combineText;
  private final boolean useCombiner;
  private final int numBackgroundPersistThreads;
  private final boolean forceExtendableShardSpecs;
  private final boolean useExplicitVersion;
  private final List<String> allowedHadoopPrefix;

  public HadoopTuningConfig1(
      final String workingPath,
      final String version,
      final PartitionsSpec partitionsSpec,
      final Map<Long, List<HadoopyShardSpec>> shardSpecs,
      final IndexSpec indexSpec,
      final Integer maxRowsInMemory,
      final boolean leaveIntermediate,
      final Boolean cleanupOnFailure,
      final boolean overwriteFiles,
      final boolean ignoreInvalidRows,
      final Map<String, String> jobProperties,
      final boolean combineText,
      final Boolean useCombiner,
      // See https://github.com/druid-io/druid/pull/1922
      final Integer maxRowsInMemoryCOMPAT,
      // This parameter is left for compatibility when reading existing configs, to be removed in Druid 0.12.
      final Boolean buildV9Directly,
      final Integer numBackgroundPersistThreads,
      final boolean forceExtendableShardSpecs,
      final boolean useExplicitVersion,
      final List<String> allowedHadoopPrefix
  ){
    this.workingPath = workingPath;
    this.version = version == null ? DateTimes.nowUtc().toString() : version;
    this.partitionsSpec = partitionsSpec == null ? DEFAULT_PARTITIONS_SPEC : partitionsSpec;
    this.shardSpecs = shardSpecs == null ? DEFAULT_SHARD_SPECS : shardSpecs;
    this.indexSpec = indexSpec == null ? DEFAULT_INDEX_SPEC : indexSpec;
    this.rowFlushBoundary = maxRowsInMemory == null ? maxRowsInMemoryCOMPAT == null
                                                      ? DEFAULT_ROW_FLUSH_BOUNDARY
                                                      : maxRowsInMemoryCOMPAT : maxRowsInMemory;
    this.leaveIntermediate = leaveIntermediate;
    this.cleanupOnFailure = cleanupOnFailure == null ? true : cleanupOnFailure;
    this.overwriteFiles = overwriteFiles;
    this.ignoreInvalidRows = ignoreInvalidRows;
    this.jobProperties = (jobProperties == null
                          ? ImmutableMap.<String, String>of()
                          : ImmutableMap.copyOf(jobProperties));
    this.combineText = combineText;
    this.useCombiner = useCombiner == null ? DEFAULT_USE_COMBINER : useCombiner.booleanValue();
    this.numBackgroundPersistThreads = numBackgroundPersistThreads == null
                                       ? DEFAULT_NUM_BACKGROUND_PERSIST_THREADS
                                       : numBackgroundPersistThreads;
    this.forceExtendableShardSpecs = forceExtendableShardSpecs;
    Preconditions.checkArgument(this.numBackgroundPersistThreads >= 0, "Not support persistBackgroundCount < 0");
    this.useExplicitVersion = useExplicitVersion;
    this.allowedHadoopPrefix = allowedHadoopPrefix == null ? ImmutableList.of() : allowedHadoopPrefix;
  }

  public String getWorkingPath(){
    return workingPath;
  }

  public String getVersion(){
    return version;
  }

  public PartitionsSpec getPartitionsSpec(){
    return partitionsSpec;
  }

  public Map<Long, List<HadoopyShardSpec>> getShardSpecs(){
    return shardSpecs;
  }

  public IndexSpec getIndexSpec(){
    return indexSpec;
  }

  public int getRowFlushBoundary(){
    return rowFlushBoundary;
  }

  public boolean isLeaveIntermediate(){
    return leaveIntermediate;
  }

  public Boolean isCleanupOnFailure(){
    return cleanupOnFailure;
  }

  public boolean isOverwriteFiles(){
    return overwriteFiles;
  }

  public boolean isIgnoreInvalidRows(){
    return ignoreInvalidRows;
  }

  public Map<String, String> getJobProperties(){
    return jobProperties;
  }

  public boolean isCombineText(){
    return combineText;
  }

  public boolean getUseCombiner(){
    return useCombiner;
  }

  /**
   * Always returns true, doesn't affect the version being built.
   */
  @Deprecated
  public Boolean getBuildV9Directly() {
    return true;
  }

  public int getNumBackgroundPersistThreads(){
    return numBackgroundPersistThreads;
  }

  public boolean isForceExtendableShardSpecs(){
    return forceExtendableShardSpecs;
  }

  public boolean isUseExplicitVersion(){
    return useExplicitVersion;
  }

  public List<String> getUserAllowedHadoopPrefix(){
    // Just the user-specified list. More are added in HadoopDruidIndexerConfig.
    return allowedHadoopPrefix;
  }

  public HadoopTuningConfig1 withWorkingPath(String path){
    return new HadoopTuningConfig1(
        path,
        version,
        partitionsSpec,
        shardSpecs,
        indexSpec,
        rowFlushBoundary,
        leaveIntermediate,
        cleanupOnFailure,
        overwriteFiles,
        ignoreInvalidRows,
        jobProperties,
        combineText,
        useCombiner,
        null,
        true,
        numBackgroundPersistThreads,
        forceExtendableShardSpecs,
        useExplicitVersion,
        allowedHadoopPrefix
    );
  }

  public HadoopTuningConfig1 withVersion(String ver){
    return new HadoopTuningConfig1(
        workingPath,
        ver,
        partitionsSpec,
        shardSpecs,
        indexSpec,
        rowFlushBoundary,
        leaveIntermediate,
        cleanupOnFailure,
        overwriteFiles,
        ignoreInvalidRows,
        jobProperties,
        combineText,
        useCombiner,
        null,
        true,
        numBackgroundPersistThreads,
        forceExtendableShardSpecs,
        useExplicitVersion,
        allowedHadoopPrefix
    );
  }

  public HadoopTuningConfig1 withShardSpecs(Map<Long, List<HadoopyShardSpec>> specs){
    return new HadoopTuningConfig1(
        workingPath,
        version,
        partitionsSpec,
        specs,
        indexSpec,
        rowFlushBoundary,
        leaveIntermediate,
        cleanupOnFailure,
        overwriteFiles,
        ignoreInvalidRows,
        jobProperties,
        combineText,
        useCombiner,
        null,
        true,
        numBackgroundPersistThreads,
        forceExtendableShardSpecs,
        useExplicitVersion,
        allowedHadoopPrefix
    );
  }
}
