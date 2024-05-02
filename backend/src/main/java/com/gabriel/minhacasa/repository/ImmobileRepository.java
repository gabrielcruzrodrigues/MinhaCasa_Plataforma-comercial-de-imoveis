package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobileRepository extends JpaRepository<Immobile, Long> {
}
