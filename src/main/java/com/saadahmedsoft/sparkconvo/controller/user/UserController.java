package com.saadahmedsoft.sparkconvo.controller.user;

import com.saadahmedsoft.sparkconvo.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getUserExceptMyself(HttpServletRequest request) {
        return userService.getAllUsersExceptMyself(request);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        return userService.getProfile(request);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getOtherProfile(@PathVariable("id") long id) {
        return userService.getOtherProfile(id);
    }
}
