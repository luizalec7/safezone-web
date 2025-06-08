package com.safezone.web.config;

import com.safezone.web.model.Usuario;
import com.safezone.web.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioService usuarioService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        String nome = oauthUser.getAttribute("name");

        // Verifica se já existe o usuário
        Optional<Usuario> optionalUsuario = usuarioService.buscarPorEmail(email);
        Usuario usuario;

        if (optionalUsuario.isEmpty()) {
            // Cria novo usuário no banco
            usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setNome(nome);
            usuario.setSenha(""); // Não precisa senha nesse caso
            usuarioService.salvar(usuario);
        } else {
            usuario = optionalUsuario.get();
        }

        // Salva o ID do usuário na sessão
        HttpSession session = request.getSession();
        session.setAttribute("usuarioId", usuario.getId());

        // Redireciona para home
        response.sendRedirect("/home");
    }
}
