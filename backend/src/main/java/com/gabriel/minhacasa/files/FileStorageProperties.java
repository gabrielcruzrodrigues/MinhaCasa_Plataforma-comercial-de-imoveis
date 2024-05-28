package com.gabriel.minhacasa.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageProperties {
    private String uploadImageProfileDir;
    private String uploadImageImmobileDir;
}
