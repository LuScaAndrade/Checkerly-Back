package com.Checkerly.BackEnd.Security;

import com.Checkerly.BackEnd.repository.OrganizerRepository;
import com.Checkerly.BackEnd.repository.UserRepository;
import com.Checkerly.BackEnd.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            // Busca o login no repositório de usuários
            var user = userRepository.findByEmail(login).orElse(null);
            if (user != null) {
                setAuthentication(user, "ROLE_USER");
            } else {
                // Caso o usuário não seja encontrado, tenta buscar no repositório de organizadores
                var organizer = organizerRepository.findByEmail(login).orElse(null);
                if (organizer != null) {
                    setAuthentication(organizer, "ROLE_ORGANIZER");
                } else {
                    throw new RuntimeException("User or Organizer Not Found");
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Object user, String role) {
        var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}