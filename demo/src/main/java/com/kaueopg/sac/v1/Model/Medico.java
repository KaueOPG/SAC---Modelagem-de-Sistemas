package com.kaueopg.sac.v1.Model;

public class Medico extends Usuario {
    private String especializacao;
    private double valor;

    public Medico() {
    }

    public Medico(String nome, String cpf, String senha, String especializacao, double valor) {
        super(nome,cpf, senha);
        this.especializacao = especializacao;
        this.valor = valor;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
