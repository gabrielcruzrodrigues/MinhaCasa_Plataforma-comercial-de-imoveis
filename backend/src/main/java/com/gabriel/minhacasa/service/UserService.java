package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.CreateUserDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImmobileService immobileService;
    private final ImageProfileService imageProfileService;

    @Transactional
    public void createUser(CreateUserDTO userData) {
        User user = new User();
        user.setName(userData.name());
        user.setPhone(userData.phone());
        user.setWhatsapp(userData.whatsapp());
        user.setEmail(userData.email());
        user.setPassword(passwordEncoder.encode(userData.password()));
        user.setDateOfBirth(userData.dateOfBirth());
        user.setState(userData.state());
        user.setGender(userData.gender());
        user.setCity(userData.city());
        user.setRole(Set.of(RoleEnum.USER.toString()));
        user.setCreatedAt(LocalDateTime.now());
        user.setContractQuantities(0L);
        user.setFavorites(null);
        user.setProperties(null);
        user.setFacebook(null);
        user.setInstagram(null);
        user.setActive(true);

        User userSaved = this.userRepository.save(user);

        if (userData.imageProfile() != null) {
            this.imageProfileService.saveFile(userData.imageProfile(), userSaved);
        }
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

    public ProfileUserResponseDTO findByIdForProfile(Long id) throws IOException {
        User user = this.findById(id);

        Path path = Paths.get(user.getImageProfile().getPath());
        if (!Files.exists(path)) {
            throw new FileNotFoundException();
        }

        byte[] imageBytes = Files.readAllBytes(path);
        String imageProfileBase64 = Base64.getEncoder().encodeToString(imageBytes);

        return new ProfileUserResponseDTO(
                user.getName(), user.getDateOfBirth().toString(), user.getPhone(), user.getWhatsapp(),
                user.getEmail(), user.getState(), user.getCity(), user.getProperties(), imageProfileBase64
        );
    }

    @Transactional
    public void updateUser(UpdateUserDTO userDTO) {
        User user = this.findById(userDTO.id());

        user.setName(userDTO.name());
        user.setPhone(userDTO.phone());
        user.setWhatsapp(userDTO.whatsapp());
        user.setEmail(userDTO.email());
        user.setDateOfBirth(userDTO.dateOfBirth());
        user.setState(userDTO.state());
        user.setGender(userDTO.gender());
        user.setCity(userDTO.city());

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
