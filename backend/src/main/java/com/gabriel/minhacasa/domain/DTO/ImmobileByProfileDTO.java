package com.gabriel.minhacasa.domain.DTO;

public record ImmobileByProfileDTO(
        Long id,
        int quantityRooms,
        int quantityBedrooms,
        int quantityBathrooms,
        String imageProfileBase64,
        float price,
        String name
) {
}
