package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.Immobile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProfileUserResponseDTO(
    String name,
    String dateOfBirth,
    String phone,
    String whatsapp,
    String email,
    String state,
    String city,
    List<Immobile> immobiles,
    String imageProfileBase64
) {
}
