package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class ImmobileNotFoundException extends RuntimeException {
    public ImmobileNotFoundException() {
        super("Immobile not found exception");
    }
}
