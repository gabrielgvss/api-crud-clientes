package com.trabalho.api_clientes.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento no banco de dados
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)  // Nome não pode ser nulo e tem comprimento de 100 caracteres
    private String nome;

    @Column(name = "data_nascimento", nullable = false)  // Coluna de data de nascimento, não pode ser nula
    private LocalDate dataNascimento;

    @Column(name = "cpf", nullable = false, length = 14)  // CPF não pode ser nulo e deve ter exatamente 14 caracteres
    private String cpf;

    @Column(name = "endereco", length = 100)  // Endereço pode ser nulo e ter até 100 caracteres
    private String endereco;

    @Column(name = "telefone", length = 20)  // Telefone pode ser nulo e tem um comprimento de até 20 caracteres
    private String telefone;

    @Column(name = "email", length = 50)  // Email pode ser nulo e tem comprimento máximo de 50 caracteres
    private String email;

    // Construtor vazio necessário para o JPA
    public Cliente() {}

    // Construtor com parâmetros
    public Cliente(String nome, String cpf, LocalDate dataNascimento, String endereco, String telefone, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", data de nascimento = " + dataNascimento +
                ", cpf = '" + cpf + '\'' +
                ", endereco = '" + endereco + '\'' +
                ", telefone = '" + telefone + '\'' +
                ", email = '" + email + '\'' +
                '}' + '\n';
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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
