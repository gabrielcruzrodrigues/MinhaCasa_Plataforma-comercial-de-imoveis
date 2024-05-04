package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.security.DTO.AuthenticatedResponseDTO;
import com.gabriel.minhacasa.security.DTO.LoginDTO;
import com.gabriel.minhacasa.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticatedResponseDTO> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok().body(this.authenticationService.loginUser(request.email(), request.password()));
    }
}
