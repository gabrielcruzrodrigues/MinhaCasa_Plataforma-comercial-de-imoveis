package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.ImmobileFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilesImmobileRepository extends JpaRepository<ImmobileFiles, Long> {
    Optional<ImmobileFiles> findByPath(String path);
}
