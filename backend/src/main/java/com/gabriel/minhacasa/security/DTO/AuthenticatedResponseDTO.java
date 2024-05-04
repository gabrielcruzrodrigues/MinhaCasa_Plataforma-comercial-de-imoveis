package com.gabriel.minhacasa.security.DTO;

public record AuthenticatedResponseDTO(
        Long id,
        String token,
        String role
//        @JsonIgnore
//        boolean login
) {
}
