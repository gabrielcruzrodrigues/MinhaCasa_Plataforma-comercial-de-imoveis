package com.gabriel.minhacasa.utils;

import com.gabriel.minhacasa.exceptions.DataTypeNotAcceptedForThisOperationException;
import com.gabriel.minhacasa.exceptions.FileNullContentException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CheckFileType {

    public Boolean verifyIfIsAImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (file != null) {
            boolean verify = fileName.endsWith(".JPG") || fileName.endsWith(".jpg") || fileName.endsWith("jpeg") || fileName.endsWith(".png");

            if (verify) {
                return true;
            } else {
                throw new DataTypeNotAcceptedForThisOperationException();
            }

        } else {
            throw new FileNullContentException("verifyIfIsAImage method");
        }
    }

    public Boolean verifyFilesForImmobile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (file != null) {
            boolean verify = fileName.endsWith(".JPG") || fileName.endsWith(".jpg") || fileName.endsWith("jpeg") || fileName.endsWith(".png")
                    || fileName.endsWith("mp4");

            if (verify) {
                return true;
            } else {
                throw new DataTypeNotAcceptedForThisOperationException();
            }

        } else {
            throw new FileNullContentException("verifyIfIsAImage method");
        }
    }
}
