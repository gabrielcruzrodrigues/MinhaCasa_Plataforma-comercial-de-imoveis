package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.CreateUserDTO;
import com.gabriel.minhacasa.domain.DTO.ImmobileByCardsDTO;
import com.gabriel.minhacasa.domain.DTO.ProfileUserResponseDTO;
import com.gabriel.minhacasa.domain.DTO.UpdateUserDTO;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.RoleEnum;
import com.gabriel.minhacasa.exceptions.customizeExceptions.FavoriteAlreadyExistsException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ImmobileNotFoundException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.UserNotFoundException;
import com.gabriel.minhacasa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${base-url}")
    private String baseUrl;
    @Value("${base-url-user-files-api}")
    private String baseUrlProfileFilesApi;
    @Value("${base-url-immobile-files-api}")
    private String baseUrlImmobileFilesApi;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImmobileService immobileService;

    @Transactional
    public void createUser(CreateUserDTO userData) {
        User user = new User();
        user.setName(userData.name());
        user.setPhone(userData.phone());
        user.setWhatsapp(userData.whatsapp());
        user.setEmail(userData.email());
        user.setPassword(passwordEncoder.encode(userData.password()));
        user.setDateOfBirth(userData.dateOfBirth());
        user.setRole(Set.of(RoleEnum.USER.toString()));
        user.setCreatedAt(LocalDateTime.now());
        user.setContractQuantities(0L);
        user.setFavorites(null);
        user.setProperties(null);
        user.setFacebook(null);
        user.setInstagram(null);
        user.setActive(true);

        this.userRepository.save(user);
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

    public ProfileUserResponseDTO findByIdForProfile(Long id) throws IOException {
        User user = this.findById(id);
        List<ImmobileByCardsDTO> immobiles = new ArrayList<>();
        List<Immobile> properties = user.getProperties();

        for (Immobile immobile : properties) {
            if (immobile.isActive()) {
                Path pathFirstImmobileImage = Paths.get(immobile.getFiles().get(0));
                String imageImmobile = baseUrl + baseUrlImmobileFilesApi + pathFirstImmobileImage;

                ImmobileByCardsDTO profileDTO = new ImmobileByCardsDTO(
                        immobile.getId(), immobile.getQuantityRooms(), immobile.getQuantityBedrooms(), immobile.getQuantityBathrooms(),
                        imageImmobile, immobile.getPrice(), immobile.getName(), immobile.getDescription(), immobile.getUser().getId()
                );

                immobiles.add(profileDTO);
            }
        }
        String imageProfile;
        if (user.getImageProfile() != null) {
            imageProfile = baseUrl + baseUrlProfileFilesApi + user.getImageProfile();

            return new ProfileUserResponseDTO(
                    user.getId(), user.getName(), user.getDateOfBirth().toString(), user.getPhone(), user.getWhatsapp(),
                    user.getEmail(), immobiles, imageProfile
            );
        } else {
            return new ProfileUserResponseDTO(
                    user.getId(), user.getName(), user.getDateOfBirth().toString(), user.getPhone(), user.getWhatsapp(),
                    user.getEmail(), immobiles, null
            );
        }
    }

    @Transactional
    public void updateUser(UpdateUserDTO userDTO) {
        User user = this.findById(userDTO.id());

        user.setName(userDTO.name());
        user.setPhone(userDTO.phone());
        user.setWhatsapp(userDTO.whatsapp());
        user.setEmail(userDTO.email());
        user.setDateOfBirth(userDTO.dateOfBirth());

        userRepository.save(user);
    }

    @Transactional
    public void disabledUser(Long id) {
        User user = this.findById(id);
        user.setActive(false);
        userRepository.save(user);
    }

    public void addNewFavorite(Long studentId, Long immobileId) {
        Immobile immobile = this.immobileService.findById(immobileId);
        User user = this.findById(studentId);

        if (!user.getFavorites().contains(immobile)) {
            user.getFavorites().add(immobile);
            userRepository.save(user);
        } else {
            throw new FavoriteAlreadyExistsException();
        }
    }

    public void removeFavorite(Long studentId, Long immobileId) {
        Immobile immobile = this.immobileService.findById(immobileId);
        User user = this.findById(studentId);

        if (user.getFavorites().contains(immobile)) {
            user.getFavorites().remove(immobile);
            userRepository.save(user);
        } else {
            throw new ImmobileNotFoundException();
        }
    }
}
