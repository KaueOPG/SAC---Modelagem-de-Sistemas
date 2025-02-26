package com.kaueopg.sac.v1.Controller;

import com.kaueopg.sac.v1.Model.Consulta;
import java.util.ArrayList;
import java.util.List;

public class ConsultaController {
    private static List<Consulta> consultas = new ArrayList<>();

    static {
        consultas.add(new Consulta("10/10/2023", "10:00", "11122233344", "12345678900"));  
        consultas.add(new Consulta("15/10/2023", "14:00", "55566677788", "98765432100")); 
    }

    public static Consulta cadastrar(String cpfPaciente, String cpfMedico, String data, String horario) {
        if (!verificarDisponibilidade(cpfMedico, data, horario)) {
            return null;
        }
        Consulta consulta = new Consulta(data, horario, cpfPaciente, cpfMedico);
        consulta.setStatus(true);
        consultas.add(consulta);
        return consulta;
    }

    public static void excluir(String cpfPaciente, String cpfMedico, String data, String horario) {
        Consulta consulta = procurar(cpfPaciente, cpfMedico, data, horario);
        if (consulta != null) {
            consultas.remove(consulta);
        }
    }

    public static boolean verificarDisponibilidade(String cpfMedico, String data, String horario) {
        for (Consulta consulta : consultas) {
            if (consulta.getCpfMedico().equals(cpfMedico) && consulta.getData().equals(data) && consulta.getHorario().equals(horario) && consulta.getStatus()) {
                return false;
            }
        }
        return true;
    }

    public static Consulta procurar(String cpfPaciente, String cpfMedico, String data, String horario) {
        for (Consulta consulta : consultas) {
            if (consulta.getCpfPaciente().equals(cpfPaciente) && consulta.getCpfMedico().equals(cpfMedico) && consulta.getData().equals(data) && consulta.getHorario().equals(horario)) {
                return consulta;
            }
        }
        return null;
    }

    public static List<Consulta> lista() {
        return consultas;
    }
}