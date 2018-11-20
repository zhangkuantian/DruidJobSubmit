package com.yuewen.data.druid.task.pojo;

public class TimestampSpec {
	private String column;
	private String format = "auto";
	public TimestampSpec() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TimestampSpec(String column, String format) {
		super();
		this.column = column;
		this.format = format;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	@Override
	public String toString() {
		return "TimestampSpec [column=" + column + ", format=" + format + "]";
	}
}
