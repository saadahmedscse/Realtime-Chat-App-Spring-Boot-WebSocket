package com.saadahmedsoft.sparkconvo.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String message;
    private long senderId;
    private long receiverId;
    private long conversationId;
    private String createdAt;
    private String updatedAt;
}
