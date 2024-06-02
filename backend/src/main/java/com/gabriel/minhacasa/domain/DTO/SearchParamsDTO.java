package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class SearchParamsDTO {
    Optional<Integer> pageNumber;
    Optional<Integer> pageSize;
    Optional<String> name;
    Optional<String> city;
    Optional<String> neighborhood;
    Optional<String> state;
    Optional<Boolean> garage;
    Optional<Integer> quantityBedrooms;
    Optional<Integer> quantityRooms;
    Optional<String> iptu;
    Optional<Float> price;
    Optional<Boolean> suite;
    Optional<Float> totalArea;
    Optional<Integer> quantityBathrooms;
    Optional<IntegrityEnum> integrity;
    Optional<SellerTypeEnum> sellerType;
    Optional<AgeEnum> age;
    Optional<CategoryEnum> category;
    Optional<TypeEnum> type;
    Optional<Boolean> garden;
    Optional<Boolean> virtualTour;
    Optional<Boolean> videos;
    Optional<Boolean> beach;
    Optional<Boolean> disabledAccess;
    Optional<Boolean> playground;
    Optional<Boolean> grill;
    Optional<Boolean> energyGenerator;
    Optional<Boolean> closeToTheCenter;
    Optional<Boolean> elevator;
    Optional<Boolean> pool;
    Optional<Boolean> frontDesk;
    Optional<Boolean> multiSportsCourt;
    Optional<Boolean> gym;
    Optional<Boolean> steamRoom;
    Optional<Boolean> cableTV;
    Optional<Boolean> heating;
    Optional<Boolean> cabinetsInTheKitchen;
    Optional<Boolean> bathroomInTheRoom;
    Optional<Boolean> internet;
    Optional<Boolean> partyRoom;
    Optional<Boolean> airConditioning;
    Optional<Boolean> americanKitchen;
    Optional<Boolean> hydromassage;
    Optional<Boolean> fireplace;
    Optional<Boolean> privatePool;
    Optional<Boolean> electronicGate;
    Optional<Boolean> serviceArea;
    Optional<Boolean> pub;
    Optional<Boolean> closet;
    Optional<Boolean> office;
    Optional<Boolean> yard;
    Optional<Boolean> alarmSystem;
    Optional<Boolean> balcony;
    Optional<Boolean> concierge24Hour;
    Optional<Boolean> walledArea;
    Optional<Boolean> dogAllowed;
    Optional<Boolean> catAllowed;
    Optional<Boolean> cameras;
    Optional<Boolean> furnished;
    Optional<Boolean> seaView;
    Optional<Boolean> gatedCommunity;
}

