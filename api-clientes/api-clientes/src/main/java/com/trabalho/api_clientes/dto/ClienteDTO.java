package com.trabalho.api_clientes.dto;

import com.trabalho.api_clientes.model.Cliente;
import java.time.LocalDate;

public class ClienteDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;

    public ClienteDTO() {}

    // Construtor que cria um DTO a partir de um model Cliente
    private ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.dataNascimento = cliente.getDataNascimento();
        this.cpf = cliente.getCpf();
        this.endereco = cliente.getEndereco();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
    }

    // Método para criar um DTO de Cliente de um Model
    public static ClienteDTO fromModel(Cliente cliente) {
        return new ClienteDTO(cliente);
    }

    // Método para criar exportar um DTO para Model
    public Cliente toModel() {
        Cliente cliente = new Cliente();

        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setDataNascimento(this.dataNascimento);
        cliente.setCpf(this.cpf);
        cliente.setEndereco(this.endereco);
        cliente.setEmail(this.email);
        cliente.setTelefone(this.telefone);

        return cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
