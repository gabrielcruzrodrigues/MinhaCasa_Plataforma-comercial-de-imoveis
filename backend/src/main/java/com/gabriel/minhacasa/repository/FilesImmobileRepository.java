package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.ImmobileFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilesImmobileRepository extends JpaRepository<ImmobileFile, Long> {
    Optional<ImmobileFile> findByPath(String path);
}
