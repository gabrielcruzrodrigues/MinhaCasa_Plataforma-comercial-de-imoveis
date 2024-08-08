package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.DTO.CardDTO;
import com.gabriel.minhacasa.domain.DTO.ImmobileByCardsDTO;
import com.gabriel.minhacasa.domain.Immobile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobileRepository extends JpaRepository<Immobile, Long> {

     @Query(value = "SELECT * FROM Immobile ORDER BY RANDOM() LIMIT 4", nativeQuery = true)
     List<Immobile> find4RandomProducts();

     @Query(value = "SELECT immobile_id FROM user_favorites WHERE user_id = :id", nativeQuery = true)
     List<Long> findFavoritedImmobilesIdOfUser(@Param("id") Long id);

//     @Query(value = "SELECT id, quantity_rooms, quantity_bedrooms, quantity_bathrooms, files, price, name, description, user_id FROM Immobile WHERE id = :id", nativeQuery = true)
//     Immobile findImmobileByCards(@Param("id") Long id);
}
