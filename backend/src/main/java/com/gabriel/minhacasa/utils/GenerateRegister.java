package com.gabriel.minhacasa.utils;

import java.util.UUID;

public class GenerateRegister {

    public String newRegister() {
        return UUID.randomUUID().toString().substring(0, 15);
    }
}
