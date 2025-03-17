package com.trabalho.api_clientes.service;

import com.trabalho.api_clientes.dto.ClienteDTO;
import com.trabalho.api_clientes.model.Cliente;
import com.trabalho.api_clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    private static final int TAMANHO_PAGINA = 4;

    // Listar todos os clientes paginados
    public List<ClienteDTO> listarTodos(int pagina) {
        Pageable pageable = PageRequest.of(pagina, TAMANHO_PAGINA);
        Page<Cliente> clientesPage = clienteRepository.findAll(pageable);
        return clientesPage.getContent()
                .stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
    }

    // Buscar clientes por nome paginados
    public List<ClienteDTO> buscarPorNome(String nome, int pagina) {
        Pageable pageable = PageRequest.of(pagina, TAMANHO_PAGINA);
        Page<Cliente> clientesPage = clienteRepository.findByNomeContainingIgnoreCase(nome, pageable); // Corrigido para findByNomeContaining
        return clientesPage.getContent()
                .stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
    }

    // Buscar cliente por ID
    public ClienteDTO buscarPorId(int id) { // Corrigido para Long
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ClienteDTO::fromModel).orElse(null);
    }

    // Salvar um novo cliente
    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        Cliente cliente = clienteDTO.toModel();
        Cliente salvo = clienteRepository.save(cliente);
        return ClienteDTO.fromModel(salvo);
    }

    // Atualizar um cliente existente
    public ClienteDTO atualizar(ClienteDTO clienteDTO) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(clienteDTO.getId());
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setTelefone(clienteDTO.getTelefone());
            cliente.setEndereco(clienteDTO.getEndereco());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setDataNascimento(clienteDTO.getDataNascimento());

            Cliente clienteAtualizado = clienteRepository.save(cliente);
            return ClienteDTO.fromModel(clienteAtualizado);
        } else {
            return null; // Cliente não encontrado
        }
    }

    // Excluir um cliente por ID
    public void excluir(int id) { // Corrigido para Long
        clienteRepository.deleteById(id);
    }
}