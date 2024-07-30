package com.gabriel.minhacasa.exceptions.customizeExceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException() {
        super("Message not found exception");
    }
}
