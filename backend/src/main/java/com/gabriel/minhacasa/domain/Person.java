package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.GenderEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String name;

    @Column(nullable = false, length = 11)
    @NotBlank
    protected String phone;

    @Column(length = 11)
    protected String whatsapp;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Email
    protected String email;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String password;

    protected MultipartFile file;

    @Column(nullable = false)
    protected Date dateOfBirth;

    @Column(nullable = false)
    @NotBlank
    protected String nationality;

    @Column(nullable = false)
    protected GenderEnum gender;

    @Column(nullable = false)
    @NotBlank
    protected String city;

    @Column(nullable = false)
    @NotBlank
    protected Set<String> role;
}
