package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.ImmobileFile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.TypeFileEnum;
import com.gabriel.minhacasa.exceptions.customizeExceptions.*;
import com.gabriel.minhacasa.repository.FilesImmobileRepository;
import com.gabriel.minhacasa.repository.ImmobileRepository;
import com.gabriel.minhacasa.repository.UserRepository;
import com.gabriel.minhacasa.utils.CheckFileType;
import com.gabriel.minhacasa.utils.GenerateNewName;
import com.gabriel.minhacasa.utils.GenerateRegister;
import com.gabriel.minhacasa.utils.GetFileExtension;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilesImmobileService {

    @Value("${images-immobile-path}")
    private String imagesPath;

    private final CheckFileType checkFileType;
    private final GenerateNewName generateNewName;
    private final FilesImmobileRepository filesImmobileRepository;
    private final ImmobileRepository immobileRepository;
    private final GenerateRegister generateRegister;
    private final GetFileExtension getFileExtension;
    private final UserRepository userRepository;

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

    private void writeFilesInFolder(List<MultipartFile> files, Immobile immobile) {
        for (MultipartFile file : files) {
            if (immobile != null && checkFileType.verifyFilesForImmobile(file)) {
                try {
                    byte[] bytes = file.getBytes();
                    String newFileName = this.generateFileName(file, immobile);
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
        TypeFileEnum type = getFileExtension.getExtension(fileName);

        ImmobileFile fileImage = ImmobileFile.builder()
                .register(register)
                .path(fileName)
                .type(type)
                .immobile(immobile)
                .build();

        filesImmobileRepository.save(fileImage);
    }

    private String generateFileName(MultipartFile file, Immobile immobile) {
        String newFileName = generateNewName.generateFileName(file, immobile.getName());
        if (filesImmobileRepository.findByPath(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return generateNewName.addCharactersToFileName(newFileName);
        }
    }

    @Transactional
    public void deleteFile(Long immobileId, Long fileId) {
        Immobile immobile = this.immobileRepository.findById(immobileId).orElseThrow(ImmobileNotFoundException::new);
        ImmobileFile immobileFile = this.filesImmobileRepository.findById(fileId).orElseThrow(ImmobileNotFoundException::new);

        if (immobile.getFiles().size() <= 1) {
            throw new IntegrityErrorException("The immobile cannot be left without any file");
        }

        Path path = Paths.get(immobileFile.getPath());

        try {
            Files.delete(path);
            this.deleteRegisterInDatabase(immobileFile);
        } catch (IOException ex) {
            throw new ErrorForDeleteFileException("Error for delete ImmobileFile");
        }
    }

    @Transactional
    public void deleteFile(Immobile immobile, ImmobileFile file) {
        Path path = Paths.get(file.getPath());

        try {
            Files.delete(path);
            this.deleteRegisterInDatabase(file);
        } catch (IOException ex) {
            throw new ErrorForDeleteFileException("Error for delete ImmobileFile");
        }
    }

    @Transactional
    private void deleteRegisterInDatabase(ImmobileFile file) {
        this.filesImmobileRepository.deleteById(file.getId());
    }

    public void soldImmobile(Immobile immobile) {
        List<ImmobileFile> files = immobile.getFiles();
        if (files.size() > 1) {
            for (int i = 1; i < files.size(); i++) {
                this.deleteFile(immobile, files.get(i));
            }
            this.addContractQuantityForUser(immobile);
        }
    }

    private void addContractQuantityForUser(Immobile immobile) {
        User user = this.userRepository.findById(immobile.getUser().getId())
                .orElseThrow(UserNotFoundException::new);
        user.setContractQuantities(user.getContractQuantities() + 1);
        this.userRepository.save(user);
    }
}
