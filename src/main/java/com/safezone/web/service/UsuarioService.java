package com.safezone.web.service;

import com.safezone.web.model.Usuario;
import com.safezone.web.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    // Verifica se o usuário já existe no banco pelo email
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Salva o usuário no banco de dados
    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    // Implementação do método loadUserByUsername para UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Encontrar o usuário no banco de dados pelo email
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Retornar um objeto User com a senha criptografada e sem permissões adicionais (se necessário, ajuste as permissões)
        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }
}