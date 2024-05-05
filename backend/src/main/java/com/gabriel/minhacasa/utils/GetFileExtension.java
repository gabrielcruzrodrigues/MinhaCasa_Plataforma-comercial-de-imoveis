package com.gabriel.minhacasa.utils;

import com.gabriel.minhacasa.domain.enums.TypeFileEnum;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ExtensionNotValidException;
import org.springframework.stereotype.Component;

@Component
public class GetFileExtension {

    public TypeFileEnum getExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            String extension = filename.substring(filename.lastIndexOf(".") + 1);

            return switch (extension.toLowerCase()) {
                case "png", "jpg", "jpeg" -> TypeFileEnum.IMAGE;
                case "mp4" -> TypeFileEnum.VIDEO;
                default -> throw new ExtensionNotValidException();
            };

        } else {
            throw new ExtensionNotValidException();
        }
    }
}
