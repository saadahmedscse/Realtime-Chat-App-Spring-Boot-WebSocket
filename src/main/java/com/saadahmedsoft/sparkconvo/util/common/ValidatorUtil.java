package com.saadahmedsoft.sparkconvo.util.common;

import com.saadahmedsoft.sparkconvo.dto.common.ApiResponse;
import com.saadahmedsoft.sparkconvo.dto.user.UserRequest;
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

    public ResponseEntity<?> isCreateAccountRequestValid(MultipartFile photo, UserRequest userRequest) {
        if (photo == null || photo.isEmpty()) return generateCommonBadRequestResponse("Profile picture is required");
        if (userRequest.getName() == null || userRequest.getName().isBlank()) return generateCommonBadRequestResponse("Name is required");
        if (userRequest.getEmail() == null || userRequest.getEmail().isBlank()) return generateCommonBadRequestResponse("Email is required");
        if (!userRequest.getEmail().matches(EMAIL_PATTERN)) return generateCommonBadRequestResponse("Invalid email address");
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) return generateCommonBadRequestResponse("Email already exist");
        if (userRequest.getGender() == null || userRequest.getGender().isBlank()) return generateCommonBadRequestResponse("Gender is required");
        if (userRequest.getPassword() == null || userRequest.getPassword().isBlank()) return generateCommonBadRequestResponse("Password is required");
        if (userRequest.getConfirmPassword() == null || userRequest.getConfirmPassword().isBlank()) return generateCommonBadRequestResponse("Confirm password is required");
        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) return generateCommonBadRequestResponse("Password didn't match");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<?> generateCommonBadRequestResponse(String message) {
        return new ResponseEntity<>(new ApiResponse(false, message), HttpStatus.BAD_REQUEST);
    }
}
