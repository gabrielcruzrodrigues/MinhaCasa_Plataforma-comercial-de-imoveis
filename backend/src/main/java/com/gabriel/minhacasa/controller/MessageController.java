package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.domain.DTO.MessageDTO;
import com.gabriel.minhacasa.domain.Message;
import com.gabriel.minhacasa.security.accessInterfaces.AdminAccess;
import com.gabriel.minhacasa.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok().body("backend is online");
    }

    @PostMapping
    public ResponseEntity<Object> createMessage(@RequestBody @Valid MessageDTO messageDTO) {
        this.messageService.create(messageDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @AdminAccess
    @GetMapping("/all")
    public ResponseEntity<List<Message>> findAll() {
        return ResponseEntity.ok().body(this.messageService.findAll());
    }

    @AdminAccess
    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.messageService.findById(id));
    }

    @AdminAccess
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Message>> findByType(@PathVariable String type) {
        return ResponseEntity.ok().body(this.messageService.findByType(type));
    }

    @AdminAccess
    @GetMapping("/sender/{senderName}")
    public ResponseEntity<List<Message>> findBySenderName(@PathVariable String senderName) {
        return ResponseEntity.ok().body(this.messageService.findBySenderName(senderName));
    }

    @AdminAccess
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable Long id) {
        this.messageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
