package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.DTO.SearchParamsDTO;
import com.gabriel.minhacasa.domain.Immobile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImmobileRepositoryImpl implements ImmobileRepositoryCustomInterface {

    private final EntityManager entityManager;

    public ImmobileRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Immobile> searchByParams(SearchParamsDTO params) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Immobile> criteriaQuery = criteriaBuilder.createQuery(Immobile.class);
        Root<Immobile> root = criteriaQuery.from(Immobile.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!params.getName().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("name"), params.getName()));
        }

        if (!params.getCity().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("city"), params.getCity()));
        }

        if (!params.getNeighborhood().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("neighborhood"), params.getNeighborhood()));
        }

        if (!params.getState().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("state"), params.getState()));
        }

        if (params.getGarage()) {
            predicates.add(criteriaBuilder.equal(root.get("garage"), params.getGarage()));
        }

        if (!params.getQuantityBedrooms().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("quantityBedrooms"), params.getQuantityBedrooms()));
        }

        if (!params.getQuantityRooms().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("quantityRooms"), params.getQuantityRooms()));
        }

        if (!params.getIptu().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("IPTU"), params.getIptu()));
        }

        if (params.getMinPrice() != null) {
            BigDecimal minPrice = params.getMinPrice().setScale(2, RoundingMode.HALF_UP);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (params.getMaxPrice() != null) {
            BigDecimal maxPrice = params.getMaxPrice().setScale(2, RoundingMode.HALF_UP);
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        if (params.getSuite()) {
            predicates.add(criteriaBuilder.equal(root.get("suite"), params.getSuite()));
        }

        if (!params.getTotalArea().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("totalArea"), params.getTotalArea()));
        }

        if (!params.getQuantityBathrooms().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("quantityBathrooms"), params.getQuantityBathrooms()));
        }

        if (params.getIntegrity() != null) {
            predicates.add(criteriaBuilder.equal(root.get("integrity"), params.getIntegrity()));
        }

        if (params.getSellerType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("sellerType"), params.getSellerType()));
        }

        if (params.getAge() != null) {
            predicates.add(criteriaBuilder.equal(root.get("age"), params.getAge()));
        }

        if (params.getCategory() != null) {
            predicates.add(criteriaBuilder.equal(root.get("category"), params.getCategory()));
        }

        if (params.getType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), params.getType()));
        }

        if (params.getGarden()) {
            predicates.add(criteriaBuilder.equal(root.get("garden"), params.getGarden()));
        }

        if (params.getVirtualTour()) {
            predicates.add(criteriaBuilder.equal(root.get("virtualTour"), params.getVirtualTour()));
        }

        if (params.getVideos()) {
            predicates.add(criteriaBuilder.equal(root.get("videos"), params.getVideos()));
        }

        if (params.getBeach()) {
            predicates.add(criteriaBuilder.equal(root.get("beach"), params.getBeach()));
        }

        if (params.getDisabledAccess()) {
            predicates.add(criteriaBuilder.equal(root.get("disabledAccess"), params.getDisabledAccess()));
        }

        if (params.getPlayground()) {
            predicates.add(criteriaBuilder.equal(root.get("playground"), params.getPlayground()));
        }

        if (params.getGrill()) {
            predicates.add(criteriaBuilder.equal(root.get("grill"), params.getGrill()));
        }

        if (params.getEnergyGenerator()) {
        predicates.add(criteriaBuilder.equal(root.get("energyGenerator"), params.getEnergyGenerator()));
        }

        if (params.getCloseToTheCenter()) {
            predicates.add(criteriaBuilder.equal(root.get("closeToTheCenter"), params.getCloseToTheCenter()));
        }

        if (params.getElevator()) {
            predicates.add(criteriaBuilder.equal(root.get("elevator"), params.getElevator()));
        }

        if (params.getPool()) {
            predicates.add(criteriaBuilder.equal(root.get("pool"), params.getPool()));
        }

        if (params.getFrontDesk()) {
            predicates.add(criteriaBuilder.equal(root.get("frontDesk"), params.getFrontDesk()));
        }

        if (params.getMultiSportsCourt()) {
            predicates.add(criteriaBuilder.equal(root.get("multiSportsCourt"), params.getMultiSportsCourt()));
        }

        if (params.getGym()) {
            predicates.add(criteriaBuilder.equal(root.get("gym"), params.getGym()));
        }

        if (params.getSteamRoom()) {
            predicates.add(criteriaBuilder.equal(root.get("steamRoom"), params.getSteamRoom()));
        }

        if (params.getCableTV()) {
            predicates.add(criteriaBuilder.equal(root.get("cableTV"), params.getCableTV()));
        }

        if (params.getHeating()) {
            predicates.add(criteriaBuilder.equal(root.get("heating"), params.getCableTV()));
        }

        if (params.getCabinetsInTheKitchen()) {
            predicates.add(criteriaBuilder.equal(root.get("cabinetsInTheKitchen"), params.getCabinetsInTheKitchen()));
        }

        if (params.getBathroomInTheRoom()) {
            predicates.add(criteriaBuilder.equal(root.get("bathroomInTheRoom"), params.getBathroomInTheRoom()));
        }

        if (params.getInternet()) {
            predicates.add(criteriaBuilder.equal(root.get("internet"), params.getInternet()));
        }

        if (params.getPartyRoom()) {
            predicates.add(criteriaBuilder.equal(root.get("partyRoom"), params.getPartyRoom()));
        }

        if (params.getAirConditioning()) {
            predicates.add(criteriaBuilder.equal(root.get("airConditioning"), params.getAirConditioning()));
        }

        if (params.getAmericanKitchen()) {
            predicates.add(criteriaBuilder.equal(root.get("americanKitchen"), params.getAmericanKitchen()));
        }

        if (params.getHydromassage()) {
            predicates.add(criteriaBuilder.equal(root.get("hydromassage"), params.getHydromassage()));
        }

        if (params.getFireplace()) {
            predicates.add(criteriaBuilder.equal(root.get("fireplace"), params.getFireplace()));
        }

        if (params.getPrivatePool()) {
            predicates.add(criteriaBuilder.equal(root.get("privatePool"), params.getPrivatePool()));
        }

        if (params.getElectronicGate()) {
            predicates.add(criteriaBuilder.equal(root.get("electronicGate"), params.getElectronicGate()));
        }

        if (params.getServiceArea()) {
            predicates.add(criteriaBuilder.equal(root.get("serviceArea"), params.getServiceArea()));
        }

        if (params.getPub()) {
            predicates.add(criteriaBuilder.equal(root.get("pub"), params.getPub()));
        }

        if (params.getCloset()) {
            predicates.add(criteriaBuilder.equal(root.get("closet"), params.getCloset()));
        }

        if (params.getOffice()) {
            predicates.add(criteriaBuilder.equal(root.get("office"), params.getOffice()));
        }

        if (params.getYard()) {
            predicates.add(criteriaBuilder.equal(root.get("yard"), params.getYard()));
        }

        if (params.getAlarmSystem()) {
            predicates.add(criteriaBuilder.equal(root.get("alarmSystem"), params.getAlarmSystem()));
        }

        if (params.getBalcony()) {
            predicates.add(criteriaBuilder.equal(root.get("balcony"), params.getBalcony()));
        }

        if (params.getConcierge24Hour()) {
            predicates.add(criteriaBuilder.equal(root.get("concierge24Hour"), params.getConcierge24Hour()));
        }

        if (params.getWalledArea()) {
            predicates.add(criteriaBuilder.equal(root.get("walledArea"), params.getWalledArea()));
        }

        if (params.getDogAllowed()) {
            predicates.add(criteriaBuilder.equal(root.get("dogAllowed"), params.getDogAllowed()));
        }

        if (params.getCatAllowed()) {
            predicates.add(criteriaBuilder.equal(root.get("catAllowed"), params.getCatAllowed()));
        }

        if (params.getCameras()) {
            predicates.add(criteriaBuilder.equal(root.get("cameras"), params.getCameras()));
        }

        if (params.getFurnished()) {
            predicates.add(criteriaBuilder.equal(root.get("furnished"), params.getFurnished()));
        }

        if (params.getSeaView()) {
            predicates.add(criteriaBuilder.equal(root.get("seaView"), params.getSeaView()));
        }

        if (params.getGatedCommunity()) {
            predicates.add(criteriaBuilder.equal(root.get("gatedCommunity"), params.getGatedCommunity()));
        }

        int offset = (params.getPageNumber() - 1) * params.getPageSize();

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Immobile> query = entityManager.createQuery(criteriaQuery);

        query.setFirstResult(offset);
        query.setMaxResults(params.getPageSize());

        return query.getResultList();
    }
}
