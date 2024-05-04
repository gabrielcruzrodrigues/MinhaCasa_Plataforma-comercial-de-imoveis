package com.gabriel.minhacasa.security.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
