package com.saadahmedsoft.sparkconvo.controller.chat;

import com.saadahmedsoft.sparkconvo.dto.chat.ConversationRequest;
import com.saadahmedsoft.sparkconvo.dto.chat.SingleConversationRequest;
import com.saadahmedsoft.sparkconvo.service.chat.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversation")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public ResponseEntity<?> createConversation(@RequestBody ConversationRequest conversationRequest) {
        return chatService.createConversation(conversationRequest);
    }

    @GetMapping
    public ResponseEntity<?> getMyConversations(HttpServletRequest request) {
        return chatService.getConversationWithoutChatList(request);
    }

    @PostMapping("/single")
    public ResponseEntity<?> getSingleConversation(@RequestBody SingleConversationRequest singleConversationRequest) {
        return chatService.getSingleConversation(singleConversationRequest);
    }
}
