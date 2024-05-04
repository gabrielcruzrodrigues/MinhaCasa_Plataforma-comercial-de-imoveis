package com.gabriel.minhacasa.exceptions;

public class ErrorForDeleteImageException extends RuntimeException {
    public ErrorForDeleteImageException(String error) {
        super(error);
    }
}
