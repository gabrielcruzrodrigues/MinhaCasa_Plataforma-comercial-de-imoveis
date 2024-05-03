package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.domain.DTO.CreateUserDTO;
import com.gabriel.minhacasa.domain.DTO.UpdateUserDTO;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO request) {
        this.userService.createUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.userService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserDTO request) {
        this.userService.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> disabledUser(@PathVariable Long id) {
        this.userService.disabledUser(id);
        return ResponseEntity.noContent().build();
    }
}
