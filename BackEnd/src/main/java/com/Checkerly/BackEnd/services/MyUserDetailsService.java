//package com.Checkerly.BackEnd.services;
//
//import com.Checkerly.BackEnd.domain.Event;
//import com.Checkerly.BackEnd.repository.EventRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.Checkerly.BackEnd.domain.Organizer;
//import com.Checkerly.BackEnd.domain.User;
//
//import com.Checkerly.BackEnd.repository.OrganizerRepository;
//import com.Checkerly.BackEnd.repository.UserRepository;
//
//import java.util.Optional;
//import java.util.ArrayList;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private OrganizerRepository organizerRepository;
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // Busca primeiro no repositório de usuários
//        Optional<User> user = userRepository.findByEmail(email);
//        if (user.isPresent()) {
//            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getSenha(), new ArrayList<>());
//        }
//
//        // Se não encontrar no repositório de usuários, busca nos organizadores
//        Optional<Organizer> organizer = organizerRepository.findByEmail(email);
//        if (organizer.isPresent()) {
//            return new org.springframework.security.core.userdetails.User(organizer.get().getEmail(), organizer.get().getSenha(), new ArrayList<>());
//        }
//
//        throw new UsernameNotFoundException("Email não encontrado: " + email);
//    }
//
//    // Salvar usuários com senha criptografada
//    public User saveUser(User user) {
//        user.setSenha(passwordEncoder.encode(user.getSenha()));
//        return userRepository.save(user);
//    }
//
//    // Salvar organizadores com senha criptografada
//    public Organizer saveOrganizer(Organizer organizer) {
//        organizer.setSenha(passwordEncoder.encode(organizer.getSenha()));
//        return organizerRepository.save(organizer);
//    }
//
//    public Event saveEvent(Event event) {
//        return eventRepository.save(event);
//    }
//}

package com.Checkerly.BackEnd.services;

import com.Checkerly.BackEnd.domain.Event;
import com.Checkerly.BackEnd.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Checkerly.BackEnd.domain.Organizer;
import com.Checkerly.BackEnd.domain.User;

import com.Checkerly.BackEnd.repository.OrganizerRepository;
import com.Checkerly.BackEnd.repository.UserRepository;

import java.util.Optional;
import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca primeiro no repositório de usuários
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getSenha(), new ArrayList<>());
        }

        // Se não encontrar no repositório de usuários, busca nos organizadores
        Optional<Organizer> organizer = organizerRepository.findByEmail(email);
        if (organizer.isPresent()) {
            return new org.springframework.security.core.userdetails.User(organizer.get().getEmail(), organizer.get().getSenha(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("Email não encontrado: " + email);
    }

    // Salvar usuários com senha criptografada
    public User saveUser(User user) {
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    // Salvar organizadores com senha criptografada
    public Organizer saveOrganizer(Organizer organizer) {
        organizer.setSenha(passwordEncoder.encode(organizer.getSenha()));
        return organizerRepository.save(organizer);
    }
}