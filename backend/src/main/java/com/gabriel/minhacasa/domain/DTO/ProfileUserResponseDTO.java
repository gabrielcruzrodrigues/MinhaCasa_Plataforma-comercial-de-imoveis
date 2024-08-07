package com.gabriel.minhacasa.domain.DTO;

import java.util.List;

public record ProfileUserResponseDTO(
    Long id,
    String name,
    String dateOfBirth,
    String phone,
    String whatsapp,
    String email,
    List<ImmobileByCardsDTO> immobiles,
    String imageProfileUrl
) {
}
