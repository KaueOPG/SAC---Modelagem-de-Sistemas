package com.kaueopg.sac.v1.Controller;

import com.kaueopg.sac.v1.Model.*;
import com.kaueopg.sac.v1.View.*;

import javax.swing.JOptionPane;

public class TelaInicialController {
    private TelaInicial tela;

    public TelaInicialController(TelaInicial tela) {
        this.tela = tela;
    }

    public void entrar(String cpf, String senha) {

        Administrador administrador = Administrador.getMaster();
        if (administrador.getCpf().equals(cpf) && administrador.getSenha().equals(senha)) {
            new TelaAdministrador(); 
            tela.dispose();
            return;
        }

        administrador = AdministradorController.procurar(cpf);
        if (administrador != null && administrador.getSenha().equals(senha)) {
            new TelaAdministrador();
            tela.dispose();
            return;
        }

        Paciente paciente = PacienteController.procurar(cpf);
        if (paciente != null && paciente.getSenha().equals(senha)) {
            new TelaPaciente(paciente);
            tela.dispose();
            return;
        }

        Medico medico = MedicoController.procurar(cpf);
        if (medico != null && medico.getSenha().equals(senha)) {
            new TelaMedico(medico);
            tela.dispose();
            return;
        }

        JOptionPane.showMessageDialog(tela, "CPF ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void cadastrar() {
        new TelaCadastro();
        tela.dispose();
    }
}