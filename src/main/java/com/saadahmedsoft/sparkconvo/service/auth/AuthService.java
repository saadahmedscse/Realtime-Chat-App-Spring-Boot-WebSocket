package com.saadahmedsoft.sparkconvo.service.auth;

import com.saadahmedsoft.sparkconvo.dto.user.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AuthService {

    ResponseEntity<?> createAccount(MultipartFile photo, UserRequest userRequest);
}
