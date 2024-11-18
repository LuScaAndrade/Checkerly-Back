package com.Checkerly.BackEnd.resources;

import com.Checkerly.BackEnd.Security.TokenService;
import com.Checkerly.BackEnd.domain.Event;
import com.Checkerly.BackEnd.domain.Organizer;
import com.Checkerly.BackEnd.domain.User;
import com.Checkerly.BackEnd.dto.*;
import com.Checkerly.BackEnd.repository.EventRepository;
import com.Checkerly.BackEnd.repository.OrganizerRepository;
import com.Checkerly.BackEnd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private EventRepository eventRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailController emailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        Optional<User> userOptional = userRepository.findByEmail(body.email());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(body.password(), user.getSenha())) {
                String token = tokenService.generateUserToken(user);
                return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
            } else {
                // Senha incorreta
                return ResponseEntity.badRequest().body(Map.of("message", "senha incorreta"));
            }
        } else {
            Optional<Organizer> organizerOptional = organizerRepository.findByEmail(body.email());

            if (organizerOptional.isPresent()) {
                Organizer organizer = organizerOptional.get();
                if (passwordEncoder.matches(body.password(), organizer.getSenha())) {
                    String token = tokenService.generateOrganizerToken(organizer);
                    return ResponseEntity.ok(new ResponseDTO(organizer.getName(), token));
                } else {
                    // Senha incorreta
                    return ResponseEntity.badRequest().body(Map.of("message", "senha incorreta"));
                }
            }
        }
        // Email incorreto
        return ResponseEntity.badRequest().body(Map.of("message", "email incorreto"));
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO body) {
        if (userRepository.findByEmail(body.email()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        User newUser = new User();
        newUser.setSenha(passwordEncoder.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setName(body.name());
        userRepository.save(newUser);

        String token = tokenService.generateUserToken(newUser);

        ResponseCookie tokenCookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(2 * 60 * 60)
                .build();
        return ResponseEntity.ok()
                .header("Set-Cookie", tokenCookie.toString())
                .body(new ResponseDTO(newUser.getName(), "User registered successfuly"));
    }

    @PostMapping("/register/organizer")
    public ResponseEntity<?> registerOrganizer(@RequestBody RegisterRequestDTO body) {
        if (organizerRepository.findByEmail(body.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Organizer already exists");
        }
        Organizer newOrganizer = new Organizer();
        newOrganizer.setSenha(passwordEncoder.encode(body.password()));
        newOrganizer.setEmail(body.email());
        newOrganizer.setName(body.name());
        organizerRepository.save(newOrganizer);

        String token = tokenService.generateOrganizerToken(newOrganizer);

        ResponseCookie tokenCookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(2 * 60 * 60)
                .build();
        return ResponseEntity.ok()
                .header("Set-Cookie", tokenCookie.toString())
                .body(new ResponseDTO(newOrganizer.getName(), "Organizer registered successfuly"));
    }

    @PostMapping("/register/event")
    public ResponseEntity<?> registerEvent(@RequestBody EventDTO body){
        if (body.getId() == null) {
            return ResponseEntity.badRequest().body("Event ID must not be null");
        }

        // Verifica se o evento já existe
        if (eventRepository.findById(body.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("Event already exists");
        }
        Event newEvent = new Event();
        newEvent.setNomeEvento(body.getNomeEvento());
        newEvent.setAssuntoEvento(body.getAssuntoEvento());
        newEvent.setLatitude(body.getLatitude());
        newEvent.setLongitude(body.getLongitude());
        newEvent.setDataInicio(body.getDataInicio());
        newEvent.setDataFim(body.getDataFim());
        newEvent.setHoraEvento(body.getHoraEvento());
        eventRepository.save(newEvent);

        String token = tokenService.generateEventToken(newEvent);

        ResponseCookie tokenCookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(2 * 60 * 60)
                .build();
        return ResponseEntity.ok()
                .header("Set-Cookie", tokenCookie.toString())
                .body(new ResponseDTO(newEvent.getId(), "Event registered successfuly"));
    }

    @PostMapping("/validate/qr")
    public ResponseEntity<?> validateQr(@RequestBody EmailDTO emailDTO) {
        // Obtenha os dados do QR code
        String name = emailDTO.name();
        String id = emailDTO.id();
        String pdfPath = emailDTO.pdfPath();

        // Enviar o e-mail com o certificado
        emailService.sendEmail(emailDTO, name, id);

        return ResponseEntity.ok("Certificado enviado com sucesso.");
    }
}