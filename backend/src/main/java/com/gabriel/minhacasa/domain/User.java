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

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends Person {

    private List<String> favorites;

    public User(Long id, @NotBlank String name, @NotBlank String phone, String whatsapp, @NotBlank @Email String email,
                @NotBlank String password, MultipartFile file, Date dateOfBirth, @NotBlank String nationality, GenderEnum gender,
                @NotBlank String city, @NotBlank Set<String> role, List<String> favorites) {
        super(id, name, phone, whatsapp, email, password, file, dateOfBirth, nationality, gender, city, role);
        this.favorites = favorites;
    }
}
