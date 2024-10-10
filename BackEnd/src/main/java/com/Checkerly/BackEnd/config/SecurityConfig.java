package com.Checkerly.BackEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true); // Se necessário para enviar cookies/autenticação
            }
        };
    }
  
    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())  // Desabilita CSRF
        .authorizeRequests(auth -> auth
            .requestMatchers("/login").permitAll()
            .requestMatchers("/users/**", "/organizers/**", "/events/**").authenticated()  // Requer autenticação para essas URLs
            //.anyRequest().permitAll()  // Permite todos os outros acessos
        )
        
        .formLogin(form -> form
            .loginPage("/login")    
            .defaultSuccessUrl("/dashboard", true)  // Redireciona para /dashboard após login bem-sucedido
            .permitAll()  // Permite o acesso à página de login
        )
        .logout(logout -> logout.permitAll());
        
        return http.build();
    }
}