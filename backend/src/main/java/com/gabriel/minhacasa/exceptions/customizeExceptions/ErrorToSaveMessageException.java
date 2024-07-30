package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class ErrorToSaveMessageException extends RuntimeException {
    public ErrorToSaveMessageException(String error) {
        super(error);
    }
}
