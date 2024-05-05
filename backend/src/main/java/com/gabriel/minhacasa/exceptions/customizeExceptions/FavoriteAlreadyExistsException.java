package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class FavoriteAlreadyExistsException extends RuntimeException {
    public FavoriteAlreadyExistsException() {
        super("Favorite already exists.");
    }
}
