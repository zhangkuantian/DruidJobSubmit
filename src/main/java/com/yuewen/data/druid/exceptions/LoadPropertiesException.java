package com.yuewen.data.druid.exceptions;

public class LoadPropertiesException extends RuntimeException{
	private static final long serialVersionUID = 1L;

    public LoadPropertiesException(String message) {
        super(message);
    }

    public LoadPropertiesException(String message, Throwable e) {
        super(message, e);
    }

    public LoadPropertiesException(Throwable e) {
        super(e);
    }
}
