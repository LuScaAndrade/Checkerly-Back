//package com.Checkerly.BackEnd.resources;
//
//import com.Checkerly.BackEnd.domain.Event;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.Checkerly.BackEnd.domain.Organizer;
//import com.Checkerly.BackEnd.domain.User;
//import com.Checkerly.BackEnd.services.MyUserDetailsService;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @PostMapping("/users")
//    public String registerUser(@RequestBody User user) {
//        userDetailsService.saveUser(user);
//        return "User registered successfully!";
//    }
//
//    @PostMapping("/organizers")
//    public String registerOrganizer(@RequestBody Organizer organizer) {
//        userDetailsService.saveOrganizer(organizer);
//        return "Organizer registered successfully!";
//    }
//
//    @PostMapping("/events")
//    public String registerEvent(@RequestBody Event event) {
//        userDetailsService.saveEvent(event);
//        return "Event registered successfully";
//    }
//}

package com.Checkerly.BackEnd.resources;

import com.Checkerly.BackEnd.domain.Event;
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
