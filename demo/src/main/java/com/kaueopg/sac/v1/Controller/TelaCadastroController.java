package com.kaueopg.sac.v1.Controller;

import com.kaueopg.sac.v1.Model.*;
import com.kaueopg.sac.v1.View.*;

import javax.swing.JOptionPane;

public class TelaCadastroController {
    private TelaCadastro tela;

    public TelaCadastroController(TelaCadastro tela) {
        this.tela = tela;
    }

    public void finalizar(String cpf, String nome, String senha, String dataNascimento, String contato) {
        if (cpf.isEmpty() || nome.isEmpty() || senha.isEmpty() || dataNascimento.isEmpty() || contato.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (!ValidarCPF.validaCPF(cpf)) {
            JOptionPane.showMessageDialog(tela, "CPF inválido ou já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        PacienteController.adicionar(nome, cpf, senha, dataNascimento, contato);
    
        Paciente paciente = PacienteController.procurar(cpf);
        if (paciente != null) {
            new TelaPaciente(paciente);
            tela.dispose();
        } else {
            JOptionPane.showMessageDialog(tela, "Erro ao cadastrar paciente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancelar() {
        new TelaInicial();
        tela.dispose();
    }
}
