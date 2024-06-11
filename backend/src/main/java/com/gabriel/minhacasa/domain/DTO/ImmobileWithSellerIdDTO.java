package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.Immobile;

public record ImmobileWithSellerIdDTO(
        Immobile immobile,
        Long sellerId
) {
}
