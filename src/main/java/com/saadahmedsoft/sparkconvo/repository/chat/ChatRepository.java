package com.saadahmedsoft.sparkconvo.repository.chat;

import com.saadahmedsoft.sparkconvo.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
