package com.gabriel.minhacasa.exceptions;

public class AuthenticationErrorException extends RuntimeException {
    public AuthenticationErrorException(String error) {
        super(error);
    }
}
