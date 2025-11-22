package com.corejava.corejava.exeptionHandling.trywithResources;

public class ExceptionvsRuntimeException {

    public void parseMessage(String msg) throws MessageParsingException {
        // parsing logic
    }
}

class MessageParsingException extends Exception {
    public MessageParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}