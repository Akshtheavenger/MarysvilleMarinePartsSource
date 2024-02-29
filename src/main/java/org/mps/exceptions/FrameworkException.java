package org.mps.exceptions;

public class FrameworkException extends RuntimeException {
	
	public FrameworkException(String message) {
		super(message);
	}
	
	//Option to customize the Stacktrace
	
	public FrameworkException(String message, Throwable cause) {
		super(message,cause);
	}
	
	

}
