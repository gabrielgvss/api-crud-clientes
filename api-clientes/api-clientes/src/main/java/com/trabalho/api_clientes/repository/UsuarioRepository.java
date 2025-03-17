package com.trabalho.api_clientes.repository;

import com.trabalho.api_clientes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Busca usuário pelo login:
    UserDetails findByLogin(String login);

}
