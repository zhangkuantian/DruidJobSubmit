package com.yuewen.data.druid.task.pojo;

import java.util.Map;

import com.yuewen.data.druid.task.pojo.itf.IOConfig;

public class HadoopIOConfig1 implements IOConfig {
	private final Map<String, Object> pathSpec;
	private final MetadataStorageUpdaterJobSpec metadataUpdateSpec;
	private final String segmentOutputPath;
	public HadoopIOConfig1(Map<String, Object> pathSpec,
			MetadataStorageUpdaterJobSpec metadataUpdateSpec,
			String segmentOutputPath) {
		super();
		this.pathSpec = pathSpec;
		this.metadataUpdateSpec = metadataUpdateSpec;
		this.segmentOutputPath = segmentOutputPath;
	}
	public Map<String, Object> getPathSpec() {
		return pathSpec;
	}
	public MetadataStorageUpdaterJobSpec getMetadataUpdateSpec() {
		return metadataUpdateSpec;
	}
	public String getSegmentOutputPath() {
		return segmentOutputPath;
	}
	@Override
	public String toString() {
		return "HadoopIOConfig [pathSpec=" + pathSpec + ", metadataUpdateSpec="
				+ metadataUpdateSpec + ", segmentOutputPath="
				+ segmentOutputPath + "]";
	}
	public HadoopIOConfig1 withSegmentOutputPath(String path){
		return new HadoopIOConfig1(pathSpec, metadataUpdateSpec, path);
	}
	
}
