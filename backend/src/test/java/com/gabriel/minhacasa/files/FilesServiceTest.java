package com.gabriel.minhacasa.files;

import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.exceptions.customizeExceptions.FileNullContentException;
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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void uploadProfileFile_returnSaveFileErrorException_whenAnErrorHappens() throws IOException {
        //settings for test
        MultipartFile file = mock(MultipartFile.class);

        User user = new User();
        user.setName("Gabriel");

        when(this.generateNewName.generateFileName(file, user.getName())).thenThrow(SaveFileErrorException.class);

        //Generation error
        Path targetLocation = Paths.get("some/directory/testFileName");
        doThrow(new IOException("Simulated IOException")).when(file).transferTo(targetLocation);

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

    @Test
    @DisplayName("when list is empty must return a FileNullContentException with the message 'The file list is nullabe'")
    void uploadImmobileFile_whenListIsEmpty_mustReturnAFileNullContentException() {
        List<MultipartFile> files = List.of();
        Immobile immobile = new Immobile();

        FileNullContentException response = assertThrows(FileNullContentException.class, () -> {
            this.filesService.uploadImmobileFile(files, immobile);
        });

        assertNotNull(response);
        assertEquals(response.getClass(), FileNullContentException.class);
        assertEquals("File is null in uploadImmobileFile: FilesService", response.getMessage());
    }
}