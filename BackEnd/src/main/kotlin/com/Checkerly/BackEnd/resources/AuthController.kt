package com.Checkerly.BackEnd.resources

import com.Checkerly.BackEnd.domain.Organizer
import com.Checkerly.BackEnd.domain.User
import com.Checkerly.BackEnd.services.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private val userDetailsService: MyUserDetailsService? = null

    @PostMapping("/users")
    fun registerUser(@RequestBody user: User?): String? {
        userDetailsService.saveUser(user)
        return "User registered successfully!"
    }

    @PostMapping("/organizers")
    fun registerOrganizer(@RequestBody organizer: Organizer?): String? {
        userDetailsService.saveOrganizer(organizer)
        return "Organizer registered successfully!"
    }
}
