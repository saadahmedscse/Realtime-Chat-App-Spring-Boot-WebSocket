package com.saadahmedsoft.sparkconvo.repository.chat;

import com.saadahmedsoft.sparkconvo.entity.chat.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT c FROM  Conversation  c WHERE c.p1Email like :p1Email AND c.p2Email LIKE :p2Email OR c.p1Email LIKE :p2Email AND c.p2Email LIKE :p1Email")
    Optional<Conversation> getSingleConversation(@Param("p1Email") String p1Email, @Param("p2Email") String p2Email);

    @Query("SELECT c FROM Conversation c WHERE c.p1Email LIKE :email OR c.p2Email LIKE :email")
    List<Conversation> getConversationsWithoutChat(@Param("email") String email);
}
