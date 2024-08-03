package com.gabriel.minhacasa.service;

import com.gabriel.minhacasa.domain.DTO.MessageDTO;
import com.gabriel.minhacasa.domain.Message;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.MessageTypeEnum;
import com.gabriel.minhacasa.exceptions.customizeExceptions.ErrorToSaveMessageException;
import com.gabriel.minhacasa.exceptions.customizeExceptions.MessageNotFoundException;
import com.gabriel.minhacasa.repository.MessageRepository;
import com.gabriel.minhacasa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private MessageService messageService;

    private MessageDTO messageDTO;
    private Message message;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startEntities();
    }

    @Test
    @DisplayName("must create message when to call")
    void create_mustCreateMessage_Success() {
        this.messageService.create(this.messageDTO);
        verify(this.messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    @DisplayName("must return ErrorToSaveMessageException when an error occurs")
    void create_mustReturnAErrorToSaveMessageException_WhenAnErrorOccurs() {
        when(this.messageRepository.save(any(Message.class))).thenThrow(ErrorToSaveMessageException.class);

        ErrorToSaveMessageException response = assertThrows(ErrorToSaveMessageException.class, () -> {
            this.messageRepository.save(this.message);
        });

        assertNotNull(response);
        assertEquals(ErrorToSaveMessageException.class, response.getClass());
    }

    @Test
    @DisplayName("must return a Message instance when to call with valid id")
    void findById_whenTheIdIsValid_returnWithSuccess() {
        when(this.messageRepository.findById(anyLong())).thenReturn(Optional.of(this.message));

        Message response = this.messageService.findById(1L);

        assertNotNull(response);
        assertEquals(Message.class, response.getClass());
        assertEquals(1L, response.getId());
        verify(this.messageRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("must return a MessageNotFoundException when id is not valid")
    void findById_whenIdIsNotValid_returnMessageNotFoundException() {
        when(this.messageRepository.findById(anyLong())).thenThrow(MessageNotFoundException.class);

        MessageNotFoundException response = assertThrows(MessageNotFoundException.class, () -> {
           this.messageService.findById(1L);
        });

        assertNotNull(response);
        assertEquals(MessageNotFoundException.class, response.getClass());
        verify(this.messageRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("must return a list of Message by type")
    void findByType_mustReturnAListOfMessageByType_success() {
        when(this.messageRepository.findMessagesByType("IDEA")).thenReturn(List.of(this.message));

        List<Message> response = this.messageService.findByType("IDEA");

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(this.messageRepository, times(1)).findMessagesByType("IDEA");
    }

    @Test
    @DisplayName("must return a list of Message by senderName")
    void findBySenderName_mustReturnAListOfMessageByType_success() {
        when(this.messageRepository.findBySenderName("Gabriel")).thenReturn(List.of(this.message));

        List<Message> response = this.messageService.findBySenderName("Gabriel");

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(this.messageRepository, times(1)).findBySenderName("Gabriel");
    }

    @Test
    @DisplayName("must return all records of Messages")
    void findAll_mustReturnAllRecord_success() {
        when(this.messageRepository.findAll()).thenReturn(List.of(this.message));

        List<Message> response = this.messageService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(this.messageRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("must delete the record with success when found record")
    void delete_whenFoundMessage_success() {
        when(this.messageRepository.findById(anyLong())).thenReturn(Optional.of(this.message));

        this.messageService.delete(1L);

        verify(this.messageRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("must return a RuntimeException when an error occurs")
    void delete_mustReturnARuntimeException_whenAnErrorOccurs() {
        when(this.messageRepository.findById(anyLong())).thenReturn(Optional.of(this.message));
        doThrow(new RuntimeException("Database error")).when(this.messageRepository).deleteById(anyLong());

        RuntimeException response = assertThrows(RuntimeException.class, () -> {
           this.messageService.delete(1L);
        });

        assertNotNull(response);
        assertEquals(RuntimeException.class, response.getClass());
        assertEquals("Error to delete message.", response.getMessage());
    }

    void startEntities() {
        this.user = new User();
        this.user.setId(1L);
        this.user.setName("Gabriel");

        this.message = new Message();
        this.message.setId(1L);
        this.message.setSenderId(1L);
        this.message.setText("example text");
        this.message.setType(MessageTypeEnum.IDEA);

        this.messageDTO = new MessageDTO();
        this.messageDTO.setSenderId(1L);
        this.messageDTO.setText("example text");
        this.messageDTO.setType(MessageTypeEnum.IDEA);

        when(this.userService.findById(1L)).thenReturn(this.user);
    }
}