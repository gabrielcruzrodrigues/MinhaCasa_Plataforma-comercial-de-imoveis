package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class SaveFileErrorException extends RuntimeException {
    public SaveFileErrorException(String error) {
        super(error);
    }
}
