package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.domain.DTO.CreateUserDTO;
import com.gabriel.minhacasa.domain.DTO.ProfileUserResponseDTO;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.*;
import com.gabriel.minhacasa.security.DTO.AuthenticatedResponseDTO;
import com.gabriel.minhacasa.security.service.AuthenticationService;
import com.gabriel.minhacasa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@Slf4j
class UserControllerTest {
    //user attributes
    public static final long ID = 1L;
    public static final String NAME = "Gabriel";
    public static final LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 2, 1);
    public static final String PHONE = "00000000000";
    public static final String WHATSAPP = "00000000000";
    public static final String EMAIL = "email@email.com";
    public static final String IMAGE_PROFILE = "teste.png";
    public static final boolean ACTIVE = true;
    public static final LocalDateTime CREATED_AT = null;
    public static final Set<String> ROLE = Set.of(RoleEnum.USER.toString());
    public static final String PASSWORD = "12345678a!";

    //immobile attributes
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
    public static final List<String> FILES = List.of("teste.png");

    @Mock
    private UserService userService;
    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private MockMvc mockMvc;
    @InjectMocks
    private UserController userController;

    private CreateUserDTO createUserDTO;
    private User user;
    private ProfileUserResponseDTO profileUserResponseDTO;
    private Immobile immobile;
    private final MockMultipartFile imageFile = new MockMultipartFile("imageProfile", "teste.png", "image/jpeg", "dummy image content".getBytes());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startElements();
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("must create user with success")
    void createUser_whenToCall_Success() throws Exception {
        CreateUserDTO request = this.createUserDTO;
        AuthenticatedResponseDTO authenticatedDTO = new AuthenticatedResponseDTO(1L, "token", RoleEnum.USER.toString());

        when(this.authenticationService.loginUser(request.email(), request.password())).thenReturn(authenticatedDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"" + request.email() + "\", \"password\": \"" + request.password() + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\": 1, \"token\": \"token\", \"role\": \"USER\"}"));
    }

    @Test
    @DisplayName("must return a user by id")
    void findById_whenToCall_mustReturnAUserById() throws Exception {
        when(this.userService.findById(anyLong())).thenReturn(this.user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", this.user.getId().toString())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(this.user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(this.user.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(this.user.getPhone()))
                .andReturn();

        log.info("Mvc Result: " + mvcResult.getResponse().getContentAsString());

        assertNotNull(mvcResult);
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("must return a profileUserResponseDTO when user exists")
    void findByIdForProfile_whenUserExists_mustReturnAProfileUserResponseDTO() throws Exception {
        when(this.userService.findByIdForProfile(anyLong())).thenReturn(this.profileUserResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/profile/{id}", this.user.getId().toString())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(this.user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(this.user.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value(this.user.getDateOfBirth().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(this.user.getPhone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.whatsapp").value(this.user.getWhatsapp()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(this.user.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageProfileUrl").value("imageprofile.png"));
    }

    @Test
    void updateUser() {
    }

    @Test
    void disabledUser() {
    }

    @Test
    void addNewImmobileFavorite() {
    }

    @Test
    void removeNewImmobileFavorite() {
    }

    void startElements() {
        this.createUserDTO = new CreateUserDTO(
                NAME,
                PHONE,
                WHATSAPP,
                EMAIL,
                PASSWORD,
                DATE_OF_BIRTH
        );

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

        this.profileUserResponseDTO = new ProfileUserResponseDTO(
                ID,
                NAME,
                DATE_OF_BIRTH.toString(),
                PHONE,
                WHATSAPP,
                EMAIL,
                List.of(),
                "imageprofile.png"
        );
    }
}
