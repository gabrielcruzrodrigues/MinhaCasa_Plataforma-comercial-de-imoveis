package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class ErrorForDeleteFileException extends RuntimeException {
    public ErrorForDeleteFileException(String error) {
        super(error);
    }
}
