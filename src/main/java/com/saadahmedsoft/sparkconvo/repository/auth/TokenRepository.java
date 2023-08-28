package com.saadahmedsoft.sparkconvo.repository.auth;

import com.saadahmedsoft.sparkconvo.entity.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
}
