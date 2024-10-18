package com.Checkerly.BackEnd.services

import com.Checkerly.BackEnd.domain.Organizer
import com.Checkerly.BackEnd.domain.User
import com.Checkerly.BackEnd.repository.OrganizerRepository
import com.Checkerly.BackEnd.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.ArrayList
import java.util.Optional

@Service
class MyUserDetailsService : UserDetailsService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val organizerRepository: OrganizerRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Override
    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(email: String?): UserDetails? {
        // Busca primeiro no repositório de usuários
        val user: Optional<User?>? = userRepository.findByEmail(email)
        if (user.isPresent()) {
            return User(user.get().getEmail(), user.get().getSenha(), ArrayList())
        }

        // Se não encontrar no repositório de usuários, busca nos organizadores
        val organizer: Optional<Organizer?>? = organizerRepository.findByEmail(email)
        if (organizer.isPresent()) {
            return User(organizer.get().getEmail(), organizer.get().getSenha(), ArrayList())
        }

        throw UsernameNotFoundException("Email não encontrado: " + email)
    }

    // Salvar usuários com senha criptografada
    fun saveUser(user: User): User? {
        user.setSenha(passwordEncoder.encode(user.getSenha()))
        return userRepository.save(user)
    }

    // Salvar organizadores com senha criptografada
    fun saveOrganizer(organizer: Organizer): Organizer? {
        organizer.setSenha(passwordEncoder.encode(organizer.getSenha()))
        return organizerRepository.save(organizer)
    }
}