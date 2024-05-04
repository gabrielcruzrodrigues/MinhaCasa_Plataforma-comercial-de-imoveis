package com.gabriel.minhacasa.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateRegister {

    public String newRegister() {
        return UUID.randomUUID().toString().substring(0, 15);
    }
}
