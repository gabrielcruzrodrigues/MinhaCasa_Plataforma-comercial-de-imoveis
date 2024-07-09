package com.gabriel.minhacasa.files;

import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.exceptions.customizeExceptions.SaveFileErrorException;
import com.gabriel.minhacasa.utils.GenerateNewName;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Slf4j
class FilesServiceTest {

    @Mock
    private GenerateNewName generateNewName;
    @Mock
    private FileStorageProperties fileStorageProperties;
    private FilesService filesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        //paths for save files in folders
        String profilePath = "C:/minhacasa/images-profile";
        String immobilePath = "C:/minhacasa/files-immobile";

        //setting the simulation values of FileStorageProperties to start FileService with all constructor dependencies
        when(this.fileStorageProperties.getUploadImageProfileDir()).thenReturn(profilePath);
        when(this.fileStorageProperties.getUploadImageImmobileDir()).thenReturn(immobilePath);

        //restarting the FileService instance with the configuration values
        this.filesService = new FilesService(fileStorageProperties, generateNewName);
    }

    @Test
    @DisplayName("must save file in folder with new name with success")
    void uploadProfileFile_mustSaveFileInFolderWithNewName_WithSuccess() {
        MultipartFile file = new MockMultipartFile(
                "imageProfile",
                "imageProfile.png",
                "image/jpeg",
                "dummy image content".getBytes());

        User user = new User();
        user.setName("Gabriel");

        String fileName = "newNameTest";

        when(this.generateNewName.generateFileName(file, user.getName())).thenReturn(fileName);

        String response = this.filesService.uploadProfileFile(file, user);

        assertNotNull(response);
        assertEquals(String.class, response.getClass());
        assertEquals(fileName, response);
    }

    @Test
    @DisplayName("must return SaveFileErrorException when an error happens")
    void uploadProfileFile_returnSaveFileErrorException_whenAnErrorHappens() {
        MultipartFile file = new MockMultipartFile(
                "imageProfile",
                "imageProfile.png",
                "image/jpeg",
                "dummy image content".getBytes());

        User user = new User();
        user.setName("Gabriel");

        when(this.generateNewName.generateFileName(file, user.getName())).thenThrow(SaveFileErrorException.class);

        SaveFileErrorException response = assertThrows(SaveFileErrorException.class, () -> {
            this.filesService.uploadProfileFile(file, user);
        });

        assertNotNull(response);
        assertEquals(response.getClass(), SaveFileErrorException.class);
    }

    @Test
    @DisplayName("must return a list with reference of immobile files with success")
    void uploadImmobileFile_mustSaveAndReturnAListWithReferenceOfImmobileFile_withSuccess() {
        List<MultipartFile> files = List.of(
                new MockMultipartFile(
                        "imageProfile",
                        "imageProfile.png",
                        "image/jpeg",
                        "dummy image content".getBytes())
        );

        Immobile immobile = new Immobile();
        immobile.setName("immobile");

        String fileName = "newNameTest";
        when(this.generateNewName.generateFileName(files.get(0), immobile.getName())).thenReturn(fileName);

        List<String> response = this.filesService.uploadImmobileFile(files, immobile);

        assertNotNull(response);
        assertEquals(response.get(0), fileName);
        assertEquals(1, response.size());
    }


}