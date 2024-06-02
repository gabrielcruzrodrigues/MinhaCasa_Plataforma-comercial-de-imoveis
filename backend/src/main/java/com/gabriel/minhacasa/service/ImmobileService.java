package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.CreateImmobileDTO;
import com.gabriel.minhacasa.domain.DTO.SearchParamsDTO;
import com.gabriel.minhacasa.domain.DTO.UpdateImmobileDTO;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.RoleEnum;
import com.gabriel.minhacasa.domain.enums.TypeEnum;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ImmobileNotFoundException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.UserNotFoundException;
import com.gabriel.minhacasa.files.FilesService;
import com.gabriel.minhacasa.repository.ImmobileRepository;
import com.gabriel.minhacasa.repository.ImmobileRepositoryImpl;
import com.gabriel.minhacasa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ImmobileService {

    @Value("${base-url}")
    private String baseUrl;
    private String baseUrlImmobileFilesApi = "/api/files/download/immobile/";

    private final ImmobileRepository immobileRepository;
    private final UserRepository userRepository;
    private final FilesService filesService;
    private final ImmobileRepositoryImpl immobileRepositorySearch;

    public void createImmobile(CreateImmobileDTO immobileData) {
        Optional<User> user = userRepository.findById(immobileData.studentId());
        if (user.isPresent()) {
            Immobile immobile = Immobile.builder()
                    .name(immobileData.name())
                    .description(immobileData.description())
                    .address(immobileData.address())
                    .city(immobileData.city())
                    .neighborhood(immobileData.neighborhood())
                    .state(immobileData.state())
                    .garage(immobileData.garage())
                    .quantityBedrooms(immobileData.quantityBedrooms())
                    .quantityRooms(immobileData.quantityRooms())
                    .IPTU(immobileData.IPTU())
                    .price(immobileData.price())
                    .suite(immobileData.suite())
                    .totalArea(immobileData.totalArea())
                    .quantityBathrooms(immobileData.quantityBathrooms())
                    .integrity(immobileData.integrity())
                    .sellerType(immobileData.sellerType())
                    .age(immobileData.age())
                    .category(immobileData.category())
                    .createdAt(LocalDateTime.now())
                    .type(immobileData.type())
                    .active(true)
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

            if (immobileData.files() != null) {
                immobile.setFiles(this.filesService.uploadImmobileFile(immobileData.files(), immobile));
            }

            this.immobileRepository.save(immobile);
            this.setRoleOWNERByUser(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    public Immobile getImmobileWithCompleteImagesPath(Long id) {
        Immobile immobile = this.findById(id);
        List<String> fullImagePaths = new ArrayList<>();

        for (String path : immobile.getFiles()) {
            fullImagePaths.add(this.baseUrl + this.baseUrlImmobileFilesApi + path);
        }

        immobile.setFiles(fullImagePaths);
        return immobile;
    }

    private void setRoleOWNERByUser(User user) {
        user.setRole(Set.of(RoleEnum.USER.toString(), RoleEnum.OWNER.toString()));
        this.userRepository.save(user);
    }

    public Immobile findById(Long id) {
        Optional<Immobile> immobile = this.immobileRepository.findById(id);
        return immobile.orElseThrow(ImmobileNotFoundException::new);
    }

    public void updateImmobile(UpdateImmobileDTO immobileData) {
        Immobile immobile = Immobile.builder()
                .name(immobileData.name())
                .description(immobileData.description())
                .address(immobileData.address())
                .city(immobileData.city())
                .neighborhood(immobileData.neighborhood())
                .state(immobileData.state())
                .garage(immobileData.garage())
                .quantityBedrooms(immobileData.quantityBedrooms())
                .quantityRooms(immobileData.quantityRooms())
                .IPTU(immobileData.IPTU())
                .price(immobileData.price())
                .suite(immobileData.suite())
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
    public void disableImmobile(Long id) {
        Immobile immobile = this.findById(id);
        immobile.setActive(false);
        this.immobileRepository.save(immobile);
    }

    @Transactional
    public void soldImmobile(Long id) {
        Immobile immobile = this.findById(id);
        this.disableImmobile(id);
//        this.filesImmobileService.soldImmobile(immobile);
    }

    public List<Immobile> searchParams(SearchParamsDTO params) {
        return this.immobileRepositorySearch.searchByParams(params);
    }
}
