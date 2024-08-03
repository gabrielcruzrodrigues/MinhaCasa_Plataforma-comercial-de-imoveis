package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.MessageDTO;
import com.gabriel.minhacasa.domain.Message;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ErrorToSaveMessageException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.MessageNotFoundException;
import com.gabriel.minhacasa.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void create(MessageDTO messageDTO) {
        Message message = messageDTO.createObjectMessage(userService);
        try {
            this.messageRepository.save(message);
        } catch (Exception ex) {
            throw new ErrorToSaveMessageException(ex.getMessage());
        }
    }

    public Message findById(Long id) {
        Optional<Message> message = this.messageRepository.findById(id);
        return message.orElseThrow(MessageNotFoundException::new);
    }

    public List<Message> findByType(String type) {
        return this.messageRepository.findMessagesByType(type);
    }

    public List<Message> findBySenderName(String senderName) {
        return this.messageRepository.findBySenderName(senderName);
    }

    public List<Message> findAll() {
        return this.messageRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        Message message = this.findById(id);
        try {
            this.messageRepository.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Error to delete message.");
        }
    }
}
