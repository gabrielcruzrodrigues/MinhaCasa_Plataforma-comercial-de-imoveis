package com.gabriel.minhacasa.exceptions;

public class IntegrityErrorException extends RuntimeException {
    public IntegrityErrorException(String error) {
        super(error);
    }
}
