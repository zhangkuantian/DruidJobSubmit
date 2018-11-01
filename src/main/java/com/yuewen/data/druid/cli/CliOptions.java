package com.yuewen.data.druid.cli;

public class CliOptions {
	private final boolean isPrintHelp;
	private final String paths;
	private final String template;
	private final String intervals;
	private final String host;
	public CliOptions(boolean isPrintHelp, String paths, String template,
			String intervals, String host) {
		super();
		this.isPrintHelp = isPrintHelp;
		this.paths = paths;
		this.template = template;
		this.intervals = intervals;
		this.host = host;
	}
	public boolean isPrintHelp() {
		return isPrintHelp;
	}
	public String getPaths() {
		return paths;
	}
	public String getTemplate() {
		return template;
	}
	public String getIntervals() {
		return intervals;
	}
	public String getHost() {
		return host;
	}
	@Override
	public String toString() {
		return "CliOptions [isPrintHelp=" + isPrintHelp + ", paths=" + paths
				+ ", ds=" + template + ", intervals=" + intervals + ", host=" + host
				+ "]";
	}
}
