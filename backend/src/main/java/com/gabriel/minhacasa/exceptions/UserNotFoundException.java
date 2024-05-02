package com.gabriel.minhacasa.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found exception.");
    }
}
