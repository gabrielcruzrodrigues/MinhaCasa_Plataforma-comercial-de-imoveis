package com.gabriel.minhacasa.security.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@Setter
@AllArgsConstructor
@Configuration
public class RsaKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RsaKeyProperties() throws IllegalAccessException {
        KeyPair keyPair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
