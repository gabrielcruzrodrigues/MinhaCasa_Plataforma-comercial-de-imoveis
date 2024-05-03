package com.gabriel.minhacasa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.minhacasa.domain.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Immobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String name;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String address;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String neighborhood;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String state;

    @Column(nullable = false)
    protected boolean garage;

    @Column(nullable = false)
    protected int quantityBedrooms;

    @Column(nullable = false)
    protected int quantityRooms;

    @Column(nullable = false)
    protected float IPTU;

    @Column(nullable = false)
    protected float price;

    @Column(nullable = false)
    protected boolean suite;

    @Column(nullable = false)
    protected float usefulArea;

    @Column(nullable = false)
    protected float totalArea;

    @Column(nullable = false)
    protected int quantityBathrooms;

    @Column(nullable = false)
    protected IntegrityEnum integrity;

    @Column(nullable = false)
    protected SellerTypeEnum sellerType;

    @Column(nullable = false)
    protected AgeEnum age;

    @Column(nullable = false)
    protected CategoryEnum category;

    @Column(nullable = false)
    protected LocalDateTime createdAt;

    @Column(nullable = false)
    protected TypeEnum type;

    @Column(nullable = false)
    protected boolean garden;

    @Column(nullable = false)
    protected boolean virtualTour;

    @Column(nullable = false)
    protected boolean videos;

    @Column(nullable = false)
    protected boolean beach;

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

    @Column(nullable = false)
    private boolean gatedCommunity;

    @Column(nullable = false)
    private boolean active;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToMany(mappedBy = "favorites")
    private List<User> favoriteUser;
}
