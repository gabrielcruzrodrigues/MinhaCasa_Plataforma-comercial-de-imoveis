package com.gabriel.minhacasa.domain.DTO;

public record ImmobileByProfileDTO(
        Long id,
        int quantityRooms,
        int quantityBedrooms,
        int quantityBathrooms,
        String imageUrl,
        Double price,
        String name,
        String description,
        Long sellerId
) {
}
