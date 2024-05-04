package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.ImageImmobileFile;
import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.exceptions.FileNullContentException;
import com.gabriel.minhacasa.exceptions.ImmobileNotFoundException;
import com.gabriel.minhacasa.exceptions.SaveFileErrorException;
import com.gabriel.minhacasa.repository.ImageImmobileFileRepository;
import com.gabriel.minhacasa.repository.ImmobileRepository;
import com.gabriel.minhacasa.utils.CheckFileType;
import com.gabriel.minhacasa.utils.GenerateNewName;
import com.gabriel.minhacasa.utils.GenerateRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageImmobileService {

    @Value("${images-immobile-path}")
    private String imagesPath;

    private final CheckFileType checkFileType;
    private final GenerateNewName generateNewName;
    private final ImageImmobileFileRepository imageImmobileFileRepository;
    private final ImmobileRepository immobileRepository;
    private final GenerateRegister generateRegister;

    public void saveFiles(List<MultipartFile> images, Long id) {
        if (images != null) {
            Optional<Immobile> immobileOPT = this.immobileRepository.findById(id);
            if (immobileOPT.isPresent()) {
                this.writeFilesInFolder(images, immobileOPT.get());
            } else {
                throw new ImmobileNotFoundException();
            }
        } else {
            throw new FileNullContentException("saveFiles method");
        }
    }

    public void saveFiles(List<MultipartFile> images, Immobile immobile) {
        writeFilesInFolder(images, immobile);
    }

    private void writeFilesInFolder(List<MultipartFile> images, Immobile immobile) {
        for (MultipartFile image : images) {
            if (immobile != null && checkFileType.verifyIfIsAImage(image)) {
                try {
                    byte[] bytes = image.getBytes();
                    String newFileName = this.generateFileName(image, immobile);
                    Path path = Paths.get(imagesPath + "/" + newFileName);
                    Files.write(path, bytes);
                    this.saveFileReferenceInDatabase(path.toString(), immobile);
                } catch (Exception ex) {
                    throw new SaveFileErrorException(ex.getMessage());
                }
            } else {
                throw new FileNullContentException("writeFilesInFolder method");
            }
        }
    }

    private void saveFileReferenceInDatabase(String fileName, Immobile immobile) {
        String register = generateRegister.newRegister();

        ImageImmobileFile fileImage = ImageImmobileFile.builder()
                .register(register)
                .path(fileName)
                .immobile(immobile)
                .build();

        imageImmobileFileRepository.save(fileImage);
    }

    private String generateFileName(MultipartFile file, Immobile immobile) {
        String newFileName = generateNewName.generateFileName(file, immobile.getName());
        if (imageImmobileFileRepository.findByPath(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return generateNewName.addCharactersToFileName(newFileName);
        }
    }
}
