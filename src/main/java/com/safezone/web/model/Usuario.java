package com.safezone.web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    @Column(unique = true)
    private String email;

    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @Column(nullable = false)
    private String perfil = "USER";

    private LocalDate dataCadastro = LocalDate.now();
}