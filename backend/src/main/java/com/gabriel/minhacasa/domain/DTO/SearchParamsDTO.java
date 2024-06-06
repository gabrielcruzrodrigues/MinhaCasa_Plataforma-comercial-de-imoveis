package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Setter
public class SearchParamsDTO {
    Integer pageNumber;
    Integer pageSize;
    String name;
    String city;
    String neighborhood;
    String state;
    Boolean garage;
    String quantityBedrooms;
    String quantityRooms;
    String iptu;
    BigDecimal minPrice;
    BigDecimal maxPrice;
    Boolean suite;
    String totalArea;
    String quantityBathrooms;
    IntegrityEnum integrity;
    SellerTypeEnum sellerType;
    AgeEnum age;
    CategoryEnum category;
    TypeEnum type;
    Boolean garden;
    Boolean virtualTour;
    Boolean videos;
    Boolean beach;
    Boolean disabledAccess;
    Boolean playground;
    Boolean grill;
    Boolean energyGenerator;
    Boolean closeToTheCenter;
    Boolean elevator;
    Boolean pool;
    Boolean frontDesk;
    Boolean multiSportsCourt;
    Boolean gym;
    Boolean steamRoom;
    Boolean cableTV;
    Boolean heating;
    Boolean cabinetsInTheKitchen;
    Boolean bathroomInTheRoom;
    Boolean internet;
    Boolean partyRoom;
    Boolean airConditioning;
    Boolean americanKitchen;
    Boolean hydromassage;
    Boolean fireplace;
    Boolean privatePool;
    Boolean electronicGate;
    Boolean serviceArea;
    Boolean pub;
    Boolean closet;
    Boolean office;
    Boolean yard;
    Boolean alarmSystem;
    Boolean balcony;
    Boolean concierge24Hour;
    Boolean walledArea;
    Boolean dogAllowed;
    Boolean catAllowed;
    Boolean cameras;
    Boolean furnished;
    Boolean seaView;
    Boolean gatedCommunity;
}

