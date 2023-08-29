package com.saadahmedsoft.sparkconvo.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationRequest {
    private String p1Email;
    private String p2Email;
}
