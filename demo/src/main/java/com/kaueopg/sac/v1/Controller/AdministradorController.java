package com.kaueopg.sac.v1.Controller;

import java.util.ArrayList;
import java.util.List;

import com.kaueopg.sac.v1.Model.Administrador;

public class AdministradorController {
    private static final List<Administrador> administradores = new ArrayList<>();

    static {
        administradores.add(Administrador.getMaster());
    }

    public static void adicionar(Administrador admin) {
        if (admin != null) {
            administradores.add(admin);
        }
    }

    public static void editar(String nome, String cpf, String senha, String cpfAntigo) {
        Administrador admin = procurar(cpfAntigo);
        if (admin != null) {
            admin.setNome(nome);
            admin.setCpf(cpf);
            admin.setSenha(senha);
        } else {
            throw new IllegalArgumentException("Administrador não encontrado com o CPF: " + cpfAntigo);
        }
    }

    public static void excluir(String cpf) {
        Administrador admin = procurar(cpf);
        if (admin != null) {
            administradores.remove(admin);
        } else {
            throw new IllegalArgumentException("Administrador não encontrado com o CPF: " + cpf);
        }
    }

    public static Administrador procurar(String cpf) {
        for (Administrador admin : administradores) {
            if (admin.getCpf().equals(cpf)) {
                return admin;
            }
        }
        return null;
    }

    public static List<Administrador> lista() {
        return administradores;
    }
}