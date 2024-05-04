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
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO request) { //change to @ModelAttribute
        this.userService.createUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
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

    //favorite

    @PutMapping("/favorite/add/{studentId}/{immobileId}")
    public ResponseEntity<Object> addNewImmobileFavorite(@PathVariable Long studentId, @PathVariable Long immobileId) {
        this.userService.addNewFavorite(studentId, immobileId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/favorite/remove/{studentId}/{immobileId}")
    public ResponseEntity<Object> removeNewImmobileFavorite(@PathVariable Long studentId, @PathVariable Long immobileId) {
        this.userService.removeFavorite(studentId, immobileId);
        return ResponseEntity.noContent().build();
    }
}
