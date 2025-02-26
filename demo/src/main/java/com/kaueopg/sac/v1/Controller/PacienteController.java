package com.kaueopg.sac.v1.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kaueopg.sac.v1.Model.Paciente;
import com.kaueopg.sac.v1.Model.Prontuario;

public class PacienteController {
    private static List<Paciente> pacientes = new ArrayList<>();

    static {
        Prontuario prontuario1 = new Prontuario();
        prontuario1.setTipoSanguineo("O+");
        prontuario1.setAlergias(Arrays.asList("Pólen", "Amendoim"));
        prontuario1.setCirurgias(Arrays.asList("Apendicectomia"));
        prontuario1.setMedicamentos(Arrays.asList("Paracetamol", "Ibuprofeno"));
        prontuario1.setOutros("Histórico de asma na infância");

        Prontuario prontuario2 = new Prontuario();
        prontuario2.setTipoSanguineo("A-");
        prontuario2.setAlergias(Arrays.asList("Penicilina"));
        prontuario2.setCirurgias(Arrays.asList("Colecistectomia", "Herniorrafia"));
        prontuario2.setMedicamentos(Arrays.asList("Omeprazol"));
        prontuario2.setOutros("Sem histórico familiar relevante");

        Paciente paciente1 = new Paciente("Ana Clara", "11122233344", "senha789", "10/05/1995", "11999999999");
        Paciente paciente2 = new Paciente("Carlos Eduardo", "55566677788", "senha101", "22/11/1988", "11888888888");
        
        paciente1.setProntuario(prontuario1);
        paciente2.setProntuario(prontuario2);

        pacientes.add(paciente1);
        pacientes.add(paciente2);
    }

    public static void adicionar(String nome, String cpf, String senha, String dataNascimento, String contato) {
        Paciente paciente = new Paciente(nome, cpf, senha, dataNascimento, contato);
        pacientes.add(paciente);
    }

    public static void excluir(String cpf) {
        Paciente paciente = procurar(cpf);
        if (paciente != null) {
            pacientes.remove(paciente);
        }
    }

    public static boolean editar(String nome, String cpf, String senha, String dataNascimento, String contato, String cpfAtual) {
        Paciente paciente = procurar(cpfAtual);
        if (paciente == null) {
            return false;
        }
    
        if (!nome.isEmpty()) {
            paciente.setNome(nome);
        }
    
        if (!senha.isEmpty()) {
            paciente.setSenha(senha);
        }
    
        if (!cpf.isEmpty()) {
            paciente.setCpf(cpf);
        }
    
        if (!dataNascimento.isEmpty()) {
            paciente.setDataNascimento(dataNascimento);
        }
    
        if (!contato.isEmpty()) {
            paciente.setContato(contato);
        }
    
        return true;
    }

    public static boolean editarProntuario(Prontuario prontuario, String cpfAtual) {
        Paciente paciente = procurar(cpfAtual);
        if (paciente == null) {
            return false;
        }
    
        if (prontuario != null) {
            paciente.setProntuario(prontuario);
        }
    
        return true;
    }

    public static boolean confereVazia() {
        return pacientes.isEmpty();
    }

    public static Paciente procurar(String cpf) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }
        return null;
    }

    public static List<Paciente> lista() {
        return pacientes;
    }
}