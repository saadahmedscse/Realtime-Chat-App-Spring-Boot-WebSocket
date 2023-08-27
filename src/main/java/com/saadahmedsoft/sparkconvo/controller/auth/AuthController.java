package com.saadahmedsoft.sparkconvo.controller.auth;

import com.saadahmedsoft.sparkconvo.dto.user.UserRequest;
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
    public ResponseEntity<?> createAccount(@RequestPart("photo") MultipartFile photo, @RequestBody UserRequest userRequest) {
        return authService.createAccount(photo, userRequest);
    }
}
