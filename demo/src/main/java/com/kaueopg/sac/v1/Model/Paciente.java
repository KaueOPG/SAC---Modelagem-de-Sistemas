package com.kaueopg.sac.v1.Model;

import java.util.ArrayList;

public class Paciente extends Usuario {
    private String dataNascimento;
    private String contato;
    private Prontuario prontuario;

    public Paciente() {
    }

    public Paciente(String nome, String cpf, String senha, String dataNascimento, String contato) {
        super(nome, cpf, senha);
        this.dataNascimento = dataNascimento;
        this.contato = contato;
        Prontuario prontuario = new Prontuario();
        prontuario.setTipoSanguineo(""); 
        prontuario.setAlergias(new ArrayList<>()); 
        prontuario.setCirurgias(new ArrayList<>()); 
        prontuario.setMedicamentos(new ArrayList<>()); 
        prontuario.setOutros(""); 
        this.prontuario = prontuario;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }
}
