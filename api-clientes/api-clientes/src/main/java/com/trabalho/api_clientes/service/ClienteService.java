package com.trabalho.api_clientes.service;

import com.trabalho.api_clientes.dto.ClienteDTO;
import com.trabalho.api_clientes.model.Cliente;
import com.trabalho.api_clientes.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar todos os clientes
    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
    }

    // Buscar por id
    public ClienteDTO buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ClienteDTO.fromModel(cliente.get());
        } else {
            throw new EntityNotFoundException("Cliente não encontrado com id: " + id);
        }
    }

    // Buscar por nome
    public List<ClienteDTO> buscarPorNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
        if (clientes.isEmpty()) {
            throw new EntityNotFoundException("Nenhum cliente encontrado com o nome: " + nome);
        }
        return clientes.stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
    }

    // Salvar cliente no banco de dados
    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        validarCliente(clienteDTO);

        Cliente cliente = clienteDTO.toModel();
        Cliente clienteSalvo = clienteRepository.save(cliente);

        logger.info("Cliente salvo com sucesso: {}", clienteSalvo);

        return ClienteDTO.fromModel(clienteSalvo);
    }

    // Atualizar cliente no banco de dados
    public ClienteDTO atualizar(ClienteDTO clienteDTO) {
        if (clienteDTO.getId() == null) {
            throw new IllegalArgumentException("O id do cliente não pode ser nulo para atualização.");
        }

        Optional<Cliente> clienteExistente = clienteRepository.findById(clienteDTO.getId());

        if (clienteExistente.isEmpty()) {
            throw new EntityNotFoundException("Cliente não encontrado com id: " + clienteDTO.getId());
        }

        validarCliente(clienteDTO);

        Cliente cliente = clienteExistente.get();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());

        Cliente clienteAtualizado = clienteRepository.save(cliente);

        logger.info("Cliente atualizado com sucesso: {}", clienteAtualizado);

        return ClienteDTO.fromModel(clienteAtualizado);
    }

    // Excluir cliente
    public void excluir(Long id) {
        try {
            clienteRepository.deleteById(id);
            logger.info("Cliente com id {} excluído com sucesso.", id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Cliente não encontrado para exclusão com id: " + id);
        }
    }

    // Método para validar se o cliente tem dados essenciais não nulos ou vazios
    private void validarCliente(ClienteDTO clienteDTO) {
        if (clienteDTO.getNome() == null || clienteDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser nulo ou vazio.");
        }
        if (clienteDTO.getEmail() == null || clienteDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O email do cliente não pode ser nulo ou vazio.");
        }
        if (clienteDTO.getTelefone() == null || clienteDTO.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O telefone do cliente não pode ser nulo ou vazio.");
        }
        if (clienteDTO.getCpf() == null || clienteDTO.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente não pode ser nulo ou vazio.");
        }
        if (clienteDTO.getDataNascimento() == null) {
            throw new IllegalArgumentException("A data de nascimento do cliente não pode ser nula.");
        }
    }
}
