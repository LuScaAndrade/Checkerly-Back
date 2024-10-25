package com.Checkerly.BackEnd.resources;

import com.Checkerly.BackEnd.config.JwtUtil;
import com.Checkerly.BackEnd.domain.Event;
import com.Checkerly.BackEnd.dto.LoginRequest;
import com.Checkerly.BackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Checkerly.BackEnd.domain.Organizer;
import com.Checkerly.BackEnd.domain.User;
import com.Checkerly.BackEnd.services.MyUserDetailsService;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/users")
    public String registerUser(@RequestBody User user) {
        userDetailsService.saveUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/organizers")
    public String registerOrganizer(@RequestBody Organizer organizer) {
        userDetailsService.saveOrganizer(organizer);
        return "Organizer registered successfully!";
    }

    @PostMapping("/events")
    public String registerEvent(@RequestBody Event event) {
        userDetailsService.saveEvent(event);
        return "Event registered successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        if (user.isEmpty() || !userDetailsService.isPasswordValid(loginRequest.getPassword(), user.get().getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Credenciais inválidas"));
        }

        String token = jwtUtil.generateToken(user.get().getEmail());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
