package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.*;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.RoleEnum;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ErrorForDeleteFileException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ImmobileNotFoundException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.UserNotFoundException;
import com.gabriel.minhacasa.files.FilesService;
import com.gabriel.minhacasa.repository.ImmobileRepository;
import com.gabriel.minhacasa.repository.ImmobileRepositoryImpl;
import com.gabriel.minhacasa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImmobileService {

    //change for ambient variables
    @Value("${base-url}")
    private String baseUrl;
    @Value("${base-url-immobile-files-api}")
    private String baseUrlImmobileFilesApi;
    @Value("{file.upload-image-profile-dir}")
    private String rootPath;

    private final ImmobileRepository immobileRepository;
    private final UserRepository userRepository;
    private final FilesService filesService;
    private final ImmobileRepositoryImpl immobileRepositorySearch;

    public void createImmobile(CreateImmobileDTO immobileData) {
        Optional<User> user = userRepository.findById(immobileData.studentId());
        if (user.isPresent()) {
            Immobile immobile = Immobile.builder()
                    .name(immobileData.immobileTitle())
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
                    .usefulArea(immobileData.totalArea())
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

    public ImmobileWithSellerIdDTO getImmobileWithCompleteImagesPath(Long id) {
        Immobile immobile = this.findById(id);
        List<String> fullImagePaths = new ArrayList<>();

        for (String path : immobile.getFiles()) {
            fullImagePaths.add(this.baseUrl + this.baseUrlImmobileFilesApi + path);
        }

        immobile.setFiles(fullImagePaths);
        return new ImmobileWithSellerIdDTO(immobile, immobile.getUser().getId());
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
                .name(immobileData.immobileTitle())
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

    public void soldImmobile(Long id) {
        Immobile immobile = this.findById(id);
        String firstImage = immobile.getFiles().get(0);

        List<String> imagesForDelete = new ArrayList<>();
        if (immobile.getFiles().size() > 1) {
            for (int i = 1; i < immobile.getFiles().size(); i++) {
                imagesForDelete.add(immobile.getFiles().get(i));
            }
        }
        this.deleteImages(imagesForDelete);

        List<String> newFiles = new ArrayList<>();
        newFiles.add(firstImage);
        immobile.setFiles(newFiles);

        this.disableImmobile(id);
    }

    private void deleteImages(List<String> images) {
        for (String image : images) {
            Path path = Paths.get(this.rootPath + image);

            try {
                Files.delete(path);
            } catch (IOException ex) {
                throw new ErrorForDeleteFileException("Error for delete ImmobileFile");
            }
        }
    }

    public List<ImmobileByCardsDTO> findImmobileByParamsWithCompleteImagePath(SearchParamsDTO params) {
        log.info("get data in findImmobileByParamsWithCompleteImagePath: " + params);
        List<Immobile> immobiles = this.immobileRepositorySearch.searchByParams(params);
        List<ImmobileByCardsDTO> immobilesWithCompletePath = new ArrayList<>();

        for (Immobile immobile : immobiles) {
            String fullImagePath;

            String path = immobile.getFiles().get(0);
            fullImagePath = this.baseUrl + this.baseUrlImmobileFilesApi + path;

            ImmobileByCardsDTO immobileByCardsDTO = new ImmobileByCardsDTO(
                    immobile.getId(), immobile.getQuantityRooms(), immobile.getQuantityBedrooms(), immobile.getQuantityBathrooms(),
                    fullImagePath, immobile.getPrice(), immobile.getName(), immobile.getDescription(), immobile.getUser().getId()
            );

            immobilesWithCompletePath.add(immobileByCardsDTO);
        }


        return immobilesWithCompletePath;
    }

    public List<ImmobileByCardsDTO> find4RandomImmobilesForHome() {
        List<ImmobileByCardsDTO> immobilesWithFullPathImages = new ArrayList<>();
        List<Immobile> immobiles = this.immobileRepository.find4RandomProducts();
        for (Immobile immobile : immobiles) {
            String fullImagePath;

            String path = immobile.getFiles().get(0);
            fullImagePath = this.baseUrl + this.baseUrlImmobileFilesApi + path;

            ImmobileByCardsDTO immobileByCardsDTO = new ImmobileByCardsDTO(
                immobile.getId(), immobile.getQuantityRooms(), immobile.getQuantityBedrooms(), immobile.getQuantityBathrooms(),
                fullImagePath, immobile.getPrice(), immobile.getName(), immobile.getDescription(), immobile.getUser().getId()
            );

            immobilesWithFullPathImages.add(immobileByCardsDTO);
        }

        return immobilesWithFullPathImages;
    }

    private List<ImmobileByCardsDTO> createImmobileByCard(List<Immobile> immobiles) {
        List<ImmobileByCardsDTO> immobilesWithFullPathImages = new ArrayList<>();

        for (Immobile immobile : immobiles) {
            String fullImagePath;

            String path = immobile.getFiles().get(0);
            fullImagePath = this.baseUrl + this.baseUrlImmobileFilesApi + path;

            ImmobileByCardsDTO immobileByCardsDTO = new ImmobileByCardsDTO(
                    immobile.getId(), immobile.getQuantityRooms(), immobile.getQuantityBedrooms(), immobile.getQuantityBathrooms(),
                    fullImagePath, immobile.getPrice(), immobile.getName(), immobile.getDescription(), immobile.getUser().getId()
            );

            immobilesWithFullPathImages.add(immobileByCardsDTO);
        }
        return immobilesWithFullPathImages;
    }

    public List<Long> searchForUserFavoritesImmobilesId(Long id) {
        return this.immobileRepository.findFavoritedImmobilesIdOfUser(id);
    }

    public List<ImmobileByCardsDTO> searchForUserFavoritesImmobiles(Long id) {
        List<Long> favoritesImmobilesId = this.searchForUserFavoritesImmobilesId(id);
        System.out.println(favoritesImmobilesId);
        List<Immobile> immobiles = new ArrayList<>();

        for (Long immobileId : favoritesImmobilesId) {
            immobiles.add(this.findById(immobileId));
        }

        return this.createImmobileByCard(immobiles);
    }
}
