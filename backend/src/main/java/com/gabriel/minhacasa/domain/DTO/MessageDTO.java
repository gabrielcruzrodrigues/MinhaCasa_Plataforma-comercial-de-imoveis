package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.Message;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.MessageTypeEnum;
import com.gabriel.minhacasa.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDTO {

    @NotNull
    Long senderId;
    @NotBlank
    String text;
    @NotNull
    MessageTypeEnum type;

    public Message createObjectMessage(UserService userService) {
        User user = userService.findById(this.senderId);
        return Message.builder()
                .senderId(this.senderId)
                .senderName(user.getName())
                .text(this.text)
                .type(type)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
