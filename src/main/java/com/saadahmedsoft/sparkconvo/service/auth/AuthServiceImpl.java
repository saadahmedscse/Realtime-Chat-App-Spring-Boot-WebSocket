package com.saadahmedsoft.sparkconvo.service.auth;

import com.saadahmedsoft.sparkconvo.dto.auth.LoginRequest;
import com.saadahmedsoft.sparkconvo.dto.auth.LoginResponse;
import com.saadahmedsoft.sparkconvo.dto.common.ApiResponse;
import com.saadahmedsoft.sparkconvo.dto.auth.CreateAccountRequest;
import com.saadahmedsoft.sparkconvo.entity.auth.Token;
import com.saadahmedsoft.sparkconvo.entity.user.User;
import com.saadahmedsoft.sparkconvo.repository.auth.TokenRepository;
import com.saadahmedsoft.sparkconvo.repository.user.UserRepository;
import com.saadahmedsoft.sparkconvo.util.auth.JwtUtil;
import com.saadahmedsoft.sparkconvo.util.common.RandomStringUtil;
import com.saadahmedsoft.sparkconvo.util.common.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String jwtToken = null;

    @Override
    public ResponseEntity<?> createAccount(MultipartFile photo, CreateAccountRequest createAccountRequest) {
        ResponseEntity<?> validationResult = validatorUtil.isCreateAccountRequestValid(photo, createAccountRequest);
        if (validationResult.getStatusCode().equals(HttpStatus.BAD_REQUEST)) return validationResult;

        try {
            String storagePath = "C:/Temp/Uploads/" + RandomStringUtil.getRandomString(16) + "-" + photo.getOriginalFilename();
            Files.copy(photo.getInputStream(), Paths.get(storagePath), StandardCopyOption.REPLACE_EXISTING);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            String creationTime = simpleDateFormat.format(new Date());

            User user = User.builder()
                    .name(createAccountRequest.getName())
                    .email(createAccountRequest.getEmail())
                    .gender(createAccountRequest.getGender())
                    .photo(storagePath)
                    .password(passwordEncoder.encode(createAccountRequest.getPassword()))
                    .createdAt(creationTime)
                    .updatedAt(creationTime).build();

            userRepository.save(user);

            return new ResponseEntity<>(new ApiResponse(true, "Account created successfully"), HttpStatus.CREATED);
        }
        catch (IOException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        ResponseEntity<?> validationResult = validatorUtil.isLoginRequestValid(loginRequest);
        if (validationResult.getStatusCode().isSameCodeAs(HttpStatus.BAD_REQUEST)) return validationResult;

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        jwtToken = jwtUtil.generateToken(userDetails);

        tokenRepository.save(new Token(jwtToken));

        return new ResponseEntity<>(new LoginResponse(true, "Logged in successfully", jwtToken), HttpStatus.CREATED);
    }
}
