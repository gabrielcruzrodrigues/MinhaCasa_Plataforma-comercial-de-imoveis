package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.domain.DTO.SaveFilesForImmobileDTO;
import com.gabriel.minhacasa.service.FilesImmobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("file-immobile")
@RequiredArgsConstructor
public class FilesImmobileController {

    private final FilesImmobileService filesImmobileService;

    @PostMapping
    public ResponseEntity<Object> saveFiles(@ModelAttribute SaveFilesForImmobileDTO request) {
        this.filesImmobileService.saveFiles(request.files(), request.immobileId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{immobileId}/{fileId}")
    public ResponseEntity<Object> deleteFiles(@PathVariable Long immobileId, @PathVariable Long fileId) {
        this.filesImmobileService.deleteFile(immobileId, fileId);
        return ResponseEntity.noContent().build();
    }
}
