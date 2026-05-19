package com.sum.Calculator.controller;

import com.sum.Calculator.models.User;
import com.sum.Calculator.repository.UserRepository;
import com.sum.Calculator.service.UserService;
import com.sum.Calculator.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        if (userRepository.findByEmail(email).isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }

        userService.createUser(
                User.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .build()
        );
        return new ResponseEntity<>("Successfully registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        var userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not registered", HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
