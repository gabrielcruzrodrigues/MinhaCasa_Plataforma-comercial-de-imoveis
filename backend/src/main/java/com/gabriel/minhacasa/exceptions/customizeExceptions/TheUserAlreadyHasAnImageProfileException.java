package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class TheUserAlreadyHasAnImageProfileException extends RuntimeException {
    public TheUserAlreadyHasAnImageProfileException() {
        super("the user already has an image profile.");
    }
}
