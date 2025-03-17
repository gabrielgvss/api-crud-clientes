package com.trabalho.api_clientes.controller;

import com.trabalho.api_clientes.dto.ClienteDTO;
import com.trabalho.api_clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes") // Define o caminho base para todos os endpoints
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar todos os clientes (paginado)
    @GetMapping
    public List<ClienteDTO> listarTodos(
            @RequestParam(defaultValue = "0") int pagina, // Número da página
            @RequestParam(required = false) String nome) { // Filtro por nome (opcional)

        if (nome != null && !nome.isEmpty()) {
            // Se o parâmetro "nome" for fornecido, busca clientes por nome
            return clienteService.buscarPorNome(nome, pagina);
        } else {
            // Caso contrário, lista todos os clientes
            return clienteService.listarTodos(pagina);
        }
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable int id) {
        return clienteService.buscarPorId(id);
    }

    // Salvar um novo cliente
    @PostMapping
    public ClienteDTO salvar(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.salvar(clienteDTO);
    }

    // Atualizar um cliente existente
    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable int id, @RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setId(id); // Garante que o ID do DTO seja o mesmo da URL
        return clienteService.atualizar(clienteDTO);
    }

    // Excluir um cliente por ID
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable int id) {
        clienteService.excluir(id);
    }
}