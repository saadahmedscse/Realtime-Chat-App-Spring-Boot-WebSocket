package com.saadahmedsoft.sparkconvo.util.common;

import com.saadahmedsoft.sparkconvo.dto.auth.LoginRequest;
import com.saadahmedsoft.sparkconvo.dto.common.ApiResponse;
import com.saadahmedsoft.sparkconvo.dto.auth.CreateAccountRequest;
import com.saadahmedsoft.sparkconvo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ValidatorUtil {

    private final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> isCreateAccountRequestValid(MultipartFile photo, CreateAccountRequest createAccountRequest) {
        if (photo == null || photo.isEmpty()) return generateCommonBadRequestResponse("Profile picture is required");
        if (createAccountRequest.getName() == null || createAccountRequest.getName().isBlank()) return generateCommonBadRequestResponse("Name is required");
        if (createAccountRequest.getEmail() == null || createAccountRequest.getEmail().isBlank()) return generateCommonBadRequestResponse("Email is required");
        if (!createAccountRequest.getEmail().matches(EMAIL_PATTERN)) return generateCommonBadRequestResponse("Invalid email address");
        if (userRepository.findByEmail(createAccountRequest.getEmail()).isPresent()) return generateCommonBadRequestResponse("Email already exist");
        if (createAccountRequest.getGender() == null || createAccountRequest.getGender().isBlank()) return generateCommonBadRequestResponse("Gender is required");
        if (createAccountRequest.getPassword() == null || createAccountRequest.getPassword().isBlank()) return generateCommonBadRequestResponse("Password is required");
        if (createAccountRequest.getConfirmPassword() == null || createAccountRequest.getConfirmPassword().isBlank()) return generateCommonBadRequestResponse("Confirm password is required");
        if (!createAccountRequest.getPassword().equals(createAccountRequest.getConfirmPassword())) return generateCommonBadRequestResponse("Password didn't match");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> isLoginRequestValid(LoginRequest loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isBlank()) return generateCommonBadRequestResponse("Email is required");
        if (!loginRequest.getEmail().matches(EMAIL_PATTERN)) return generateCommonBadRequestResponse("Invalid email address");
        if (userRepository.findByEmail(loginRequest.getEmail()).isEmpty()) return generateCommonBadRequestResponse("Email not found");
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) return generateCommonBadRequestResponse("Password is required");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<?> generateCommonBadRequestResponse(String message) {
        return new ResponseEntity<>(new ApiResponse(false, message), HttpStatus.BAD_REQUEST);
    }
}
