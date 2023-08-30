package com.saadahmedsoft.sparkconvo.dto.chat;

import com.saadahmedsoft.sparkconvo.dto.user.UserProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponse {
    private long id;
    private String lastMessage;
    private UserProfileResponse friend;
    private String createdAt;
    private String updatedAt;
}
