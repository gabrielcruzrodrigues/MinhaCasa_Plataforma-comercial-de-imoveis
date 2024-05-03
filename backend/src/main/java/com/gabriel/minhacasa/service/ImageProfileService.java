package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.ImageProfileFile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.exceptions.FileNullContentException;
import com.gabriel.minhacasa.exceptions.SaveFileErrorException;
import com.gabriel.minhacasa.exceptions.UserNullContentException;
import com.gabriel.minhacasa.repository.ImageProfileFileRepository;
import com.gabriel.minhacasa.repository.UserRepository;
import com.gabriel.minhacasa.utils.CheckFileType;
import com.gabriel.minhacasa.utils.GenerateNewName;
import com.gabriel.minhacasa.utils.GenerateRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class ImageProfileService implements FileStorageServiceInterface<User> {

    @Value("${images-user-path}")
    private String imagesPath;

    private final CheckFileType checkFileType;
    private final GenerateNewName generateNewName;
    private final ImageProfileFileRepository imageProfileFileRepository;
    private final GenerateRegister generateRegister;

    @Override
    public void saveFile(MultipartFile file, User user) {
        if (checkFileType.verifyIfIsAImage(file)) {
            if (user != null) {
                try {
                    byte[] bytes = file.getBytes();
                    String newFileName = this.generateFileName(file, user);
                    Path path = Paths.get(imagesPath + "/" + newFileName);
                    Files.write(path, bytes);
                    this.saveFileReferenceInDatabase(newFileName, user);
                } catch (Exception ex) {
                    throw new SaveFileErrorException(ex.getMessage());
                }
            } else {
                throw new UserNullContentException();
            }
        } else {
            throw new FileNullContentException();
        }
    }

    @Override
    public void saveFileReferenceInDatabase(String fileName, User user) {
        String register = generateRegister.newRegister();

        ImageProfileFile fileImage = ImageProfileFile.builder()
                .register(register)
                .path(fileName)
                .user(user)
                .build();

        imageProfileFileRepository.save(fileImage);
    }

    @Override
    public String generateFileName(MultipartFile file, User user) {
        String newFileName = generateNewName.generateFileName(file, user.getName());
        if (imageProfileFileRepository.findByPath(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return generateNewName.addCharactersToFileName(newFileName);
        }
    }
}
