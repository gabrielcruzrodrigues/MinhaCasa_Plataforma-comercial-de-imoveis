package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found exception.");
    }
}
