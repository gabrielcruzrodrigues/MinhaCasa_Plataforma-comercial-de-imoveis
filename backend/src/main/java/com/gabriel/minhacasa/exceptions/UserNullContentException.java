package com.gabriel.minhacasa.exceptions;

public class UserNullContentException extends RuntimeException {
    public UserNullContentException() {
        super("User null content.");
    }
}
