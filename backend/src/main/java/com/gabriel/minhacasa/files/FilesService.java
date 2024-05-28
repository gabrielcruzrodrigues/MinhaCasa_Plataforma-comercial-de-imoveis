package com.gabriel.minhacasa.files;

import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.utils.GenerateNewName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FilesService {

    private final Path fileStorageProfileLocation;
    private final Path fileStorageImmobileLocation;
    private final GenerateNewName generateNewName;

    public FilesService(FileStorageProperties fileStorageProperties, GenerateNewName generateNewName) {
        this.fileStorageProfileLocation = Paths.get(fileStorageProperties.getUploadImageProfileDir())
                .toAbsolutePath().normalize();
        this.fileStorageImmobileLocation = Paths.get(fileStorageProperties.getUploadImageImmobileDir())
                .toAbsolutePath().normalize();
        this.generateNewName = generateNewName;
    }

    public String uploadProfileFile(MultipartFile file, User user) {
        String filename = generateNewName.generateFileName(file, user.getName());
         try {
             Path targetLocation = fileStorageProfileLocation.resolve(filename);
             file.transferTo(targetLocation);

             return "/api/files/download/profile/" + filename;
         } catch (IOException ex) {
            throw new RuntimeException("File Upload Failed.");
         }
    }

    public String uploadImmobileFile(MultipartFile file, User user) {
        String filename = generateNewName.generateFileName(file, user.getName());
        try {
            Path targetLocation = fileStorageImmobileLocation.resolve(filename);
            file.transferTo(targetLocation);

            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/download/immobile/")
                    .path(filename)
                    .toUriString();
        } catch (IOException ex) {
            throw new RuntimeException("File Upload Failed.");
        }
    }
}
