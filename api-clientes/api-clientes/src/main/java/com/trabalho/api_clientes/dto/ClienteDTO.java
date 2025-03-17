package com.trabalho.api_clientes.dto;

import com.trabalho.api_clientes.model.Cliente;

import java.time.LocalDate;

public class ClienteDTO {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;

    public ClienteDTO() {}

    public ClienteDTO(int id, String nome, LocalDate dataNascimento, String cpf, String endereco, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    public Cliente toModel() {
        return new Cliente(this.id, this.nome, this.dataNascimento, this.cpf, this.endereco, this.email, this.telefone);
    }

    public static ClienteDTO fromModel(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getDataNascimento(), cliente.getCpf(), cliente.getEndereco(), cliente.getEmail(), cliente.getTelefone());
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
