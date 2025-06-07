package com.safezone.web.config;

import com.safezone.web.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioService usuarioService;

    // Configura o PasswordEncoder para a senha criptografada
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura o AuthenticationManager para carregar o usuário e usar a senha criptografada
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
        return builder.build();
    }

    // Configura o filtro de segurança para as páginas e autenticação
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/register", "/login", "/forgot-password", "/css/**", "/js/**", "/image/**", "/webjars/**", "/favicon.ico") // Permite acesso sem autenticação
                .permitAll()
                .anyRequest().authenticated() // Exige autenticação para todas as outras requisições
                .and()
                .formLogin()
                .loginPage("/login") // Página de login personalizada
                .defaultSuccessUrl("/home", true) // Redireciona para a página home após login
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // Redireciona para login após logout
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/login") // Página de login personalizada para login do Google
                .defaultSuccessUrl("/home", true) // Redireciona para a página home após login do Google
                .and()
                .csrf().disable(); // Desabilita a proteção CSRF (opcional, caso não esteja usando)

        return http.build();
    }
}