package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.GenderEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Owner extends Person {
    private List<String> properties;
    private String facebook;
    private String instagram;

    public Owner(Long id, @NotBlank String name, @NotBlank String phone, String whatsapp, @NotBlank @Email String email,
                 @NotBlank String password, String file, Date dateOfBirth, @NotBlank String nationality, GenderEnum gender,
                 @NotBlank String city, @NotBlank Set<String> role, List<String> properties, String facebook, String instagram) {
        super(id, name, phone, whatsapp, email, password, file, dateOfBirth, nationality, gender, city, role);
        this.properties = properties;
        this.facebook = facebook;
        this.instagram = instagram;
    }
}
