package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.CreateImmobileDTO;
import com.gabriel.minhacasa.domain.DTO.ImmobileByProfileDTO;
import com.gabriel.minhacasa.domain.DTO.ImmobileWithSellerIdDTO;
import com.gabriel.minhacasa.domain.DTO.SearchParamsDTO;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.*;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ImmobileNotFoundException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.UserNotFoundException;
import com.gabriel.minhacasa.files.FilesService;
import com.gabriel.minhacasa.repository.ImmobileRepository;
import com.gabriel.minhacasa.repository.ImmobileRepositoryImpl;
import com.gabriel.minhacasa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class ImmobileServiceTest {

    @Value("${base-url}")
    private String baseUrl;
    @Value("${base-url-immobile-files-api}")
    private String baseUrlImmobileFilesApi;

    @Mock
    private ImmobileRepository immobileRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FilesService filesService;
    @Mock
    private ImmobileRepositoryImpl immobileRepositorySearch;
    @InjectMocks
    private ImmobileService immobileService;

    private Immobile immobile;
    private User user;
    private CreateImmobileDTO createImmobileDTO;
    MockMultipartFile imageFile = new MockMultipartFile("imageProfile", "teste.png", "image/jpeg", "dummy image content".getBytes());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startImmobiles();
    }

    @Test
    @DisplayName("must create a new immobile using a CreateImmobileDTO")
    void createImmobile_whenToReceiveACreateImmobileDTO_mustCreateANewImmobile() {
        List<MultipartFile> files = List.of(this.imageFile);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(this.user));
        when(filesService.uploadImmobileFile(files, this.immobile)).thenReturn(List.of("ImageProfile"));
        when(immobileRepository.save(any(Immobile.class))).thenReturn(this.immobile);

        this.immobileService.createImmobile(this.createImmobileDTO);

        verify(userRepository, times(1)).findById(anyLong());
        verify(immobileRepository, times(1)).save(any(Immobile.class));
    }

    @Test
    @DisplayName("must return a UserNotFoundException if user not found.")
    void createImmobile_ifUserNotFound_mustReturnAUserNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserNotFoundException response = assertThrows(UserNotFoundException.class, () -> {
            this.immobileService.createImmobile(this.createImmobileDTO);
        });

        assertNotNull(response);
        assertEquals(UserNotFoundException.class, response.getClass());
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("must return a immobile with complete image path")
    void getImmobileWithCompleteImagesPath_whenToCall_mustReturnAImmobileWithCompleteImagePath() {
        when(immobileRepository.findById(anyLong())).thenReturn(Optional.of(this.immobile));

        ImmobileWithSellerIdDTO response = this.immobileService.getImmobileWithCompleteImagesPath(1L);

        assertNotNull(response);
        assertEquals(this.immobile.getFiles().get(0), response.immobile().getFiles().get(0));
        verify(immobileRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("must return a immobile with success")
    void findById_whenToCall_mustReturnAImmobileWithSuccess() {
        when(this.immobileRepository.findById(anyLong())).thenReturn(Optional.of(this.immobile));

        Immobile response = this.immobileService.findById(1L);

        assertNotNull(response);
        assertEquals(response.getClass(), Immobile.class);
        verify(immobileRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("must return a immobileNotFoundException when immobile to be empty")
    void findById_whenImmobileToBeEmpty_mustReturnAImmobileNotFoundException() {
        when(this.immobileRepository.findById(anyLong())).thenReturn(Optional.empty());

        ImmobileNotFoundException response = assertThrows(ImmobileNotFoundException.class, () -> {
           this.immobileService.findById(1L);
        });

        assertNotNull(response);
        assertEquals(ImmobileNotFoundException.class, response.getClass());
    }

    @Test
    void updateImmobile() {
    }

    @Test
    @DisplayName("must disabled of the immobile when to call")
    void disableImmobile_whenToCall_mustDisabledOfTheImmobile() {
        when(this.immobileRepository.findById(anyLong())).thenReturn(Optional.of(this.immobile));

        this.immobileService.disableImmobile(1L);

        verify(this.immobileRepository, times(1)).findById(anyLong());
        verify(this.immobileRepository, times(1)).save(any(Immobile.class));
    }

    @Test
    @DisplayName("must delete images and disabled immobile")
    void soldImmobile_whenToCall_mustDeleteImagesAndDisabledImmobile() {
        when(this.immobileRepository.findById(anyLong())).thenReturn(Optional.of(this.immobile));

        this.immobileService.soldImmobile(1L);

        assertFalse(this.immobile.isActive());
        assertEquals(1, this.immobile.getFiles().size());
    }

    @Test
    @DisplayName("must return a immobile by params with complete image path")
    void findImmobileByParamsWithCompleteImagePath_whenReceiveParams_mustSearchAndReturnAImmobileWithCompleteImagePath() {
        when(this.immobileRepositorySearch.searchByParams(any(SearchParamsDTO.class))).thenReturn(List.of(this.immobile));
        String path = this.baseUrl + this.baseUrlImmobileFilesApi;

        List<ImmobileByProfileDTO> response = this.immobileService.findImmobileByParamsWithCompleteImagePath(new SearchParamsDTO());

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(path + this.immobile.getFiles().get(0), response.get(0).imageUrl());
    }

    void startImmobiles() {
        this.user = User.builder()
                .id(1L)
                .build();

        this.immobile = Immobile.builder()
                .name("Casa de Praia")
                .description("Uma bela casa de praia com vista para o mar.")
                .address("Rua das Flores, 123")
                .city("Florianópolis")
                .neighborhood("Jurerê Internacional")
                .state("Santa Catarina")
                .garage(true)
                .quantityBedrooms(4)
                .quantityRooms(6)
                .IPTU(new BigDecimal("1200.00"))
                .price(new BigDecimal("850000.00"))
                .suite(true)
                .usefulArea(200.0)
                .totalArea(300.0)
                .quantityBathrooms(3)
                .integrity(IntegrityEnum.NEW)
                .sellerType(SellerTypeEnum.OWNER)
                .age(AgeEnum.UP_TO_1_YEARS)
                .category(CategoryEnum.SELL)
                .createdAt(LocalDateTime.now())
                .type(TypeEnum.HOUSE)
                .garden(true)
                .virtualTour(true)
                .videos(false)
                .beach(true)
                .disabledAccess(true)
                .playground(true)
                .grill(true)
                .energyGenerator(false)
                .closeToTheCenter(true)
                .elevator(false)
                .pool(true)
                .frontDesk(true)
                .multiSportsCourt(false)
                .gym(true)
                .steamRoom(false)
                .cableTV(true)
                .heating(true)
                .cabinetsInTheKitchen(true)
                .bathroomInTheRoom(true)
                .internet(true)
                .partyRoom(true)
                .airConditioning(true)
                .americanKitchen(true)
                .hydromassage(true)
                .fireplace(true)
                .privatePool(false)
                .electronicGate(true)
                .serviceArea(true)
                .pub(false)
                .closet(true)
                .office(true)
                .yard(true)
                .alarmSystem(true)
                .balcony(true)
                .concierge24Hour(true)
                .walledArea(true)
                .dogAllowed(true)
                .catAllowed(true)
                .cameras(true)
                .furnished(true)
                .seaView(true)
                .gatedCommunity(true)
                .active(true)
                .user(this.user)
                .favoriteUser(List.of())
                .files(List.of("teste.jpg"))
                .build();

        List<MultipartFile> files = List.of();

        this.createImmobileDTO = new CreateImmobileDTO(
                1L,
                "Casa de Praia",
                "Uma bela casa de praia com vista para o mar.",
                "Rua das Flores, 123",
                "Florianópolis",
                "Jurerê Internacional",
                "Santa Catarina",
                true,
                4,
                6,
                new BigDecimal("1200.00"),
                new BigDecimal("850000.00"),
                true,
                300.0,
                3,
                IntegrityEnum.NEW,
                SellerTypeEnum.OWNER,
                AgeEnum.UP_TO_1_YEARS,
                CategoryEnum.SELL,
                TypeEnum.HOUSE,
                true,
                false,
                true,
                true,
                true,
                true,
                false,
                true,
                false,
                true,
                true,
                false,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                files
        );
    }
}