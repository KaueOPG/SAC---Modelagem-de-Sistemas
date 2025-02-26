package com.kaueopg.sac.v1.Model;

public class Administrador extends Usuario {
    private static final Administrador master = new Administrador("Master", "00000000000", "admin123");

    public Administrador(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    public static Administrador getMaster() {
        return master;
    }
}