package com.yuewen.data.druid.exceptions;

public class TaskException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 12232321357631L;
	public TaskException(Exception e) {
        super(e);
    }
    public TaskException(String message) {
        super(message);
    }
}
