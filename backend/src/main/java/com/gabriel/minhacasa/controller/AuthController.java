package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.security.DTO.AuthenticatedResponseDTO;
import com.gabriel.minhacasa.security.DTO.LoginDTO;
import com.gabriel.minhacasa.security.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Login do usuário no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso."),
        @ApiResponse(responseCode = "500", description = "Problema interno do servidor."),
        @ApiResponse(responseCode = "401", description = "Login não autorizado.")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticatedResponseDTO> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok().body(this.authenticationService.loginUser(request.email(), request.password()));
    }
}
