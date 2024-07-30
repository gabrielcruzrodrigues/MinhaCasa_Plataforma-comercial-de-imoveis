package com.gabriel.minhacasa.domain.DTO;

import java.math.BigDecimal;

public record ImmobileByCardsDTO(
        Long id,
        int quantityRooms,
        int quantityBedrooms,
        int quantityBathrooms,
        String imageUrl,
        BigDecimal price,
        String name,
        String description,
        Long sellerId
) {
}
