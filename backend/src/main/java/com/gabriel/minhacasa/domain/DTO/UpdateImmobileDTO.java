package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateImmobileDTO(
        @NotBlank
        String immobileTitle,
        @NotBlank
        String description,
        @NotBlank
        String address,
        @NotBlank
        String city,
        @NotBlank
        String neighborhood,
        @NotBlank
        String state,
        @NotNull
        boolean garage,
        @NotNull
        int quantityBedrooms,
        @NotNull
        int quantityRooms,
        @NotNull
        BigDecimal IPTU,
        @NotNull
        BigDecimal price,
        @NotNull
        boolean suite,
        @NotNull
        Double totalArea,
        @NotNull
        int quantityBathrooms,
        @NotNull
        IntegrityEnum integrity,
        @NotNull
        SellerTypeEnum sellerType,
        @NotNull
        AgeEnum age,
        @NotNull
        CategoryEnum category,
        @NotNull
        TypeEnum type,
        @NotNull
        boolean garden,
        @NotNull
        boolean beach,
        @NotNull
        boolean disabledAccess,
        @NotNull
        boolean playground,
        @NotNull
        boolean grill,
        @NotNull
        boolean energyGenerator,
        @NotNull
        boolean closeToTheCenter,
        @NotNull
        boolean elevator,
        @NotNull
        boolean pool,
        @NotNull
        boolean frontDesk,
        @NotNull
        boolean multiSportsCourt,
        @NotNull
        boolean gym,
        @NotNull
        boolean steamRoom,
        @NotNull
        boolean cableTV,
        @NotNull
        boolean heating,
        @NotNull
        boolean cabinetsInTheKitchen,
        @NotNull
        boolean bathroomInTheRoom,
        @NotNull
        boolean internet,
        @NotNull
        boolean partyRoom,
        @NotNull
        boolean airConditioning,
        @NotNull
        boolean americanKitchen,
        @NotNull
        boolean hydromassage,
        @NotNull
        boolean fireplace,
        @NotNull
        boolean privatePool,
        @NotNull
        boolean electronicGate,
        @NotNull
        boolean serviceArea,
        @NotNull
        boolean pub,
        @NotNull
        boolean closet,
        @NotNull
        boolean office,
        @NotNull
        boolean yard,
        @NotNull
        boolean alarmSystem,
        @NotNull
        boolean balcony,
        @NotNull
        boolean concierge24Hour,
        @NotNull
        boolean walledArea,
        @NotNull
        boolean dogAllowed,
        @NotNull
        boolean catAllowed,
        @NotNull
        boolean cameras,
        @NotNull
        boolean furnished,
        @NotNull
        boolean seaView,
        @NotNull
        boolean gatedCommunity
) {
}
