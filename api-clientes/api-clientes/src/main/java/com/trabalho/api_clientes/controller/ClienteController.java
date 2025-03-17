package com.trabalho.api_clientes.controller;

import com.trabalho.api_clientes.dto.ClienteDTO;
import com.trabalho.api_clientes.model.Cliente;
import com.trabalho.api_clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes") // Url básica para endpoints
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    // Listar todos os clientes:
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<ClienteDTO> clientes = clienteService.listarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        try{
            ClienteDTO clienteDTO = clienteService.buscarPorId(id);
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Buscar cliente por nome
    @GetMapping ("/search")
    public ResponseEntity<List<ClienteDTO>> buscarPorNome(@RequestParam String nome) {
        try{
            List<ClienteDTO> clientes = clienteService.buscarPorNome(nome);
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Salvar novo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@RequestBody ClienteDTO clienteDTO) {
        try{
            ClienteDTO clienteDTOSalva = clienteService.salvar(clienteDTO);
            return new ResponseEntity<>(clienteDTOSalva, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try{
            clienteDTO.setId(id); // Definindo id do cliente que será atualizado
            ClienteDTO clienteDTOSalva = clienteService.atualizar(clienteDTO);
            return new ResponseEntity<>(clienteDTOSalva, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Excluir registro de cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> excluir(@PathVariable Long id) {
        try{
            clienteService.excluir(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
