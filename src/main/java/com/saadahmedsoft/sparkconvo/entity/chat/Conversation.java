package com.saadahmedsoft.sparkconvo.entity.chat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "p1_email")
    private String p1Email;
    @Column(name = "p2_email")
    private String p2Email;
    @Column(name = "p1_photo")
    private String p1Photo;
    @Column(name = "p2_photo")
    private String p2Photo;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    @OneToMany
    private List<Chat> chats;
}
