package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class UserNullContentException extends RuntimeException {
    public UserNullContentException() {
        super("User null content.");
    }
}
