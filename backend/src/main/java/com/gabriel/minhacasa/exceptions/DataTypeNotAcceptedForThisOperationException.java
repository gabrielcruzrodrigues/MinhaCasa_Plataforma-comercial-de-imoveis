package com.gabriel.minhacasa.exceptions;

public class DataTypeNotAcceptedForThisOperationException extends RuntimeException {
    public DataTypeNotAcceptedForThisOperationException() {
        super("Data type not accepted for this operation");
    }
}
