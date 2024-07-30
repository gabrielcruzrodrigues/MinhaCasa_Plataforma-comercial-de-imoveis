package com.gabriel.minhacasa.domain;

import com.gabriel.minhacasa.domain.enums.MessageTypeEnum;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Message {
     
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false)
     private String senderName;

     @Column(nullable = false)
     private Long senderId;

     @Column(nullable = false)
     private String text;

     @Enumerated(EnumType.STRING)
     @Column(nullable = false)
     private MessageTypeEnum type;

     @Column(nullable = false)
     private LocalDateTime createdAt;
}
