package com.gabriel.minhacasa.files;

import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.exceptions.customizeExceptions.FileNullContentException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.SaveFileErrorException;
import com.gabriel.minhacasa.utils.GenerateNewName;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        String filename = generateNewName.generateFileName(file);
         try {
             Path targetLocation = fileStorageProfileLocation.resolve(filename);
             file.transferTo(targetLocation);

             return filename;
         } catch (IOException ex) {
            throw new SaveFileErrorException("File Upload Failed.");
         }
    }

    public List<String> uploadImmobileFile(List<MultipartFile> files, Immobile immobile) {
        List<String> referencesOfImmobileFiles = new ArrayList<>();
        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
                String filename = generateNewName.generateFileName(file);
                try {
                    Path targetLocation = fileStorageImmobileLocation.resolve(filename);
                    file.transferTo(targetLocation);
                    referencesOfImmobileFiles.add(filename);
                } catch (IOException ex) {
                    throw new SaveFileErrorException("File Upload Failed.");
                }
            }
            return referencesOfImmobileFiles;
        } else {
            throw new FileNullContentException("uploadImmobileFile: FilesService");
        }
    }
}
