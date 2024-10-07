package com.Checkerly.BackEnd.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Checkerly.BackEnd.domain.Organizer;
import com.Checkerly.BackEnd.domain.User;
import com.Checkerly.BackEnd.services.MyUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private MyUserDetailsService userDetailsService;

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
}
