package com.kaueopg.sac.v1.Controller;

import java.util.ArrayList;
import java.util.List;

import com.kaueopg.sac.v1.Model.Medico;

public class MedicoController {
    private static List<Medico> medicos = new ArrayList<>();

    static {
        medicos.add(new Medico("João Silva", "12345678900", "senha123", "Cardiologia", 300.0));
        medicos.add(new Medico("Maria Souza", "98765432100", "senha456", "Dermatologia", 250.0));
    }

    public static void adicionar(String nome, String cpf, String senha, String especializacao, double valor) {
        Medico medico = new Medico(nome, cpf, senha, especializacao, valor);
        medicos.add(medico);
    }

    public static void excluir(String cpf) {
        Medico medico = procurar(cpf);
        if (medico != null) {
            medicos.remove(medico);
        }
    }

    public static boolean editar(String nome, String cpf, String senha, String especializacao, double valor, String cpfAtual) {
        Medico medico = procurar(cpfAtual);
        if (medico == null) {
            return false;
        }

        if (!nome.isEmpty()) {
            medico.setNome(nome);
        }

        if (!senha.isEmpty()) {
            medico.setSenha(senha);
        }

        if (!cpf.isEmpty()) {
            medico.setCpf(cpf);
        }

        if (!especializacao.isEmpty()) {
            medico.setEspecializacao(especializacao);
        }

        if (valor > 0) {
            medico.setValor(valor);
        }

        return true;
    }

    public static boolean confereVazia() {
        return medicos.isEmpty();
    }

    public static Medico procurar(String cpf) {
        for (Medico medico : medicos) {
            if (medico.getCpf().equals(cpf)) {
                return medico;
            }
        }
        return null;
    }

    public static List<Medico> lista() {
        return medicos;
    }
}