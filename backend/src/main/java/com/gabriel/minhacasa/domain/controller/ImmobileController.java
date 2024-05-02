package com.gabriel.minhacasa.domain.controller;


import com.gabriel.minhacasa.domain.DTO.CreateImmobileDTO;
import com.gabriel.minhacasa.domain.DTO.UpdateImmobileDTO;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.service.ImmobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/immobile")
@RequiredArgsConstructor
public class ImmobileController {

    private final ImmobileService immobileService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateImmobileDTO request) {
        this.immobileService.createImmobile(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Immobile> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.immobileService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateImmobile(@RequestBody UpdateImmobileDTO request) {
        this.immobileService.updateImmobile(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteImmobile(@PathVariable Long id) {
        this.immobileService.deleteImmobile(id);
        return ResponseEntity.noContent().build();
    }
}
