package com.saadahmedsoft.sparkconvo.service.user;

import com.saadahmedsoft.sparkconvo.repository.user.UserRepository;
import com.saadahmedsoft.sparkconvo.util.auth.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> getAllUsersExceptMyself(HttpServletRequest request) {
        return new ResponseEntity<>(userRepository.getAllUserExceptMyself(getUsernameFromRequest(request)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        return new ResponseEntity<>(userRepository.getProfile(getUsernameFromRequest(request)), HttpStatus.OK);
    }

    private String getUsernameFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return jwtUtil.getUsernameFromToken(token);
    }
}
