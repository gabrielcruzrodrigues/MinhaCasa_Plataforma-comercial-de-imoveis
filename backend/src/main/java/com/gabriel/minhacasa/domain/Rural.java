package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Rural extends Immobile {

    @Column(nullable = false)
    private RuralTypeEnum type;

    @Column(nullable = false)
    private boolean grill;

    @Column(nullable = false)
    private boolean disabledAccess;

    @Column(nullable = false)
    private boolean energyGenerator;

    @Column(nullable = false)
    private boolean elevator;

    @Column(nullable = false)
    private boolean pool;

    @Column(nullable = false)
    private boolean internet;

    @Column(nullable = false)
    private boolean cableTV;

    @Column(nullable = false)
    private boolean cabinetsInTheKitchen;

    @Column(nullable = false)
    private boolean bathroomInTheRoom;

    @Column(nullable = false)
    private boolean americanKitchen;

    @Column(nullable = false)
    private boolean fireplace;

    @Column(nullable = false)
    private boolean serviceArea;

    @Column(nullable = false)
    private boolean cameras;

    @Column(nullable = false)
    private boolean furnished;

    public Rural(Long id, @NotBlank String name, @NotBlank String address, @NotBlank String neighborhood, @NotBlank String state,
                 boolean garage, int quantityBedrooms, int quantityRooms, float IPTU, float price, boolean suite, float usefulArea,
                 float totalArea, int quantityBathrooms, IntegrityEnum integrity, SellerTypeEnum sellerType, AgeEnum age, CategoryEnum category,
                 LocalDateTime createdAt, boolean garden, boolean virtualTour, boolean videos, boolean beach, RuralTypeEnum type,
                 boolean grill, boolean disabledAccess, boolean energyGenerator, boolean elevator, boolean pool, boolean internet,
                 boolean cableTV, boolean cabinetsInTheKitchen, boolean bathroomInTheRoom, boolean americanKitchen, boolean fireplace,
                 boolean serviceArea, boolean cameras, boolean furnished) {

        super(id, name, address, neighborhood, state, garage, quantityBedrooms, quantityRooms, IPTU, price, suite, usefulArea, totalArea,
                quantityBathrooms, integrity, sellerType, age, category, createdAt, garden, virtualTour, videos, beach);

        this.type = type;
        this.grill = grill;
        this.disabledAccess = disabledAccess;
        this.energyGenerator = energyGenerator;
        this.elevator = elevator;
        this.pool = pool;
        this.internet = internet;
        this.cableTV = cableTV;
        this.cabinetsInTheKitchen = cabinetsInTheKitchen;
        this.bathroomInTheRoom = bathroomInTheRoom;
        this.americanKitchen = americanKitchen;
        this.fireplace = fireplace;
        this.serviceArea = serviceArea;
        this.cameras = cameras;
        this.furnished = furnished;
    }
}
