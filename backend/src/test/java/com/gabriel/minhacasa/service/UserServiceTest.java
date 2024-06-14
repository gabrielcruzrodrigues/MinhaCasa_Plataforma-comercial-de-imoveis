package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.CreateUserDTO;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.*;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ImageProfileNotFoundException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.UserNotFoundException;
import com.gabriel.minhacasa.files.FilesService;
import com.gabriel.minhacasa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private FilesService filesService;
    @Mock
    private ImmobileService immobileService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    private User user;
    private CreateUserDTO createUserDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    @DisplayName("must create a new user with image profile with success")
    void createUser_whenImageProfileExists_mustSavedUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(filesService.uploadProfileFile(this.createUserDTO.imageProfile(), this.user)).thenReturn("path/to/uploaded/image");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        this.userService.createUser(createUserDTO);

        verify(this.userRepository, times(1)).save(any(User.class));
        verify(this.filesService, times(1)).uploadProfileFile(any(), any());
    }

    @Test
    @DisplayName("must return a exception when have no image profile")
    void createUser_whenImageProfileNotExists_mustReturnImageProfileNotFoundException() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        CreateUserDTO createUserDTOWithoutImageProfile = new CreateUserDTO(
                "Gabriel", "00000000000", "00000000000", "gabriel@gmail.com", "12345678a!",
                null, LocalDate.of(2000, 8, 13), "BA", GenderEnum.MALE, "Salvador"
        );

        ImageProfileNotFoundException response = assertThrows(ImageProfileNotFoundException.class, () -> {
            this.userService.createUser(createUserDTOWithoutImageProfile);
        });

        assertNotNull(response);
        assertEquals(ImageProfileNotFoundException.class, response.getClass());
        verify(this.filesService, times(0)).uploadProfileFile(any(), any());
        verify(this.userRepository, times(0)).save(any(User.class));
    }

    @Test
    @DisplayName("must return a user with success if the user exist")
    void findById_whenUserExists_mustReturnAUserWithSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));

        User response = this.userService.findById(anyLong());

        assertNotNull(response);
        verify(userRepository, times(1)).findById(anyLong());
        assertEquals(response.getClass(), User.class);
    }

    @Test
    @DisplayName("must return a UserNotFoundException when the user no exist")
    void findById_whenUserNotExists_mustReturnAUserNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserNotFoundException response = assertThrows(UserNotFoundException.class, () -> {
            this.userService.findById(anyLong());
        });

        assertNotNull(response);
        assertEquals(UserNotFoundException.class, response.getClass());
        verify(this.userRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("must return a profileUserResponseDTO with success")
    void findByIdForProfile_whenToCall_mustReturnAProfileUserResponseDTOWithSuccess() {
        when(this.userService.findById(anyLong())).thenReturn(this.user);


    }

    @Test
    void updateUser() {
    }

    @Test
    void disabledUser() {
    }

    @Test
    void addNewFavorite() {
    }

    @Test
    void removeFavorite() {
    }

    void startUser() {
        this.user = User.builder()
                .id(1L)
                .name("Gabriel")
                .active(true)
                .properties(List.of(new Immobile(
                        1L, "Casa para alugar", "description", "Rua 7", "Salvador", "algum bairro", "BA", true, 3, 3, BigDecimal.valueOf(1500),
                        BigDecimal.valueOf(150000), true, 86.00, 86.00, 3, IntegrityEnum.NEW, SellerTypeEnum.OWNER, AgeEnum.UP_TO_1_YEARS,
                        CategoryEnum.SELL, LocalDateTime.now(), TypeEnum.HOUSE, true, false,  false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, true, this.user, null, List.of("teste.png")))
                )
                .build();

        MockMultipartFile imageFile = new MockMultipartFile("imageProfile", "profile.jpg", "image/jpeg", "dummy image content".getBytes());
        this.createUserDTO = new CreateUserDTO(
                "Gabriel", "00000000000", "00000000000", "gabriel@gmail.com", "12345678a!",
                imageFile, LocalDate.of(2000, 8, 13), "BA", GenderEnum.MALE, "Salvador"
        );
    }
}