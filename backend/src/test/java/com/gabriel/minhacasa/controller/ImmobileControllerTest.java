package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.domain.DTO.CreateImmobileDTO;
import com.gabriel.minhacasa.domain.DTO.ImmobileWithSellerIdDTO;
import com.gabriel.minhacasa.domain.DTO.SearchParamsDTO;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.*;
import com.gabriel.minhacasa.repository.ImmobileRepository;
import com.gabriel.minhacasa.service.ImmobileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

class ImmobileControllerTest {
    public static final Long ID = 1L;
    public static final String NAME = "Gabriel";
    public static final LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 2, 1);
    public static final String PHONE = "00000000000";
    public static final String WHATSAPP = "00000000000";
    public static final String EMAIL = "email@email.com";
    public static final String STATE = "BA";
    public static final String CITY = "cidade";
    public static final String IMAGE_PROFILE = "teste.png";
    public static final boolean ACTIVE = true;
    public static final LocalDateTime CREATED_AT = null;
    public static final Set<String> ROLE = Set.of(RoleEnum.USER.toString());
    public static final String PASSWORD = "12345678a!";
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
    public static final BigDecimal PRICE = BigDecimal.valueOf(150000);
    public static final boolean SUITE = true;
    public static final double USEFUL_AREA = 86.00;
    public static final double TOTAL_AREA = 86.00;
    public static final int QUANTITY_BATHROOMS = 3;
    public static final IntegrityEnum INTEGRITY_ENUM = IntegrityEnum.NEW;
    public static final SellerTypeEnum SELLER_TYPE_ENUM = SellerTypeEnum.OWNER;
    public static final AgeEnum AGE_ENUM = AgeEnum.UP_TO_1_YEARS;
    public static final CategoryEnum CATEGORY_ENUM = CategoryEnum.SELL;
    public static final LocalDateTime CREATED = LocalDateTime.now();
    public static final TypeEnum TYPE_ENUM = TypeEnum.HOUSE;
    public static final boolean GARDEN = true;
    public static final boolean VIRTUAL_TOUR = false;
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
    public static final boolean IMMOBILE_ACTIVE = true;
    public static final List<User> FAVORITE_USER = null;
    public static final List<String> FILES = List.of("test.png");

    @Mock
    private MockMvc mockMvc;
    @Mock
    private ImmobileService immobileService;
    @Mock
    private ImmobileRepository immobileRepository;
    @InjectMocks
    private ImmobileController immobileController;

    private Immobile immobile;
    private CreateImmobileDTO createImmobileDTO;
    private User user;
    private final List<MultipartFile> imageFile = List.of(new MockMultipartFile("imageProfile", "teste.png", "image/jpeg", "dummy image content".getBytes()));


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(immobileController).build();
        this.startElements();
    }

    @Test
    @DisplayName("must create a new immobile with success")
    void create_whenToCall_withSuccess() throws Exception {
        when(this.immobileService.findById(anyLong())).thenReturn(this.immobile);

        MockMultipartFile file = new MockMultipartFile(
                "files",
                "test.png",
                "image/png",
                "some-image-content".getBytes()
        );

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("immobileTitle", "Casa para alugar");
        params.add("description", "description");
        params.add("address", "Rua 7");
        params.add("city", "Salvador");
        params.add("neighborhood", "algum bairro");
        params.add("state", "BA");
        params.add("garage", "true");
        params.add("quantityBedrooms", "3");
        params.add("quantityRooms", "3");
        params.add("IPTU", "1500");
        params.add("price", "150000");
        params.add("suite", "true");
        params.add("usefulArea", "86.00");
        params.add("totalArea", "86.00");
        params.add("quantityBathrooms", "3");
        params.add("integrity", "NEW");
        params.add("sellerType", "OWNER");
        params.add("age", "UP_TO_1_YEARS");
        params.add("category", "SELL");
        params.add("type", "HOUSE");
        params.add("garden", "true");
        params.add("virtualTour", "false");
        params.add("videos", "false");
        params.add("beach", "false");
        params.add("disabledAccess", "false");
        params.add("playground", "false");
        params.add("grill", "false");
        params.add("energyGenerator", "false");
        params.add("closeToTheCenter", "false");
        params.add("elevator", "false");
        params.add("pool", "false");
        params.add("frontDesk", "false");
        params.add("multiSportsCourt", "false");
        params.add("gym", "false");
        params.add("steamRoom", "false");
        params.add("cableTV", "false");
        params.add("heating", "false");
        params.add("cabinetsInTheKitchen", "false");
        params.add("bathroomInTheRoom", "false");
        params.add("internet", "false");
        params.add("partyRoom", "false");
        params.add("airConditioning", "false");
        params.add("americanKitchen", "false");
        params.add("hydromassage", "false");
        params.add("fireplace", "false");
        params.add("privatePool", "false");
        params.add("electronicGate", "false");
        params.add("serviceArea", "false");
        params.add("pub", "false");
        params.add("closet", "false");
        params.add("office", "false");
        params.add("yard", "false");
        params.add("alarmSystem", "false");
        params.add("balcony", "false");
        params.add("concierge24Hour", "false");
        params.add("walledArea", "false");
        params.add("dogAllowed", "false");
        params.add("catAllowed", "false");
        params.add("cameras", "false");
        params.add("furnished", "false");
        params.add("seaView", "false");
        params.add("gatedCommunity", "false");
        params.add("active", "true");

        mockMvc.perform(MockMvcRequestBuilders.multipart("/immobile")
                    .file(file)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .params(params))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("must return a status 400 when request body is not complete")
    void create_whenRequestBodyIsNotComplete_mustReturnStatus400() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("immobileTitle", "Casa para alugar");

        mockMvc.perform(MockMvcRequestBuilders.post("/immobile")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("must find and return a Immobile with success")
    void findById_whenImmobileExists_mustReturnAImmobileWithSuccess() throws Exception {
        when(this.immobileService.findById(anyLong())).thenReturn(this.immobile);

        mockMvc.perform(MockMvcRequestBuilders.get("/immobile/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(1L)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(IMMOBILE_NAME));
    }

    @Test
    @DisplayName("must return a immobile with sellerId and full images path")
    void getImmobileWithFullImagePaths_mustReturnAImmobileWithSellerIdAndFullImagesPath() throws Exception {
        ImmobileWithSellerIdDTO immobile = new ImmobileWithSellerIdDTO(this.immobile, 1L);
        when(this.immobileService.getImmobileWithCompleteImagesPath(anyLong())).thenReturn(immobile);

        mockMvc.perform(MockMvcRequestBuilders.get("/immobile/details/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(1L)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sellerId").value(1L))
                .andReturn();
    }

    @Test
    void updateImmobile() {
    }

    @Test
    void deleteImmobile() {
    }

    @Test
    @DisplayName("must disabled the immobile and return status 200")
    void soldImmobile_mustSoldTheImmobile_withSuccess() throws Exception {
        Immobile immobile = this.immobile;
        when(this.immobileRepository.findById(anyLong())).thenReturn(Optional.of(immobile));

        mockMvc.perform(MockMvcRequestBuilders.put("/immobile/sold/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("must return a list with ImmobileByProfileDTO with success")
    void search_whenToCall_mustReturnAListWithImmobileByProfileDTO() throws Exception {
        SearchParamsDTO params = new SearchParamsDTO();
        when(this.immobileService.findImmobileByParamsWithCompleteImagePath(params)).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.post("/immobile/search")
                .contentType(MediaType.APPLICATION_JSON)
                .param("params", String.valueOf(params)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    void startElements() {
        this.immobile = new Immobile(
                ID, IMMOBILE_NAME, IMMOBILE_DESCRIPTION, IMMOBILE_ADDRESS, IMMOBILE_CITY, IMMOBILE_NEIGHBORHOOD, IMMOBILE_STATE,
                GARAGE, QUANTITY_BEDROOMS, QUANTITY_ROOMS, IPTU, PRICE, SUITE, USEFUL_AREA, TOTAL_AREA, QUANTITY_BATHROOMS,
                INTEGRITY_ENUM, SELLER_TYPE_ENUM, AGE_ENUM, CATEGORY_ENUM, CREATED, TYPE_ENUM, GARDEN, VIRTUAL_TOUR, VIDEOS, BEACH,
                DISABLED_ACCESS, PLAYGROUND, GRILL, ENERGY_GENERATOR, CLOSE_TO_THE_CENTER, ELEVATOR, POOL, FRONT_DESK,
                MULTI_SPORTS_COURT, GYM, STEAM_ROOM, CABLE_TV, HEATING, CABINETS_IN_THE_KITCHEN, BATHROOM_IN_THE_ROOM,
                INTERNET, PARTY_ROOM, AIR_CONDITIONING, AMERICAN_KITCHEN, HYDROMASSAGE, FIREPLACE, PRIVATE_POOL, ELECTRONIC_GATE,
                SERVICE_AREA, PUB, CLOSET, OFFICE, YARD, ALARM_SYSTEM, BALCONY, CONCIERGE_24_HOUR, WALLED_AREA, DOG_ALLOWED,
                CAT_ALLOWED, CAMERAS, FURNISHED, SEA_VIEW, GATED_COMMUNITY, IMMOBILE_ACTIVE, user, FAVORITE_USER, FILES
        );

        this.createImmobileDTO = new CreateImmobileDTO(ID, IMMOBILE_NAME, IMMOBILE_DESCRIPTION, IMMOBILE_ADDRESS,
                IMMOBILE_CITY, IMMOBILE_NEIGHBORHOOD, IMMOBILE_STATE, GARAGE, QUANTITY_BEDROOMS, QUANTITY_ROOMS, IPTU, PRICE,
                SUITE, TOTAL_AREA, QUANTITY_BATHROOMS, INTEGRITY_ENUM, SELLER_TYPE_ENUM, AGE_ENUM, CATEGORY_ENUM, TYPE_ENUM,
                GARDEN, VIDEOS, BEACH, DISABLED_ACCESS, PLAYGROUND, GRILL, ENERGY_GENERATOR, CLOSE_TO_THE_CENTER, ELEVATOR, POOL, FRONT_DESK,
                MULTI_SPORTS_COURT, GYM, STEAM_ROOM, CABLE_TV, HEATING, CABINETS_IN_THE_KITCHEN, BATHROOM_IN_THE_ROOM,
                INTERNET, PARTY_ROOM, AIR_CONDITIONING, AMERICAN_KITCHEN, HYDROMASSAGE, FIREPLACE, PRIVATE_POOL, ELECTRONIC_GATE,
                SERVICE_AREA, PUB, CLOSET, OFFICE, YARD, ALARM_SYSTEM, BALCONY, CONCIERGE_24_HOUR, WALLED_AREA, DOG_ALLOWED,
                CAT_ALLOWED, CAMERAS, FURNISHED, SEA_VIEW, GATED_COMMUNITY, imageFile);

        User userAux = User.builder()
                .id(ID)
                .build();

        this.user = User.builder()
                .id(ID)
                .name(NAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .phone(PHONE)
                .whatsapp(WHATSAPP)
                .email(EMAIL)
                .imageProfile(IMAGE_PROFILE)
                .active(ACTIVE)
                .createdAt(CREATED_AT)
                .properties(List.of(this.immobile))
                .role(ROLE)
                .build();
    }

}