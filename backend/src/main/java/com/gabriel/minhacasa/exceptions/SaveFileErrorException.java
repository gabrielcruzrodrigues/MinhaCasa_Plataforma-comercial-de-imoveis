package com.gabriel.minhacasa.exceptions;

public class SaveFileErrorException extends RuntimeException {
    public SaveFileErrorException(String error) {
        super(error);
    }
}
