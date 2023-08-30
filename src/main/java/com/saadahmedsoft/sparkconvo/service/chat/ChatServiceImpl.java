package com.saadahmedsoft.sparkconvo.service.chat;

import com.saadahmedsoft.sparkconvo.dto.chat.ChatRequest;
import com.saadahmedsoft.sparkconvo.dto.chat.ConversationRequest;
import com.saadahmedsoft.sparkconvo.dto.chat.ConversationResponse;
import com.saadahmedsoft.sparkconvo.dto.chat.SingleConversationRequest;
import com.saadahmedsoft.sparkconvo.dto.common.ApiResponse;
import com.saadahmedsoft.sparkconvo.dto.user.UserProfileResponse;
import com.saadahmedsoft.sparkconvo.entity.chat.Chat;
import com.saadahmedsoft.sparkconvo.entity.chat.Conversation;
import com.saadahmedsoft.sparkconvo.entity.user.User;
import com.saadahmedsoft.sparkconvo.repository.chat.ChatRepository;
import com.saadahmedsoft.sparkconvo.repository.chat.ConversationRepository;
import com.saadahmedsoft.sparkconvo.repository.user.UserRepository;
import com.saadahmedsoft.sparkconvo.util.auth.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> createConversation(ConversationRequest conversationRequest) {
        if (conversationRepository.getSingleConversation(conversationRequest.getP1Email(), conversationRequest.getP2Email()).isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            String creationTime = simpleDateFormat.format(new Date());

            String p1Photo = userRepository.findFirstByEmail(conversationRequest.getP1Email()).getPhoto();
            String p2Photo = userRepository.findFirstByEmail(conversationRequest.getP2Email()).getPhoto();

            Conversation conversation = Conversation.builder()
                    .p1Email(conversationRequest.getP1Email())
                    .p2Email(conversationRequest.getP2Email())
                    .p1Photo(p1Photo)
                    .p2Photo(p2Photo)
                    .createdAt(creationTime)
                    .updatedAt(creationTime)
                    .build();

            conversationRepository.save(conversation);

            return new ResponseEntity<>(new ApiResponse(true, "Conversation created successfully"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(true, "Conversation already created before"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getSingleConversation(SingleConversationRequest singleConversationRequest) {
        Optional<Conversation> optionalConversation = conversationRepository.getSingleConversation(singleConversationRequest.getP1Email(), singleConversationRequest.getP2Email());
        if (optionalConversation.isPresent()) return new ResponseEntity<>(optionalConversation.get(), HttpStatus.OK);

        return new ResponseEntity<>(new ApiResponse(false, "No conversation found"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public void createChat(ChatRequest chatRequest) {
        Optional<Conversation> optionalConversation = conversationRepository.findById(chatRequest.getConversationId());
        if (optionalConversation.isPresent()) {
            Conversation conversation = optionalConversation.get();
            List<Chat> chatList = conversation.getChats();

            Chat chat = Chat.builder()
                    .message(chatRequest.getMessage())
                    .receiverId(chatRequest.getReceiverId())
                    .senderId(chatRequest.getSenderId())
                    .createdAt(chatRequest.getCreatedAt())
                    .updatedAt(chatRequest.getUpdatedAt())
                    .build();

            chatRepository.save(chat);
            chatList.add(chat);
            conversationRepository.save(conversation);
        }
    }

    @Override
    public ResponseEntity<?> getConversationWithoutChatList(HttpServletRequest request) {
        String email = getUsernameFromRequest(request);
        List<Conversation> conversations = conversationRepository.getConversationsWithoutChat(email);
        List<ConversationResponse> conversationResponses = new ArrayList<>();
        User user;

        for (Conversation c : conversations) {
            if (c.getP1Email().equals(email)) {
                user = userRepository.findFirstByEmail(c.getP2Email());
            } else {
                user = userRepository.findFirstByEmail(c.getP1Email());
            }

            String lastMessage = null;
            if (!c.getChats().isEmpty()) lastMessage = c.getChats().get(0).getMessage();

            conversationResponses.add(
                    ConversationResponse.builder()
                            .id(c.getId())
                            .lastMessage(lastMessage)
                            .friend(
                                    UserProfileResponse.builder()
                                            .id(user.getId())
                                            .name(user.getName())
                                            .photo(user.getPhoto())
                                            .email(user.getEmail())
                                            .gender(user.getGender())
                                            .createdAt(user.getCreatedAt())
                                            .updatedAt(user.getUpdatedAt())
                                            .build()
                            )
                            .createdAt(c.getCreatedAt())
                            .updatedAt(c.getUpdatedAt())
                            .build()
            );
        }

        return new ResponseEntity<>(conversationResponses, HttpStatus.OK);
    }

    private String getUsernameFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return jwtUtil.getUsernameFromToken(token);
    }
}
