package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class AuthenticationErrorException extends RuntimeException {
    public AuthenticationErrorException(String error) {
        super(error);
    }
}
