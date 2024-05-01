package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class Ground extends Immobile {
    private GroundTypeEnum type;

    public Ground(Long id, @NotBlank String name, @NotBlank String address, @NotBlank String neighborhood, @NotBlank String state,
                  boolean garage, int quantityBedrooms, int quantityRooms, float IPTU, float price, boolean suite, float usefulArea,
                  float totalArea, int quantityBathrooms, IntegrityEnum integrity, SellerTypeEnum sellerType, AgeEnum age,
                  CategoryEnum category, LocalDateTime createdAt, boolean garden, boolean virtualTour, boolean videos, boolean beach, GroundTypeEnum type) {
        super(id, name, address, neighborhood, state, garage, quantityBedrooms, quantityRooms, IPTU, price, suite, usefulArea,
                totalArea, quantityBathrooms, integrity, sellerType, age, category, createdAt, garden, virtualTour, videos, beach);
        this.type = type;
    }
}
