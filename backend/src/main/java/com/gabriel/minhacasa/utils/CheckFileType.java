package com.gabriel.minhacasa.utils;

import com.gabriel.minhacasa.exceptions.FileNullContentException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public class CheckFileType {

    public Boolean verifyIfIsAImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (file != null) {
            return fileName.endsWith(".JPG") || fileName.endsWith(".jpg") || fileName.endsWith("jpeg") || fileName.endsWith(".png");
        } else {
            throw new FileNullContentException();
        }
    }
}
