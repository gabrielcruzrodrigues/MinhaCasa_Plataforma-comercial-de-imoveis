package com.gabriel.minhacasa.exceptions;

public class ImmobileNotFoundException extends RuntimeException {
    public ImmobileNotFoundException() {
        super("Immobile not found exception");
    }
}
