package com.kaueopg.sac.v1.Controller;

import com.kaueopg.sac.v1.Model.Prontuario;
import com.kaueopg.sac.v1.Model.Paciente;
import java.util.HashMap;
import java.util.Map;

public class ProntuarioController {
    private static Map<Paciente, Prontuario> prontuarios = new HashMap<>();

    public static void adicionar(Paciente paciente, Prontuario prontuario) {
        prontuarios.put(paciente, prontuario);
    }

    public static void excluir(Paciente paciente) {
        prontuarios.remove(paciente);
    }

    public static Prontuario procurar(Paciente paciente) {
        return prontuarios.get(paciente);
    }

    public static boolean confereVazia() {
        return prontuarios.isEmpty();
    }

    public static Map<Paciente, Prontuario> lista() {
        return prontuarios;
    }
}