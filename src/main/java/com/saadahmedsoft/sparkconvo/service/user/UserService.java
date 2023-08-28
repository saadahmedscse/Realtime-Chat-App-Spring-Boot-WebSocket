package com.saadahmedsoft.sparkconvo.service.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<?> getAllUsersExceptMyself(HttpServletRequest request);

    ResponseEntity<?> getProfile(HttpServletRequest request);
}
