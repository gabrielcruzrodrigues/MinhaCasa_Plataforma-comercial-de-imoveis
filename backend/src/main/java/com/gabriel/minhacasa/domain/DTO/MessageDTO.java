package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.Message;
import com.gabriel.minhacasa.domain.enums.MessageTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDTO {

    @NotBlank @NotNull
    String senderName;
    @NotNull
    Long senderId;
    @NotBlank
    String text;
    @NotNull
    MessageTypeEnum type;

    public Message createObjectMessage() {
        return Message.builder()
                .senderId(this.senderId)
                .senderName(this.senderName)
                .text(this.text)
                .type(type)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
