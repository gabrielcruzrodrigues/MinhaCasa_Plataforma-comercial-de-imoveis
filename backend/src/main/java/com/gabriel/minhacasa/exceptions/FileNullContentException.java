package com.gabriel.minhacasa.exceptions;

public class FileNullContentException extends RuntimeException {
    public FileNullContentException() {
        super("File null content.");
    }
}
