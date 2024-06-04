package com.gabriel.minhacasa.files;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/files")
public class FilesController {
    private final Path fileStorageProfileLocation;
    private final Path fileStorageImmobileLocation;

    public FilesController(FileStorageProperties fileStorageProperties) {
        this.fileStorageProfileLocation = Paths.get(fileStorageProperties.getUploadImageProfileDir())
                .toAbsolutePath().normalize();
        this.fileStorageImmobileLocation = Paths.get(fileStorageProperties.getUploadImageImmobileDir())
                .toAbsolutePath().normalize();
    }

    @GetMapping("/download/profile/{filename:.+}")
    public ResponseEntity<Resource> downloadProfileFile(@PathVariable String filename, HttpServletRequest request) throws IOException {
        Path filePath = fileStorageProfileLocation.resolve(filename).normalize();

        try {
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new FileNotFoundException("File not found or not readable: " + filePath);
            }

            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (MalformedURLException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/download/immobile/{filename:.+}")
    public ResponseEntity<Resource> downloadImmobileFile(@PathVariable String filename, HttpServletRequest request) throws IOException {
        Path filePath = fileStorageImmobileLocation.resolve(filename).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (MalformedURLException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/list/profile")
    public ResponseEntity<List<String>> listProfileFiles() throws IOException {
        List<String> fileNames = Files.list(fileStorageProfileLocation)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
        return ResponseEntity.ok(fileNames);
    }

    @GetMapping("/list/immobile")
    public ResponseEntity<List<String>> listImmobileFiles() throws IOException {
        List<String> fileNames = Files.list(fileStorageImmobileLocation)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
        return ResponseEntity.ok(fileNames);
    }
}
