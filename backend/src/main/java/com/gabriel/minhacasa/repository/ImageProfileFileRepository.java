package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.ImageProfileFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageProfileFileRepository extends JpaRepository<ImageProfileFile, Long> {
    Optional<ImageProfileFile> findByPath(String path);
}
