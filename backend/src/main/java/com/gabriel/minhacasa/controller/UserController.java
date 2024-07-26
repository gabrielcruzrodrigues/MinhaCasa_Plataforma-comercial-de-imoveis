package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.domain.DTO.CreateUserDTO;
import com.gabriel.minhacasa.domain.DTO.ProfileUserResponseDTO;
import com.gabriel.minhacasa.domain.DTO.UpdateUserDTO;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.security.DTO.AuthenticatedResponseDTO;
import com.gabriel.minhacasa.security.service.AuthenticationService;
import com.gabriel.minhacasa.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(description = "Criar um novo usuário.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Erro ao passar parâmetros incorretos na criação.")
    })
    @PostMapping
    public ResponseEntity<Object> createUser(@ModelAttribute CreateUserDTO request) {
        this.userService.createUser(request);
        AuthenticatedResponseDTO authenticatedResponseDTO = this.authenticationService.loginUser(request.email(), request.password());
        return new ResponseEntity<>(authenticatedResponseDTO, HttpStatus.CREATED);
    }

    @Operation(description = "Buscar usuário.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso."),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(description = "Buscar dados do usuário para o perfil.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileUserResponseDTO> findByIdForProfile(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok().body(this.userService.findByIdForProfile(id));
    }

    //add documentation after becouse this endpoint is not active
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserDTO request) {
        this.userService.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    //add documentation after becouse this endpoint is not active
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> disabledUser(@PathVariable Long id) {
        this.userService.disabledUser(id);
        return ResponseEntity.noContent().build();
    }

    //favorite

    //add documentation after becouse this endpoint is not active
    @PutMapping("/favorite/add/{studentId}/{immobileId}")
    public ResponseEntity<Object> addNewImmobileFavorite(@PathVariable Long studentId, @PathVariable Long immobileId) {
        this.userService.addNewFavorite(studentId, immobileId);
        return ResponseEntity.noContent().build();
    }

    //add documentation after becouse this endpoint is not active
    @PutMapping("/favorite/remove/{studentId}/{immobileId}")
    public ResponseEntity<Object> removeNewImmobileFavorite(@PathVariable Long studentId, @PathVariable Long immobileId) {
        this.userService.removeFavorite(studentId, immobileId);
        return ResponseEntity.noContent().build();
    }
}
