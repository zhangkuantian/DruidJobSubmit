package com.yuewen.data.druid.task.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuewen.data.druid.task.pojo.itf.IOConfig;

public abstract class IngestionSpec1<IOConfigType extends IOConfig, TuningConfigType extends TuningConfig> {
	private final DataSchema1 dataSchema;
	private final IOConfigType ioConfig;
	private final TuningConfigType tuningConfig;
	public IngestionSpec1(
	    @JsonProperty("dataSchema") DataSchema1 dataSchema,
	@JsonProperty("ioConfig") IOConfigType ioConfig,
	@JsonProperty("tuningConfig") TuningConfigType tuningConfig
	){
	  this.dataSchema = dataSchema;
	  this.ioConfig = ioConfig;
	  this.tuningConfig = tuningConfig;
	}

	@JsonProperty("dataSchema")
	public DataSchema1 getDataSchema(){
	  return dataSchema;
	}

	@JsonProperty("ioConfig")
	public IOConfigType getIOConfig(){
	  return ioConfig;
	}

	@JsonProperty("tuningConfig")
	public TuningConfigType getTuningConfig(){
	  return tuningConfig;
	}
	
}
