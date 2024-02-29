package org.mps.exceptions;

public class InvalidPathForSparkXMLConfigFileException extends InvalidPathForFilesException {

	public InvalidPathForSparkXMLConfigFileException(String message) {
		super(message);
	}
	
	public InvalidPathForSparkXMLConfigFileException(String message, Throwable cause) {
		super(message,cause);
	}

}
