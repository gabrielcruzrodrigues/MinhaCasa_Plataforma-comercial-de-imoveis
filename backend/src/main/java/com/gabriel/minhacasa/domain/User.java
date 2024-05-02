package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.GenderEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 11, unique = true)
    @NotBlank
    private String phone;

    @Column(length = 11, unique = true)
    private String whatsapp;

    @Column(nullable = false, length = 100, unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;

    private String imageProfile;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    @NotBlank
    private String nationality;

    @Column(nullable = false)
    private GenderEnum gender;

    @Column(nullable = false)
    @NotBlank
    private String city;

    @Column(nullable = false)
    private Set<String> role;

    private LocalDateTime createdAt;

    private List<String> favorites;

    private List<String> properties;

    @Column(unique = true)
    private String facebook;

    @Column(unique = true)
    private String instagram;

    private boolean active;
}
