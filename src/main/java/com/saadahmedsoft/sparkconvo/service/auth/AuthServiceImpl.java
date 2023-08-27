package com.saadahmedsoft.sparkconvo.service.auth;

import com.saadahmedsoft.sparkconvo.dto.common.ApiResponse;
import com.saadahmedsoft.sparkconvo.dto.user.UserRequest;
import com.saadahmedsoft.sparkconvo.entity.user.User;
import com.saadahmedsoft.sparkconvo.repository.user.UserRepository;
import com.saadahmedsoft.sparkconvo.util.auth.RandomStringUtil;
import com.saadahmedsoft.sparkconvo.util.auth.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> createAccount(MultipartFile photo, UserRequest userRequest) {
        ResponseEntity<?> validationResult = validatorUtil.isCreateAccountRequestValid(photo, userRequest);
        if (validationResult.getStatusCode().equals(HttpStatus.BAD_REQUEST)) return validationResult;

        try {
            String storagePath = "C:/Temp/Uploads/" + RandomStringUtil.getRandomString(16) + " " + photo.getOriginalFilename();
            Files.copy(photo.getInputStream(), Paths.get(storagePath), StandardCopyOption.COPY_ATTRIBUTES);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            String creationTime = simpleDateFormat.format(new Date());

            User user = User.builder()
                    .name(userRequest.getName())
                    .email(userRequest.getEmail())
                    .gender(userRequest.getGender())
                    .photo(storagePath)
                    .password(userRequest.getPassword())
                    .createdAt(creationTime)
                    .updatedAt(creationTime).build();

            userRepository.save(user);

            return new ResponseEntity<>(new ApiResponse(true, "Account created successfully"), HttpStatus.CREATED);
        }
        catch (IOException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
