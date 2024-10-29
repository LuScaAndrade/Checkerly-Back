package com.Checkerly.BackEnd.Security;

import com.Checkerly.BackEnd.domain.Organizer;
import com.Checkerly.BackEnd.domain.User;
import com.Checkerly.BackEnd.repository.OrganizerRepository;
import com.Checkerly.BackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class CustomUserDetailsService  implements UserDetailsService {
    //    @Autowired
//    private UserRepository repository;
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
////        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(), new ArrayList<>());
////    }
//}
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Primeiro, tenta buscar no repositório de usuários
        User user = userRepository.findByEmail(username).orElse(null);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getSenha(),
                    new ArrayList<>()
            );
        }

        // Caso não encontre o usuário, busca no repositório de organizadores
        Organizer organizer = organizerRepository.findByEmail(username).orElse(null);

        if (organizer != null) {
            return new org.springframework.security.core.userdetails.User(
                    organizer.getEmail(),
                    organizer.getSenha(),
                    new ArrayList<>()
            );
        }

        // Lança exceção se o email não for encontrado em nenhum repositório
        throw new UsernameNotFoundException("User or Organizer not found");
    }
}
