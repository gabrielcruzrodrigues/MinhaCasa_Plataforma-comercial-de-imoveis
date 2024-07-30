package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.Message;
import com.gabriel.minhacasa.domain.enums.MessageTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM Message WHERE type = :type", nativeQuery = true)
    List<Message> findMessagesByType(@Param("type") String type);

    List<Message> findBySenderName(String senderName);
}
