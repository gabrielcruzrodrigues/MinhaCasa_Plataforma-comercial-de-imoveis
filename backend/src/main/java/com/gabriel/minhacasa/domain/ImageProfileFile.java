package com.gabriel.minhacasa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageProfileFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String register;

    @Column(nullable = false)
    private String path;

    @JsonIgnore
    @OneToOne(mappedBy = "imageProfile")
    private User user;
}
