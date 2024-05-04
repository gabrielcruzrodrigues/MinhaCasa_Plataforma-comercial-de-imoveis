package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.ImageProfileFile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.exceptions.*;
import com.gabriel.minhacasa.repository.ImageProfileFileRepository;
import com.gabriel.minhacasa.repository.UserRepository;
import com.gabriel.minhacasa.utils.CheckFileType;
import com.gabriel.minhacasa.utils.GenerateNewName;
import com.gabriel.minhacasa.utils.GenerateRegister;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageProfileService {

    @Value("${images-user-path}")
    private String imagesPath;

    private final CheckFileType checkFileType;
    private final GenerateNewName generateNewName;
    private final ImageProfileFileRepository imageProfileFileRepository;
    private final UserRepository userRepository;
    private final GenerateRegister generateRegister;

    @Transactional
    public void saveFile(MultipartFile file, User user) {
        if (user.getImageProfile() == null) {
            if (checkFileType.verifyIfIsAImage(file)) {
                try {
                    byte[] bytes = file.getBytes();
                    String newFileName = this.generateFileName(file, user);
                    Path path = Paths.get(imagesPath + "/" + newFileName);
                    Files.write(path, bytes);
                    this.saveFileReferenceInDatabase(path.toString(), user);
                } catch (Exception ex) {
                    throw new SaveFileErrorException(ex.getMessage());
                }
            } else {
                throw new DataTypeNotAcceptedForThisOperationException();
            }
        } else {
            throw new TheUserAlreadyHasAnImageProfileException();
        }
    }

    @Transactional
    private void saveFileReferenceInDatabase(String path, User user) {
        String register = generateRegister.newRegister();

        ImageProfileFile fileImage = ImageProfileFile.builder()
                .register(register)
                .path(path)
                .user(user)
                .build();

        imageProfileFileRepository.save(fileImage);
    }

    private String generateFileName(MultipartFile file, User user) {
        String newFileName = generateNewName.generateFileName(file, user.getName());
        if (imageProfileFileRepository.findByPath(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return generateNewName.addCharactersToFileName(newFileName);
        }
    }

    @Transactional
    public void updateImageProfile(MultipartFile file, Long id) {
        Optional<User> userOPT = this.userRepository.findById(id);
        if (userOPT.isPresent()) {
            User user = userOPT.get();
            this.deleteFile(user);
            this.deleteRegisterInDatabase(user);
            this.saveFile(file, user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional
    private void deleteFile(User user) {
        Path path = Paths.get(user.getImageProfile().getPath());
        try {
            Files.delete(path);
        } catch (IOException ex) {
            throw new ErrorForDeleteFileException(ex.getMessage());
        }
    }

    @Transactional
    private void deleteRegisterInDatabase(User user) {
        this.imageProfileFileRepository.deleteById(user.getImageProfile().getId());
        user.setImageProfile(null);
        this.userRepository.save(user);
    }
}
