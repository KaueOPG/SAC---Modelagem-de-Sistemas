package com.kaueopg.sac.v1.Model;

public class Usuario {
    private String cpf;
    private String senha;
    private String nome;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}