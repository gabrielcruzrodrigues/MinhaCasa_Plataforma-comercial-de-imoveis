package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.DTO.CardDTO;
import com.gabriel.minhacasa.domain.DTO.ImmobileByCardsDTO;
import com.gabriel.minhacasa.domain.Immobile;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    @Query(
//            value = "SELECT * FROM Immobile " +
//                    "WHERE (:active IS NULL OR active = :active) " +
//                    "ORDER BY RANDOM()",
//            countQuery = "SELECT count(*) FROM Immobile " +
//                    "WHERE (:active IS NULL OR active = :active)",
//            nativeQuery = true
//    )
//    Page<Immobile> findByParameters(
//            @Param("active") Boolean active,
//            Pageable pageable
//    );
}
