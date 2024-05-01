package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.CategoryEnum;
import com.gabriel.minhacasa.domain.enums.CommercialTypeEnum;
import com.gabriel.minhacasa.domain.enums.IntegrityEnum;
import com.gabriel.minhacasa.domain.enums.SellerTypeEnum;
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
public class Commercial extends Immobile {

    @Column(nullable = false)
    private CommercialTypeEnum type;

    @Column(nullable = false)
    private boolean disabledAccess;

    @Column(nullable = false)
    private boolean playground;

    @Column(nullable = false)
    private boolean energyGenerator;

    @Column(nullable = false)
    private boolean elevator;

    @Column(nullable = false)
    private boolean pool;

    @Column(nullable = false)
    private boolean frontDesk;

    @Column(nullable = false)
    private boolean internet;

    @Column(nullable = false)
    private boolean partyRoom;

    @Column(nullable = false)
    private boolean airConditioning;

    @Column(nullable = false)
    private boolean electronicGate;

    @Column(nullable = false)
    private boolean pub;

    @Column(nullable = false)
    private boolean office;

    @Column(nullable = false)
    private boolean alarmSystem;

    @Column(nullable = false)
    private boolean balcony;

    @Column(nullable = false)
    private boolean concierge24Hour;

    @Column(nullable = false)
    private boolean cameras;

    public Commercial(Long id, @NotBlank String name, @NotBlank String address, @NotBlank String neighborhood, @NotBlank String state,
                      boolean garage, int quantityBedrooms, int quantityRooms, float IPTU, float price, boolean suite, float usefulArea,
                      float totalArea, int quantityBathrooms, IntegrityEnum integrity, SellerTypeEnum sellerType, int age,
                      CategoryEnum category, LocalDateTime createdAt, boolean garden, boolean virtualTour, boolean videos,
                      boolean beach, CommercialTypeEnum type, boolean disabledAccess, boolean playground, boolean energyGenerator,
                      boolean elevator, boolean pool, boolean frontDesk, boolean internet, boolean partyRoom, boolean airConditioning,
                      boolean electronicGate, boolean pub, boolean office, boolean alarmSystem, boolean balcony, boolean concierge24Hour, boolean cameras) {

        super(id, name, address, neighborhood, state, garage, quantityBedrooms, quantityRooms, IPTU, price, suite, usefulArea, totalArea,
                quantityBathrooms, integrity, sellerType, age, category, createdAt, garden, virtualTour, videos, beach);

        this.type = type;
        this.disabledAccess = disabledAccess;
        this.playground = playground;
        this.energyGenerator = energyGenerator;
        this.elevator = elevator;
        this.pool = pool;
        this.frontDesk = frontDesk;
        this.internet = internet;
        this.partyRoom = partyRoom;
        this.airConditioning = airConditioning;
        this.electronicGate = electronicGate;
        this.pub = pub;
        this.office = office;
        this.alarmSystem = alarmSystem;
        this.balcony = balcony;
        this.concierge24Hour = concierge24Hour;
        this.cameras = cameras;
    }
}
