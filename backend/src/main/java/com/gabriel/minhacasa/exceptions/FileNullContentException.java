package com.gabriel.minhacasa.exceptions;

public class FileNullContentException extends RuntimeException {
    public FileNullContentException(String localError) {
        super("File is null in" + localError);
    }
}
