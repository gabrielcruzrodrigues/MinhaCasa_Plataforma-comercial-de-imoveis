package com.gabriel.minhacasa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String password;

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

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private ImageProfileFile imageProfile;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "immobile_id")
    )
    private List<Immobile> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Immobile> properties;

    @Column(unique = true)
    private String facebook;

    @Column(unique = true)
    private String instagram;

    private boolean active;
}
