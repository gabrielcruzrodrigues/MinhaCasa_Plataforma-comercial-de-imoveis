package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.domain.DTO.CreateUserDTO;
import com.gabriel.minhacasa.domain.DTO.ProfileUserResponseDTO;
import com.gabriel.minhacasa.domain.DTO.UpdateUserDTO;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.security.DTO.AuthenticatedResponseDTO;
import com.gabriel.minhacasa.security.service.AuthenticationService;
import com.gabriel.minhacasa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<Object> createUser(@ModelAttribute CreateUserDTO request) {
        this.userService.createUser(request);
        AuthenticatedResponseDTO authenticatedResponseDTO = this.authenticationService.loginUser(request.email(), request.password());
        return new ResponseEntity<>(authenticatedResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileUserResponseDTO> findByIdForProfile(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok().body(this.userService.findByIdForProfile(id));
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
