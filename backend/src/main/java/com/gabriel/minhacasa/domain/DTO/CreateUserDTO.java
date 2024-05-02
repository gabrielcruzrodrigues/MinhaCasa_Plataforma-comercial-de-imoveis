package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.enums.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

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
        Date dateOfBirth,
        @NotBlank
        String nationality,
        @NotNull
        GenderEnum gender,
        @NotBlank
        String city
) {
}
