package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.AgeEnum;
import com.gabriel.minhacasa.domain.enums.CategoryEnum;
import com.gabriel.minhacasa.domain.enums.IntegrityEnum;
import com.gabriel.minhacasa.domain.enums.SellerTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    protected boolean garden;

    @Column(nullable = false)
    protected boolean virtualTour;

    @Column(nullable = false)
    protected boolean videos;

    @Column(nullable = false)
    protected boolean beach;
}
