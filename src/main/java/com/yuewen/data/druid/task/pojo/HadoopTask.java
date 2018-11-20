package com.yuewen.data.druid.task.pojo;

import io.druid.indexer.HadoopIngestionSpec;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
@JsonIgnoreType
public class HadoopTask {
	private String type;
	private HadoopIngestionSpec spec;
	@JsonIgnore
	private List<String> hadoopDependencyCoordinates;
	@JsonIgnore
	private String classpathPrefix;
	
	public HadoopTask() {
		super();
	}

	public HadoopTask(String type, HadoopIngestionSpec spec) {
		super();
		this.type = type;
		this.spec = spec;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HadoopIngestionSpec getSpec() {
		return spec;
	}

	public void setSpec(HadoopIngestionSpec spec) {
		this.spec = spec;
	}
	
	public List<String> getHadoopDependencyCoordinates() {
		return hadoopDependencyCoordinates;
	}
	@JsonIgnore
	public void setHadoopDependencyCoordinates(
			List<String> hadoopDependencyCoordinates) {
		this.hadoopDependencyCoordinates = hadoopDependencyCoordinates;
	}

	public String getClasspathPrefix() {
		return classpathPrefix;
	}
	@JsonIgnore
	public void setClasspathPrefix(String classpathPrefix) {
		this.classpathPrefix = classpathPrefix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classpathPrefix == null) ? 0 : classpathPrefix.hashCode());
		result = prime
				* result
				+ ((hadoopDependencyCoordinates == null) ? 0
						: hadoopDependencyCoordinates.hashCode());
		result = prime * result + ((spec == null) ? 0 : spec.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HadoopTask other = (HadoopTask) obj;
		if (classpathPrefix == null) {
			if (other.classpathPrefix != null)
				return false;
		} else if (!classpathPrefix.equals(other.classpathPrefix))
			return false;
		if (hadoopDependencyCoordinates == null) {
			if (other.hadoopDependencyCoordinates != null)
				return false;
		} else if (!hadoopDependencyCoordinates
				.equals(other.hadoopDependencyCoordinates))
			return false;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HadoopTask [type=" + type + ", spec=" + spec
				+ ", hadoopDependencyCoordinates="
				+ hadoopDependencyCoordinates + ", classpathPrefix="
				+ classpathPrefix + "]";
	}
}