package com.trabalho.api_clientes.dto;

import com.trabalho.api_clientes.model.Usuario;

public class UsuarioDTO {
    private Long id;
    private String login;
    private String senha;

    private UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getUsername();
        this.senha = usuario.getPassword();

    }

    // Gerar DTO de usuário a partir de um model do BD  de usuários
    public static UsuarioDTO fromModel(Usuario usuario) {
        return new UsuarioDTO(usuario);
    }

    public Usuario toModel() {
        Usuario usuario = new Usuario();

        usuario.setId(this.id);
        usuario.setLogin(this.login);
        usuario.setSenha(this.senha);

        return usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
