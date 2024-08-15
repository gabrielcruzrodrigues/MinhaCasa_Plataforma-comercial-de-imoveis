package com.gabriel.minhacasa.controller;


import com.gabriel.minhacasa.domain.DTO.*;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.security.accessInterfaces.AdminAccess;
import com.gabriel.minhacasa.security.accessInterfaces.OwnerAccess;
import com.gabriel.minhacasa.security.accessInterfaces.UserAccess;
import com.gabriel.minhacasa.service.ImmobileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Criar novo imóvel.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Imóvel criado com sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor."),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
        @ApiResponse(responseCode = "400", description = "Lista de imagens vazia.")
    })
    @UserAccess
    @PostMapping
    public ResponseEntity<Object> create(@ModelAttribute CreateImmobileDTO request) {
        this.immobileService.createImmobile(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(description = "Buscar imóvel pelo id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imóvel encontrado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Imóvel não encontrado.")
    })
    @OwnerAccess
    @GetMapping("/{id}")
    public ResponseEntity<Immobile> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.immobileService.findByIdWithCompletePath(id));
    }

    @Operation(description = "Retorna uma lista com os imóveis e seus caminhos de imagens completos para download.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Imóvel não encontrado.")
    })
    @GetMapping("/details/{id}")
    public ResponseEntity<ImmobileWithSellerIdDTO> getImmobileWithFullImagePaths(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.immobileService.getImmobileWithSellerId(id));
    }

    //add documentation after because these endpoint is not active
    @OwnerAccess
    @PutMapping("/update")
    public ResponseEntity<Object> updateImmobile(@ModelAttribute UpdateImmobileDTO request) {
        this.immobileService.updateImmobile(request);
        return ResponseEntity.noContent().build();
    }

    //add documentation after because these endpoint is not active
    @DeleteMapping("/{id}")
    @AdminAccess
    public ResponseEntity<Object> deleteImmobile(@PathVariable Long id) {
        this.immobileService.disableImmobile(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Vender o imóvel e deletar suas imagens, exceto a primeira.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Processo de conclusão de venda feita com sucesso."),
        @ApiResponse(responseCode = "404", description = "Imóvel não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro ao tentar deletar as imagens.")
    })
    @OwnerAccess
    @PutMapping("/sold/{id}")
    public ResponseEntity<Object> soldImmobile(@PathVariable Long id) {
        this.immobileService.soldImmobile(id);
        return ResponseEntity.ok().body("Immobile sold successfully");
    }

    @Operation(description = "Fazer pesquisa de imóveis com base em paratros.")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Pesquisa feita com sucesso."),
        
    })
    @PostMapping("/search")
    public ResponseEntity<List<ImmobileByCardsDTO>> search(@ModelAttribute SearchParamsDTO params) {
        return ResponseEntity.ok().body(this.immobileService.findImmobileByParamsWithCompleteImagePath(params));
    }

    @GetMapping("/cards")
    public ResponseEntity<List<ImmobileByCardsDTO>> findImmobilesByCards() {
        return ResponseEntity.ok().body(this.immobileService.find4RandomImmobilesForHome());
    }

    @UserAccess
    @GetMapping("/favorites/{userId}")
    public ResponseEntity<List<ImmobileByCardsDTO>> findAllFavorites(@PathVariable Long userId) {
        List<ImmobileByCardsDTO> immobile = this.immobileService.searchForUserFavoritesImmobiles(userId);
        System.out.println(immobile);
        return ResponseEntity.ok().body(immobile);
    }

    @UserAccess
    @GetMapping("/favorites/user/{userId}")
    public ResponseEntity<List<Long>> findIdsOfImmobilesFavorited(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.immobileService.searchForUserFavoritesImmobilesId(userId));
    }
}
