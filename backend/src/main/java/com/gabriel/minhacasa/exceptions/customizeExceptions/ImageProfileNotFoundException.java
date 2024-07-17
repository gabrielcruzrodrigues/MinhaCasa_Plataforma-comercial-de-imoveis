package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class ImageProfileNotFoundException extends RuntimeException {
    public ImageProfileNotFoundException() {
        super("image profile not found.");
    }
}
