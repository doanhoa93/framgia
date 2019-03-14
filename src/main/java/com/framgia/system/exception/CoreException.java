package com.framgia.system.exception;

public class CoreException extends RuntimeException{

    public CoreException(){}

    public CoreException(String message){
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }

}