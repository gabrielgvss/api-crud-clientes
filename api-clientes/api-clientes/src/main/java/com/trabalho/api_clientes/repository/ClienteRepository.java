package com.trabalho.api_clientes.repository;

import com.trabalho.api_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// A interface JpaRepository já possui implementações prontas para os métodos CRUD
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método para buscar cliente por nome
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

}