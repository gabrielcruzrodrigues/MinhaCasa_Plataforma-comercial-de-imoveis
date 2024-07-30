package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.Immobile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobileRepository extends JpaRepository<Immobile, Long> {

     @Query(value = "SELECT * FROM Immobile ORDER BY DESC LIMIT 4", nativeQuery = true)
     List<Immobile> find4RandomProducts();
}
