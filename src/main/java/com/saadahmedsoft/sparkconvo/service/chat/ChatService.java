package com.saadahmedsoft.sparkconvo.service.chat;

import com.saadahmedsoft.sparkconvo.dto.chat.ChatRequest;
import com.saadahmedsoft.sparkconvo.dto.chat.ConversationRequest;
import com.saadahmedsoft.sparkconvo.dto.chat.SingleConversationRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ChatService {

    ResponseEntity<?> createConversation(ConversationRequest conversationRequest);

    ResponseEntity<?> getMyConversations(HttpServletRequest request);

    ResponseEntity<?> getSingleConversation(SingleConversationRequest singleConversationRequest);

    ResponseEntity<?> createChat(HttpServletRequest request, ChatRequest chatRequest);
}
