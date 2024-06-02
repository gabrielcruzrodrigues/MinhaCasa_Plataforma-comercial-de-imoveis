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

        if (params.getName().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("name"), params.getName().get()));
        }

        if (params.getCity().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("city"), params.getCity().get()));
        }

        if (params.getNeighborhood().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("neighborhood"), params.getNeighborhood().get()));
        }

        if (params.getState().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("state"), params.getState().get()));
        }

        if (params.getGarage().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("garage"), params.getGarage().get()));
        }

        if (params.getQuantityBedrooms().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("quantityBedrooms"), params.getQuantityBedrooms().get()));
        }

        if (params.getQuantityRooms().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("quantityRooms"), params.getQuantityRooms().get()));
        }

        if (params.getIptu().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("iptu"), params.getIptu().get()));
        }

        if (params.getPrice().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("price"), params.getPrice().get()));
        }

        if (params.getSuite().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("suite"), params.getSuite().get()));
        }

        if (params.getTotalArea().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("totalArea"), params.getTotalArea().get()));
        }

        if (params.getQuantityBathrooms().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("quantityBathrooms"), params.getQuantityBathrooms().get()));
        }

        if (params.getIntegrity().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("integrity"), params.getIntegrity().get()));
        }

        if (params.getSellerType().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("sellerType"), params.getSellerType().get()));
        }

        if (params.getAge().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("age"), params.getAge().get()));
        }

        if (params.getCategory().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("category"), params.getCategory().get()));
        }

        if (params.getType().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("type"), params.getType().get()));
        }

        if (params.getGarden().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("garden"), params.getGarden().get()));
        }

        if (params.getVirtualTour().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("virtualTour"), params.getVirtualTour().get()));
        }

        if (params.getVideos().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("videos"), params.getVideos().get()));
        }

        if (params.getBeach().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("beach"), params.getBeach().get()));
        }

        if (params.getDisabledAccess().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("disabledAccess"), params.getDisabledAccess().get()));
        }

        if (params.getPlayground().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("playground"), params.getPlayground().get()));
        }

        if (params.getGrill().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("grill"), params.getGrill().get()));
        }

        if (params.getEnergyGenerator().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("energyGenerator"), params.getEnergyGenerator().get()));
        }

        if (params.getCloseToTheCenter().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("closeToTheCenter"), params.getCloseToTheCenter().get()));
        }

        if (params.getElevator().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("elevator"), params.getElevator().get()));
        }

        if (params.getPool().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("pool"), params.getPool().get()));
        }

        if (params.getFrontDesk().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("frontDesk"), params.getFrontDesk().get()));
        }

        if (params.getMultiSportsCourt().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("multiSportsCourt"), params.getMultiSportsCourt().get()));
        }

        if (params.getGym().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("gym"), params.getGym().get()));
        }

        if (params.getSteamRoom().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("steamRoom"), params.getSteamRoom().get()));
        }

        if (params.getCableTV().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("cableTV"), params.getCableTV().get()));
        }

        if (params.getHeating().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("heating"), params.getCableTV().get()));
        }

        if (params.getCabinetsInTheKitchen().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("cabinetsInTheKitchen"), params.getCabinetsInTheKitchen().get()));
        }

        if (params.getBathroomInTheRoom().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("bathroomInTheRoom"), params.getBathroomInTheRoom().get()));
        }

        if (params.getInternet().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("internet"), params.getInternet().get()));
        }

        if (params.getPartyRoom().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("partyRoom"), params.getPartyRoom().get()));
        }

        if (params.getAirConditioning().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("airConditioning"), params.getAirConditioning().get()));
        }

        if (params.getAmericanKitchen().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("americanKitchen"), params.getAmericanKitchen().get()));
        }

        if (params.getHydromassage().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("hydromassage"), params.getHydromassage().get()));
        }

        if (params.getFireplace().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("fireplace"), params.getFireplace().get()));
        }

        if (params.getPrivatePool().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("privatePool"), params.getPrivatePool().get()));
        }

        if (params.getElectronicGate().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("electronicGate"), params.getElectronicGate().get()));
        }

        if (params.getServiceArea().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("serviceArea"), params.getServiceArea().get()));
        }

        if (params.getPub().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("pub"), params.getPub().get()));
        }

        if (params.getCloset().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("closet"), params.getCloset().get()));
        }

        if (params.getOffice().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("office"), params.getOffice().get()));
        }

        if (params.getYard().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("yard"), params.getYard().get()));
        }

        if (params.getAlarmSystem().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("alarmSystem"), params.getAlarmSystem().get()));
        }

        if (params.getBalcony().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("balcony"), params.getBalcony().get()));
        }

        if (params.getConcierge24Hour().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("concierge24Hour"), params.getConcierge24Hour().get()));
        }

        if (params.getWalledArea().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("walledArea"), params.getWalledArea().get()));
        }

        if (params.getDogAllowed().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("dogAllowed"), params.getDogAllowed().get()));
        }

        if (params.getCatAllowed().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("catAllowed"), params.getCatAllowed().get()));
        }

        if (params.getCameras().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("cameras"), params.getCameras().get()));
        }

        if (params.getFurnished().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("furnished"), params.getFurnished().get()));
        }

        if (params.getSeaView().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("seaView"), params.getSeaView().get()));
        }

        if (params.getGatedCommunity().isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("gatedCommunity"), params.getGatedCommunity().get()));
        }

        int offset = (params.getPageNumber().get() - 1) * params.getPageSize().get();

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        // Create the query from criteriaQuery
        TypedQuery<Immobile> query = entityManager.createQuery(criteriaQuery);

        // Set pagination parameters
        query.setFirstResult(offset);
        query.setMaxResults(params.getPageSize().get());

        return query.getResultList();
    }
}
