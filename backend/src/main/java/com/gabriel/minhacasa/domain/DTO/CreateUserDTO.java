package com.gabriel.minhacasa.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record CreateUserDTO(
        @NotBlank
        String name,
        @NotBlank
        String phone,
        @NotBlank
        String whatsapp,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        MultipartFile imageProfile,
        @NotNull
        LocalDate dateOfBirth
) {
}
