package com.saadahmedsoft.sparkconvo.service.auth;

import com.saadahmedsoft.sparkconvo.dto.auth.CreateAccountRequest;
import com.saadahmedsoft.sparkconvo.dto.auth.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AuthService {

    ResponseEntity<?> createAccount(MultipartFile photo, CreateAccountRequest createAccountRequest);
    ResponseEntity<?> login(LoginRequest loginRequest);
}
