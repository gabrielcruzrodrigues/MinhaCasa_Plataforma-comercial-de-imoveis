package com.gabriel.minhacasa.files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.when;

class FilesControllerTest {

    @Mock
    private MockMvc mockMvc;
    @Mock
    private FileStorageProperties fileStorageProperties;
    private FilesController filesController;

    private final Path fileStorageLocation = Paths.get("C:/minhacasa/images-profile").toAbsolutePath().normalize();

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        //paths for save files in folders
        String profilePath = "C:/minhacasa/images-profile";
        String immobilePath = "C:/minhacasa/files-immobile";

        this.fileStorageProperties.setUploadImageImmobileDir(immobilePath);
        this.fileStorageProperties.setUploadImageProfileDir(profilePath);

        //setting the simulation values of FileStorageProperties to start FileService with all constructor dependencies
        when(this.fileStorageProperties.getUploadImageProfileDir()).thenReturn(profilePath);
        when(this.fileStorageProperties.getUploadImageImmobileDir()).thenReturn(immobilePath);

        //restarting the FilesController instance with the configuration values
        this.filesController = new FilesController(fileStorageProperties);

        //create file for tests
        Files.createDirectories(this.fileStorageLocation);
        Path filePath = this.fileStorageLocation.resolve("testImage.jpg");
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            Files.write(filePath, "teste image content".getBytes());
        }
    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(this.fileStorageLocation.resolve("testImage.jpg"));
    }

    @Test
    @DisplayName("must find and return a image profile in response with success")
    void downloadProfileFile_mustReturnAImageProfile_withSuccess() throws Exception {
        String filename = "testImage.jpg";
        Path filePath = Paths.get("C:/minhacasa/images-profile").resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServletPath("/download/profile/" + filename);

        Mockito.when(this.filesController.downloadProfileFile(filename, request))
                .thenReturn(ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource));

        mockMvc.perform(MockMvcRequestBuilders.get("/download/profile/" + filename))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\""))
                .andExpect(MockMvcResultMatchers.content().bytes(Files.readAllBytes(filePath)));
    }

    @Test
    void downloadImmobileFile() {
    }
}