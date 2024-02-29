package org.mps.exceptions;

public class JsonFileUsageException extends FrameworkException{
    public JsonFileUsageException(String message) {
        super(message);
    }

    public JsonFileUsageException(String message, Throwable cause) {
        super(message, cause);
    }
}
