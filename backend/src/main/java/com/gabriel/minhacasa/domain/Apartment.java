package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.ApartmentTypeEnum;
import com.gabriel.minhacasa.domain.enums.CategoryEnum;
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
public class Apartment extends Immobile {

    private ApartmentTypeEnum type;

    @Column(nullable = false)
    private boolean disabledAccess;

    @Column(nullable = false)
    private boolean playground;

    @Column(nullable = false)
    private boolean grill;

    @Column(nullable = false)
    private boolean energyGenerator;

    @Column(nullable = false)
    private boolean closeToTheCenter;

    @Column(nullable = false)
    private boolean elevator;

    @Column(nullable = false)
    private boolean pool;

    @Column(nullable = false)
    private boolean frontDesk;

    @Column(nullable = false)
    private boolean multiSportsCourt;

    @Column(nullable = false)
    private boolean gym;

    @Column(nullable = false)
    private boolean steamRoom;

    @Column(nullable = false)
    private boolean cableTV;

    @Column(nullable = false)
    private boolean heating;

    @Column(nullable = false)
    private boolean cabinetsInTheKitchen;

    @Column(nullable = false)
    private boolean bathroomInTheRoom;

    @Column(nullable = false)
    private boolean internet;

    @Column(nullable = false)
    private boolean partyRoom;

    @Column(nullable = false)
    private boolean airConditioning;

    @Column(nullable = false)
    private boolean americanKitchen;

    @Column(nullable = false)
    private boolean hydromassage;

    @Column(nullable = false)
    private boolean fireplace;

    @Column(nullable = false)
    private boolean privatePool;

    @Column(nullable = false)
    private boolean electronicGate;

    @Column(nullable = false)
    private boolean serviceArea;

    @Column(nullable = false)
    private boolean pub;

    @Column(nullable = false)
    private boolean closet;

    @Column(nullable = false)
    private boolean office;

    @Column(nullable = false)
    private boolean yard;

    @Column(nullable = false)
    private boolean alarmSystem;

    @Column(nullable = false)
    private boolean balcony;

    @Column(nullable = false)
    private boolean concierge24Hour;

    @Column(nullable = false)
    private boolean walledArea;

    @Column(nullable = false)
    private boolean dogAllowed;

    @Column(nullable = false)
    private boolean catAllowed;

    @Column(nullable = false)
    private boolean cameras;

    @Column(nullable = false)
    private boolean furnished;

    @Column(nullable = false)
    private boolean seaView;

    public Apartment(Long id, @NotBlank String name, @NotBlank String address, @NotBlank String neighborhood, @NotBlank String state,
                     boolean garage, int quantityBedrooms, int quantityRooms, float IPTU, float price, boolean suite, float usefulArea,
                     float totalArea, int quantityBathrooms, IntegrityEnum integrity, SellerTypeEnum sellerType, int age, CategoryEnum category,
                     LocalDateTime createdAt, boolean garden, boolean virtualTour, boolean videos, boolean beach, ApartmentTypeEnum type,
                     boolean disabledAccess, boolean playground, boolean grill, boolean energyGenerator, boolean closeToTheCenter,
                     boolean elevator, boolean pool, boolean frontDesk, boolean multiSportsCourt, boolean gym, boolean steamRoom,
                     boolean cableTV, boolean heating, boolean cabinetsInTheKitchen, boolean bathroomInTheRoom, boolean internet,
                     boolean partyRoom, boolean airConditioning, boolean americanKitchen, boolean hydromassage, boolean fireplace,
                     boolean privatePool, boolean electronicGate, boolean serviceArea, boolean pub, boolean closet, boolean office,
                     boolean yard, boolean alarmSystem, boolean balcony, boolean concierge24Hour, boolean walledArea, boolean dogAllowed,
                     boolean catAllowed, boolean cameras, boolean furnished, boolean seaView) {

        super(id, name, address, neighborhood, state, garage, quantityBedrooms, quantityRooms, IPTU, price, suite, usefulArea,
                totalArea, quantityBathrooms, integrity, sellerType, age, category, createdAt, garden, virtualTour, videos, beach);

        this.type = type;
        this.disabledAccess = disabledAccess;
        this.playground = playground;
        this.grill = grill;
        this.energyGenerator = energyGenerator;
        this.closeToTheCenter = closeToTheCenter;
        this.elevator = elevator;
        this.pool = pool;
        this.frontDesk = frontDesk;
        this.multiSportsCourt = multiSportsCourt;
        this.gym = gym;
        this.steamRoom = steamRoom;
        this.cableTV = cableTV;
        this.heating = heating;
        this.cabinetsInTheKitchen = cabinetsInTheKitchen;
        this.bathroomInTheRoom = bathroomInTheRoom;
        this.internet = internet;
        this.partyRoom = partyRoom;
        this.airConditioning = airConditioning;
        this.americanKitchen = americanKitchen;
        this.hydromassage = hydromassage;
        this.fireplace = fireplace;
        this.privatePool = privatePool;
        this.electronicGate = electronicGate;
        this.serviceArea = serviceArea;
        this.pub = pub;
        this.closet = closet;
        this.office = office;
        this.yard = yard;
        this.alarmSystem = alarmSystem;
        this.balcony = balcony;
        this.concierge24Hour = concierge24Hour;
        this.walledArea = walledArea;
        this.dogAllowed = dogAllowed;
        this.catAllowed = catAllowed;
        this.cameras = cameras;
        this.furnished = furnished;
        this.seaView = seaView;
    }
}
