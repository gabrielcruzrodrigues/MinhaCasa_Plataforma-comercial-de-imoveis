package com.gabriel.minhacasa.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;
    private int quantityRooms;
    private int quantityBedrooms;
    private int quantityBathrooms;
    private List<String> files;
    private BigDecimal price;
    private String name;
    private String description;
    private Long userId;
}
