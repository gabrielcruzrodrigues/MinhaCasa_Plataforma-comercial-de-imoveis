package com.gabriel.minhacasa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}