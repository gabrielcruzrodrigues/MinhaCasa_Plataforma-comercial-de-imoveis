package com.gabriel.minhacasa.domain.DTO;

import java.util.List;

public record ProfileUserResponseDTO(
    String name,
    String dateOfBirth,
    String phone,
    String whatsapp,
    String email,
    String state,
    String city,
    List<ImmobileByProfileDTO> immobiles,
    String imageProfileBase64
) {
}
