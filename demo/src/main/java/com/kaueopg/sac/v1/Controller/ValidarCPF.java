package com.kaueopg.sac.v1.Controller;

import java.util.List;
import com.kaueopg.sac.v1.Model.*;

public class ValidarCPF {
    private static final String REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

    public static boolean validaCPF(String cpf) {
        if (!cpf.matches(REGEX)) {
            return false;
        }

        if (Administrador.getMaster().getCpf().equals(cpf)) {
            return false;
        }
   
        List<Paciente> pacientes = PacienteController.lista();
        for (Paciente paciente : pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                return false;
            }
        }

        List<Medico> medicos = MedicoController.lista();
        for (Medico medico : medicos) {
            if (medico.getCpf().equals(cpf)) {
                return false;
            }
        }
        return true; 
    }
}
