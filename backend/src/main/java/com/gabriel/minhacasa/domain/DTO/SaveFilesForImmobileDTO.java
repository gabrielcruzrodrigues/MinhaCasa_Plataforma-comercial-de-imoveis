package com.gabriel.minhacasa.domain.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record SaveFilesForImmobileDTO(
        List<MultipartFile> files,
        Long immobileId
) {
}
