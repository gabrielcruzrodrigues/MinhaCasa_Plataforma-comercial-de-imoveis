package com.gabriel.minhacasa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.minhacasa.domain.DTO.CardDTO;
import com.gabriel.minhacasa.domain.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Immobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    @Column(nullable = false, length = 100)
    @NotBlank 
    private String name; //

    @Column(length = 500)
    private String description; //

    @Column(nullable = false, length = 50)
    @NotBlank
    private String address; //

    @Column(nullable = false, length = 50)
    private String city; //

    @Column(nullable = false, length = 50)
    @NotBlank
    private String neighborhood; //

    @Column(nullable = false, length = 50)
    @NotBlank
    private String state; //

    @Column(nullable = false)
    private boolean garage; //

    @Column(nullable = false)
    private int quantityBedrooms; //

    @Column(nullable = false)
    private int quantityRooms; //

    @Column
    private BigDecimal IPTU; //

    @Column(nullable = false)
    private BigDecimal price; //

    @Column(nullable = false)
    private boolean suite; //

    @Column
    private Double usefulArea; //

    @Column
    private Double totalArea; //

    @Column(nullable = false)
    private int quantityBathrooms; //

    @Column(nullable = false)
    private IntegrityEnum integrity; //

    @Column(nullable = false)
    private SellerTypeEnum sellerType; //

    @Column(nullable = false)
    private AgeEnum age; //

    @Column(nullable = false)
    private CategoryEnum category; //

    @Column(nullable = false)
    private LocalDateTime createdAt; //

    @Column(nullable = false)
    private TypeEnum type; //

    @Column(nullable = false)
    private boolean garden;

    @Column(nullable = false)
    private boolean virtualTour;

    @Column(nullable = false)
    private boolean videos;

    @Column(nullable = false)
    private boolean beach;

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

    @Column(nullable = false)
    private List<String> files;
}
