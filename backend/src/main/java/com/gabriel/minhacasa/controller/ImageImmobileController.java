package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.service.FilesImmobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("image-immobile")
@RequiredArgsConstructor
public class ImageImmobileController {

    private final FilesImmobileService filesImmobileService;

    @PostMapping
    public ResponseEntity<Object> saveImages(@RequestBody List<MultipartFile> images, Long id) {
        this.filesImmobileService.saveFiles(images, id);
        return ResponseEntity.noContent().build();
    }
}
