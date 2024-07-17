package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class FileNullContentException extends RuntimeException {
    public FileNullContentException(String localError) {
        super("File is null in " + localError);
    }
}
