package com.saadahmedsoft.sparkconvo.config;

import com.google.gson.Gson;
import com.saadahmedsoft.sparkconvo.dto.chat.ChatRequest;
import com.saadahmedsoft.sparkconvo.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final ChatService chatService;

    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession s : sessions) {
            s.sendMessage(message);
        }

        ChatRequest chatRequest = new Gson().fromJson(message.getPayload(), ChatRequest.class);
        chatService.createChat(chatRequest);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }
}