package com.gabriel.minhacasa.exceptions;

public class FavoriteAlreadyExistsException extends RuntimeException {
    public FavoriteAlreadyExistsException() {
        super("Favorite already exists.");
    }
}
