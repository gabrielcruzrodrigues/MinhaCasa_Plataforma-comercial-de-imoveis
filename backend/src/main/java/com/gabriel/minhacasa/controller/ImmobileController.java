package com.gabriel.minhacasa.controller;


import com.gabriel.minhacasa.domain.DTO.*;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.service.ImmobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/immobile")
@RequiredArgsConstructor
public class ImmobileController {

    private final ImmobileService immobileService;

    @PostMapping
    public ResponseEntity<Object> create(@ModelAttribute CreateImmobileDTO request) { //change to modelAttribute
        this.immobileService.createImmobile(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Immobile> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.immobileService.findById(id));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<ImmobileWithSellerIdDTO> getImmobileWithFullImagePaths(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.immobileService.getImmobileWithCompleteImagesPath(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateImmobile(@RequestBody UpdateImmobileDTO request) {
        this.immobileService.updateImmobile(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteImmobile(@PathVariable Long id) {
        this.immobileService.disableImmobile(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/sold/{id}")
    public ResponseEntity<Object> soldImmobile(@PathVariable Long id) {
        this.immobileService.soldImmobile(id);
        return ResponseEntity.ok().body("Immobile sold successfully");
    }

    @PostMapping("/search")
    public ResponseEntity<List<ImmobileByProfileDTO>> search(@ModelAttribute SearchParamsDTO params) {
        return ResponseEntity.ok().body(this.immobileService.findImmobileByParamsWithCompleteImagePath(params));
    }
}
