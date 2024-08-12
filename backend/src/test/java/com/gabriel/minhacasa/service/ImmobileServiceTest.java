package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.CreateImmobileDTO;
import com.gabriel.minhacasa.domain.DTO.ImmobileByCardsDTO;
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

    public static final BigDecimal PRICE = new BigDecimal("850000.00");
    public static final IntegrityEnum INTEGRITY_ENUM1 = IntegrityEnum.NEW;
    @Value("${base-url}")
    private String baseUrl;
    @Value("${base-url-immobile-files-api}")
    private String baseUrlImmobileFilesApi;

    public static final Long IMMOBILE_ID = 1L;
    public static final String IMMOBILE_NAME = "Casa para alugar";
    public static final String IMMOBILE_DESCRIPTION = "description";
    public static final String IMMOBILE_ADDRESS = "Rua 7";
    public static final String IMMOBILE_CITY = "Salvador";
    public static final String IMMOBILE_NEIGHBORHOOD = "algum bairro";
    public static final String IMMOBILE_STATE = "BA";
    public static final boolean GARAGE = true;
    public static final int QUANTITY_BEDROOMS = 3;
    public static final int QUANTITY_ROOMS = 3;
    public static final BigDecimal IPTU = BigDecimal.valueOf(1500);
    public static final boolean SUITE = true;
    public static final double TOTAL_AREA = 86.00;
    public static final int QUANTITY_BATHROOMS = 3;
    public static final IntegrityEnum INTEGRITY_ENUM = IntegrityEnum.NEW;
    public static final SellerTypeEnum SELLER_TYPE_ENUM = SellerTypeEnum.OWNER;
    public static final AgeEnum AGE_ENUM = AgeEnum.UP_TO_1_YEARS;
    public static final CategoryEnum CATEGORY_ENUM = CategoryEnum.SELL;
    public static final TypeEnum TYPE_ENUM = TypeEnum.HOUSE;
    public static final boolean GARDEN = true;
    public static final boolean VIDEOS = false;
    public static final boolean BEACH = false;
    public static final boolean DISABLED_ACCESS = false;
    public static final boolean PLAYGROUND = false;
    public static final boolean GRILL = false;
    public static final boolean ENERGY_GENERATOR = false;
    public static final boolean CLOSE_TO_THE_CENTER = false;
    public static final boolean ELEVATOR = false;
    public static final boolean POOL = false;
    public static final boolean FRONT_DESK = false;
    public static final boolean MULTI_SPORTS_COURT = false;
    public static final boolean GYM = false;
    public static final boolean STEAM_ROOM = false;
    public static final boolean CABLE_TV = false;
    public static final boolean HEATING = false;
    public static final boolean CABINETS_IN_THE_KITCHEN = false;
    public static final boolean BATHROOM_IN_THE_ROOM = false;
    public static final boolean INTERNET = false;
    public static final boolean PARTY_ROOM = false;
    public static final boolean AIR_CONDITIONING = false;
    public static final boolean AMERICAN_KITCHEN = false;
    public static final boolean HYDROMASSAGE = false;
    public static final boolean FIREPLACE = false;
    public static final boolean PRIVATE_POOL = false;
    public static final boolean ELECTRONIC_GATE = false;
    public static final boolean SERVICE_AREA = false;
    public static final boolean PUB = false;
    public static final boolean CLOSET = false;
    public static final boolean OFFICE = false;
    public static final boolean YARD = false;
    public static final boolean ALARM_SYSTEM = false;
    public static final boolean BALCONY = false;
    public static final boolean CONCIERGE_24_HOUR = false;
    public static final boolean WALLED_AREA = false;
    public static final boolean DOG_ALLOWED = false;
    public static final boolean CAT_ALLOWED = false;
    public static final boolean CAMERAS = false;
    public static final boolean FURNISHED = false;
    public static final boolean SEA_VIEW = false;
    public static final boolean GATED_COMMUNITY = false;
    public static final List<MultipartFile> FILES = List.of();

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

        List<ImmobileByCardsDTO> response = this.immobileService.findImmobileByParamsWithCompleteImagePath(new SearchParamsDTO());

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(path + this.immobile.getFiles().get(0), response.get(0).imageUrl());
    }

    @Test
    @DisplayName("must return a list with 4 ImmobileByCardsDto")
    void find4RandomImmobilesForHome_mustReturnAListWith4ImmobileByCardsDto() {
        when(this.immobileRepository.find4RandomProducts()).thenReturn(List.of(this.immobile, this.immobile, this.immobile, this.immobile));

        List<ImmobileByCardsDTO> response = this.immobileService.find4RandomImmobilesForHome();

        assertNotNull(response);
        assertEquals(ImmobileByCardsDTO.class, response.get(0).getClass());
        assertEquals(4, response.size());
    }

    @Test
    @DisplayName("must return a list of ImmobileByCardsDto with immobiles favorites by user")
    void searchForUserFavoritesImmobiles_returnAListOfImmobileByCardsDto_withImmobilesFavoritesByUser() {
        when(this.immobileRepository.findFavoritedImmobilesIdOfUser(anyLong())).thenReturn(List.of(1L, 2L, 3L, 4L));
        when(this.immobileRepository.findById(anyLong())).thenReturn(Optional.of(this.immobile));

        List<ImmobileByCardsDTO> response = this.immobileService.searchForUserFavoritesImmobiles(1L);

        assertNotNull(response);
        assertEquals(ImmobileByCardsDTO.class, response.get(0).getClass());
        assertEquals(4, response.size());
    }

    @Test
    @DisplayName("must return a list of Long with favorites immobiles id by user")
    void searchForUserFavoritesImmobilesId_mustReturnAListOfLong_withFavoritesImmobilesIdByUser() {
        when(this.immobileRepository.findFavoritedImmobilesIdOfUser(anyLong())).thenReturn(List.of(1L, 2L, 3L));

        List<Long> response = this.immobileService.searchForUserFavoritesImmobilesId(1L);

        assertNotNull(response);
        assertEquals(3, response.size());
        assertEquals(Long.class, response.get(0).getClass());
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

        this.createImmobileDTO = new CreateImmobileDTO(
                IMMOBILE_ID,
                IMMOBILE_NAME,
                IMMOBILE_DESCRIPTION,
                IMMOBILE_ADDRESS,
                IMMOBILE_CITY,
                IMMOBILE_NEIGHBORHOOD,
                IMMOBILE_STATE,
                GARAGE,
                QUANTITY_BEDROOMS,
                QUANTITY_ROOMS,
                IPTU,
                PRICE,
                SUITE,
                TOTAL_AREA,
                QUANTITY_BATHROOMS,
                INTEGRITY_ENUM,
                SELLER_TYPE_ENUM,
                AGE_ENUM,
                CATEGORY_ENUM,
                TYPE_ENUM,
                GARDEN,
                VIDEOS,
                BEACH,
                DISABLED_ACCESS,
                PLAYGROUND,
                GRILL,
                ENERGY_GENERATOR,
                CLOSE_TO_THE_CENTER,
                ELEVATOR,
                POOL,
                FRONT_DESK,
                MULTI_SPORTS_COURT,
                GYM,
                STEAM_ROOM,
                CABLE_TV,
                HEATING,
                CABINETS_IN_THE_KITCHEN,
                BATHROOM_IN_THE_ROOM,
                INTERNET,
                PARTY_ROOM,
                AIR_CONDITIONING,
                AMERICAN_KITCHEN,
                HYDROMASSAGE,
                FIREPLACE,
                PRIVATE_POOL,
                ELECTRONIC_GATE,
                SERVICE_AREA,
                PUB,
                CLOSET,
                OFFICE,
                YARD,
                ALARM_SYSTEM,
                BALCONY,
                CONCIERGE_24_HOUR,
                WALLED_AREA,
                DOG_ALLOWED,
                CAT_ALLOWED,
                CAMERAS,
                FURNISHED,
                SEA_VIEW,
                GATED_COMMUNITY,
                FILES
        );
    }
}