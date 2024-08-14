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
    @Value("${file.upload-image-immobile-dir}")
    private String immobileDirBasePath;

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

    public ImmobileWithSellerIdDTO getImmobileWithSellerId(Long id) {
        Immobile immobile = this.findByIdWithCompletePath(id);
        return new ImmobileWithSellerIdDTO(immobile, immobile.getUser().getId());
    }

    private void setRoleOWNERByUser(User user) {
        user.setRole(Set.of(RoleEnum.USER.toString(), RoleEnum.OWNER.toString()));
        this.userRepository.save(user);
    }

    public Immobile findByIdWithCompletePath(Long id) {
        Immobile immobile = this.findById(id);
        List<String> imagesReference = immobile.getFiles();
        List<String> fullPaths = new ArrayList<>();

        for (String imageReference : imagesReference) {
            fullPaths.add(this.baseUrl + this.baseUrlImmobileFilesApi + imageReference);
        }

        immobile.setFiles(fullPaths);

        return immobile;
    }

    public Immobile findById(Long id) {
        Optional<Immobile> immobile = this.immobileRepository.findById(id);
        return immobile.orElseThrow(ImmobileNotFoundException::new);
    }

    //missing testes
    public void updateImmobile(UpdateImmobileDTO immobileData) {
        Immobile immobile = this.findById(immobileData.getImmobileId());

        immobile.setName(immobileData.getImmobileTitle());
        immobile.setDescription(immobileData.getDescription());
        immobile.setAddress(immobileData.getAddress());
        immobile.setCity(immobileData.getCity());
        immobile.setNeighborhood(immobileData.getNeighborhood());
        immobile.setState(immobileData.getState());
        immobile.setGarage(immobileData.isGarage());
        immobile.setQuantityBedrooms(immobileData.getQuantityBedrooms());
        immobile.setQuantityRooms(immobileData.getQuantityRooms());
        immobile.setIPTU(immobileData.getIPTU());
        immobile.setPrice(immobileData.getPrice());
        immobile.setSuite(immobileData.isSuite());
        immobile.setTotalArea(immobileData.getTotalArea());
        immobile.setQuantityBathrooms(immobileData.getQuantityBathrooms());
        immobile.setIntegrity(immobileData.getIntegrity());
        immobile.setSellerType(immobileData.getSellerType());
        immobile.setAge(immobileData.getAge());
        immobile.setCategory(immobileData.getCategory());
        immobile.setType(immobileData.getType());
        immobile.setGarden(immobileData.isGarden());
        immobile.setBeach(immobileData.isBeach());
        immobile.setDisabledAccess(immobileData.isDisabledAccess());
        immobile.setPlayground(immobileData.isPlayground());
        immobile.setGrill(immobileData.isGrill());
        immobile.setEnergyGenerator(immobileData.isEnergyGenerator());
        immobile.setCloseToTheCenter(immobileData.isCloseToTheCenter());
        immobile.setElevator(immobileData.isElevator());
        immobile.setPool(immobileData.isPool());
        immobile.setFrontDesk(immobileData.isFrontDesk());
        immobile.setMultiSportsCourt(immobileData.isMultiSportsCourt());
        immobile.setGym(immobileData.isGym());
        immobile.setSteamRoom(immobileData.isSteamRoom());
        immobile.setCableTV(immobileData.isCableTV());
        immobile.setHeating(immobileData.isHeating());
        immobile.setCabinetsInTheKitchen(immobileData.isCabinetsInTheKitchen());
        immobile.setBathroomInTheRoom(immobileData.isBathroomInTheRoom());
        immobile.setInternet(immobileData.isInternet());
        immobile.setPartyRoom(immobileData.isPartyRoom());
        immobile.setAirConditioning(immobileData.isAirConditioning());
        immobile.setAmericanKitchen(immobileData.isAmericanKitchen());
        immobile.setHydromassage(immobileData.isHydromassage());
        immobile.setFireplace(immobileData.isFireplace());
        immobile.setPrivatePool(immobileData.isPrivatePool());
        immobile.setElectronicGate(immobileData.isElectronicGate());
        immobile.setServiceArea(immobileData.isServiceArea());
        immobile.setPub(immobileData.isPub());
        immobile.setCloset(immobileData.isCloset());
        immobile.setOffice(immobileData.isOffice());
        immobile.setYard(immobileData.isYard());
        immobile.setAlarmSystem(immobileData.isAlarmSystem());
        immobile.setBalcony(immobileData.isBalcony());
        immobile.setConcierge24Hour(immobileData.isConcierge24Hour());
        immobile.setWalledArea(immobileData.isWalledArea());
        immobile.setDogAllowed(immobileData.isDogAllowed());
        immobile.setCatAllowed(immobileData.isCatAllowed());
        immobile.setCameras(immobileData.isCameras());
        immobile.setFurnished(immobileData.isFurnished());
        immobile.setSeaView(immobileData.isSeaView());
        immobile.setGatedCommunity(immobileData.isGatedCommunity());

        this.deleteImages(immobile.getFiles());
        List<String> filesReference = this.filesService.uploadImmobileFile(immobileData.getFiles(), immobile);
        immobile.setFiles(filesReference);

        this.immobileRepository.save(immobile);
    }

    @Transactional
    public void disableImmobile(Long id) {
        Immobile immobile = this.findByIdWithCompletePath(id);
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
            Path path = Paths.get(this.immobileDirBasePath + image);
            try {
                Files.delete(path);
            } catch (Exception ex) {
                throw new ErrorForDeleteFileException(ex.getMessage());
            }
        }
    }

    public List<ImmobileByCardsDTO> findImmobileByParamsWithCompleteImagePath(SearchParamsDTO params) {
        log.info("get data in findImmobileByParamsWithCompleteImagePath: " + params);
        List<Immobile> immobiles = this.immobileRepositorySearch.searchByParams(params);
        return this.createImmobileByCard(immobiles);
    }

    public List<ImmobileByCardsDTO> find4RandomImmobilesForHome() {
        List<Immobile> immobiles = this.immobileRepository.find4RandomProducts();
        return this.createImmobileByCard(immobiles);
    }

    public List<ImmobileByCardsDTO> searchForUserFavoritesImmobiles(Long id) {
        List<Long> favoritesImmobilesId = this.searchForUserFavoritesImmobilesId(id);
        List<Immobile> immobiles = new ArrayList<>();

        for (Long immobileId : favoritesImmobilesId) {
            immobiles.add(this.findByIdWithCompletePath(immobileId));
        }

        return this.createImmobileByCard(immobiles);
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
}
