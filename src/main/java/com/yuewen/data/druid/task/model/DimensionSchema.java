package com.yuewen.data.druid.task.model;

import io.druid.java.util.common.StringUtils;

public class DimensionSchema {
	public static final String STRING_TYPE_NAME = "string";
	public static final String LONG_TYPE_NAME = "long";
	public static final String FLOAT_TYPE_NAME = "float";
	public static final String SPATIAL_TYPE_NAME = "spatial";
	public static final String DOUBLE_TYPE_NAME = "double";
	
	public enum ValueType {
		
	    FLOAT,
	    LONG,
	    STRING,
	    DOUBLE,
	    @SuppressWarnings("unused") // used in io.druid.segment.column.ValueType
	    COMPLEX;

	    @Override
	    public String toString(){
	      return StringUtils.toUpperCase(this.name());
	    }

	    public static ValueType fromString(String name) {
	      return valueOf(StringUtils.toUpperCase(name));
	    }
	}
	public enum MultiValueHandling{
	    SORTED_ARRAY,
	    SORTED_SET,
	    ARRAY {
	      @Override
	      public boolean needSorting(){
	        return false;
	      }
	    };

	    public boolean needSorting(){
	      return true;
	    }

	    @Override
	    public String toString(){
	      return StringUtils.toUpperCase(name());
	    }

	    public static MultiValueHandling fromString(String name){
	      return name == null ? ofDefault() : valueOf(StringUtils.toUpperCase(name));
	    }

	    // this can be system configuration
	    public static MultiValueHandling ofDefault(){
	      return SORTED_ARRAY;
	    }
	}
	
	
	
}
