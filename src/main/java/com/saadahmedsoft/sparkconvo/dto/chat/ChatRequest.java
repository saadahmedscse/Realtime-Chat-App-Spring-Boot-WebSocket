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
    private long conversationId;
}
