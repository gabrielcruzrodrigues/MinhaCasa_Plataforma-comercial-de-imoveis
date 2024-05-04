package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.service.ImageImmobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("image-immobile")
@RequiredArgsConstructor
public class ImageImmobileController {

    private final ImageImmobileService imageImmobileService;

    @PostMapping
    public ResponseEntity<Object> saveImages(@RequestBody List<MultipartFile> images, Long id) {
        this.imageImmobileService.saveFiles(images, id);
        return ResponseEntity.noContent().build();
    }
}
