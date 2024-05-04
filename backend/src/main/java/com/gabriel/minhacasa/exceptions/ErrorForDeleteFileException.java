package com.gabriel.minhacasa.exceptions;

public class ErrorForDeleteFileException extends RuntimeException {
    public ErrorForDeleteFileException(String error) {
        super(error);
    }
}
