package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class IntegrityErrorException extends RuntimeException {
    public IntegrityErrorException(String error) {
        super(error);
    }
}
