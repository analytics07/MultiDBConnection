package com.study.exception;

public class DataExceptionHandler extends RuntimeException {
	
	public DataExceptionHandler(String message, Throwable cause  ) {
		super(message,cause);
	}
}
