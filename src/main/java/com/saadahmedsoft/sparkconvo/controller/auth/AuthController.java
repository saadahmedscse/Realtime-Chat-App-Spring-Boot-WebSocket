package com.saadahmedsoft.sparkconvo.controller.auth;

import com.saadahmedsoft.sparkconvo.dto.auth.CreateAccountRequest;
import com.saadahmedsoft.sparkconvo.dto.auth.LoginRequest;
import com.saadahmedsoft.sparkconvo.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestPart("photo") MultipartFile photo, @ModelAttribute("userRequest") CreateAccountRequest createAccountRequest) {
        return authService.createAccount(photo, createAccountRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
