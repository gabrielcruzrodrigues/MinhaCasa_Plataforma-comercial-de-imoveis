package com.gabriel.minhacasa.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServiceInterface<T> {
    void saveFile(MultipartFile file, T entity);

    void saveFileReferenceInDatabase(String fileName, T entity);

    String generateFileName(MultipartFile file, T entity);
}
