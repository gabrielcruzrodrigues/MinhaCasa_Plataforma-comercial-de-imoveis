package com.gabriel.minhacasa.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class GenerateNewName {

    public String generateFileName(MultipartFile file) {
        String randomId = generateRandomId();
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        return randomId + fileExtension;
    }

    public String generateRandomId() {
        return UUID.randomUUID().toString().substring(0, 15);
    }

    public String addCharactersToFileName(String fileName) {
        return fileName + UUID.randomUUID().toString().substring(0, 5);
    }
}
