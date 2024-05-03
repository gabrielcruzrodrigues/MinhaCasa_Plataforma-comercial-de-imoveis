package com.gabriel.minhacasa.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class GenerateNewName {

    public String generateFileName(MultipartFile file, String name) {
        String randomId = generateRandomId();
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        return name + "_" + randomId + fileExtension;
    }

    public String generateRandomId() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    public String addCharactersToFileName(String fileName) {
        return fileName + UUID.randomUUID().toString().substring(0, 5);
    }
}
