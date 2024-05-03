package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.CreateImmobileDTO;
import com.gabriel.minhacasa.domain.DTO.UpdateImmobileDTO;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.exceptions.ErrorForDeleteImmobileException;
import com.gabriel.minhacasa.exceptions.ImmobileNotFoundException;
import com.gabriel.minhacasa.exceptions.UserNotFoundException;
import com.gabriel.minhacasa.repository.ImmobileRepository;
import com.gabriel.minhacasa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImmobileService {

    private final ImmobileRepository immobileRepository;
    private final UserRepository userRepository;

    public void createImmobile(CreateImmobileDTO immobileData) {
        Optional<User> user = userRepository.findById(immobileData.studentId());
        if (user.isPresent()) {
            Immobile immobile = Immobile.builder()
                    .name(immobileData.name())
                    .address(immobileData.address())
                    .neighborhood(immobileData.neighborhood())
                    .state(immobileData.state())
                    .garage(immobileData.garage())
                    .quantityBedrooms(immobileData.quantityBedrooms())
                    .quantityRooms(immobileData.quantityRooms())
                    .IPTU(immobileData.IPTU())
                    .price(immobileData.price())
                    .suite(immobileData.suite())
                    .usefulArea(immobileData.usefulArea())
                    .totalArea(immobileData.totalArea())
                    .quantityBathrooms(immobileData.quantityBathrooms())
                    .integrity(immobileData.integrity())
                    .sellerType(immobileData.sellerType())
                    .age(immobileData.age())
                    .category(immobileData.category())
                    .createdAt(LocalDateTime.now())
                    .type(immobileData.type())
                    .garden(immobileData.garden())
                    .virtualTour(false)
                    .videos(false)
                    .beach(immobileData.beach())
                    .disabledAccess(immobileData.disabledAccess())
                    .playground(immobileData.playground())
                    .grill(immobileData.grill())
                    .energyGenerator(immobileData.energyGenerator())
                    .closeToTheCenter(immobileData.closeToTheCenter())
                    .elevator(immobileData.elevator())
                    .pool(immobileData.pool())
                    .frontDesk(immobileData.frontDesk())
                    .multiSportsCourt(immobileData.multiSportsCourt())
                    .gym(immobileData.gym())
                    .steamRoom(immobileData.steamRoom())
                    .cableTV(immobileData.cableTV())
                    .heating(immobileData.heating())
                    .cabinetsInTheKitchen(immobileData.cabinetsInTheKitchen())
                    .bathroomInTheRoom(immobileData.bathroomInTheRoom())
                    .internet(immobileData.internet())
                    .partyRoom(immobileData.partyRoom())
                    .airConditioning(immobileData.airConditioning())
                    .americanKitchen(immobileData.americanKitchen())
                    .hydromassage(immobileData.hydromassage())
                    .fireplace(immobileData.fireplace())
                    .privatePool(immobileData.privatePool())
                    .electronicGate(immobileData.electronicGate())
                    .serviceArea(immobileData.serviceArea())
                    .pub(immobileData.pub())
                    .closet(immobileData.closet())
                    .office(immobileData.office())
                    .yard(immobileData.yard())
                    .alarmSystem(immobileData.alarmSystem())
                    .balcony(immobileData.balcony())
                    .concierge24Hour(immobileData.concierge24Hour())
                    .walledArea(immobileData.walledArea())
                    .dogAllowed(immobileData.dogAllowed())
                    .catAllowed(immobileData.catAllowed())
                    .cameras(immobileData.cameras())
                    .furnished(immobileData.furnished())
                    .seaView(immobileData.seaView())
                    .gatedCommunity(immobileData.gatedCommunity())
                    .user(user.get())
                    .build();

            this.immobileRepository.save(immobile);
        } else {
            throw new UserNotFoundException();
        }
    }

    public Immobile findById(Long id) {
        Optional<Immobile> immobile = this.immobileRepository.findById(id);
        return immobile.orElseThrow(ImmobileNotFoundException::new);
    }

    public void updateImmobile(UpdateImmobileDTO immobileData) {
        Immobile immobile = Immobile.builder()
                .name(immobileData.name())
                .address(immobileData.address())
                .neighborhood(immobileData.neighborhood())
                .state(immobileData.state())
                .garage(immobileData.garage())
                .quantityBedrooms(immobileData.quantityBedrooms())
                .quantityRooms(immobileData.quantityRooms())
                .IPTU(immobileData.IPTU())
                .price(immobileData.price())
                .suite(immobileData.suite())
                .usefulArea(immobileData.usefulArea())
                .totalArea(immobileData.totalArea())
                .quantityBathrooms(immobileData.quantityBathrooms())
                .integrity(immobileData.integrity())
                .sellerType(immobileData.sellerType())
                .age(immobileData.age())
                .category(immobileData.category())
                .type(immobileData.type())
                .garden(immobileData.garden())
                .beach(immobileData.beach())
                .disabledAccess(immobileData.disabledAccess())
                .playground(immobileData.playground())
                .grill(immobileData.grill())
                .energyGenerator(immobileData.energyGenerator())
                .closeToTheCenter(immobileData.closeToTheCenter())
                .elevator(immobileData.elevator())
                .pool(immobileData.pool())
                .frontDesk(immobileData.frontDesk())
                .multiSportsCourt(immobileData.multiSportsCourt())
                .gym(immobileData.gym())
                .steamRoom(immobileData.steamRoom())
                .cableTV(immobileData.cableTV())
                .heating(immobileData.heating())
                .cabinetsInTheKitchen(immobileData.cabinetsInTheKitchen())
                .bathroomInTheRoom(immobileData.bathroomInTheRoom())
                .internet(immobileData.internet())
                .partyRoom(immobileData.partyRoom())
                .airConditioning(immobileData.airConditioning())
                .americanKitchen(immobileData.americanKitchen())
                .hydromassage(immobileData.hydromassage())
                .fireplace(immobileData.fireplace())
                .privatePool(immobileData.privatePool())
                .electronicGate(immobileData.electronicGate())
                .serviceArea(immobileData.serviceArea())
                .pub(immobileData.pub())
                .closet(immobileData.closet())
                .office(immobileData.office())
                .yard(immobileData.yard())
                .alarmSystem(immobileData.alarmSystem())
                .balcony(immobileData.balcony())
                .concierge24Hour(immobileData.concierge24Hour())
                .walledArea(immobileData.walledArea())
                .dogAllowed(immobileData.dogAllowed())
                .catAllowed(immobileData.catAllowed())
                .cameras(immobileData.cameras())
                .furnished(immobileData.furnished())
                .seaView(immobileData.seaView())
                .gatedCommunity(immobileData.gatedCommunity())
                .build();

        this.immobileRepository.save(immobile);
    }

    @Transactional
    public void deleteImmobile(Long id) {
        Immobile immobile = this.findById(id);
        try {
            this.immobileRepository.delete(immobile);
        } catch (Exception ex) {
            throw new ErrorForDeleteImmobileException(ex.getMessage());
        }
    }
}
