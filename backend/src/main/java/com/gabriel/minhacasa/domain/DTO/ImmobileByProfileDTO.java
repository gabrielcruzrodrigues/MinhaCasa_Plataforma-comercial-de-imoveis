package com.gabriel.minhacasa.domain.DTO;

public record ImmobileByProfileDTO(
        Long id,
        int quantityRooms,
        int quantityBedrooms,
        int quantityBathrooms,
        String imageUrl,
        float price,
        String name
) {
}
