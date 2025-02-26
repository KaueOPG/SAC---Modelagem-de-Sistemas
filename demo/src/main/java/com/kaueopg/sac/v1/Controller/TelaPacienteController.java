package com.kaueopg.sac.v1.Controller;

import com.kaueopg.sac.v1.Controller.*;
import com.kaueopg.sac.v1.Model.*;
import com.kaueopg.sac.v1.View.TelaPaciente;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaPacienteController {

    private TelaPaciente tela;
    private Paciente paciente;

    public TelaPacienteController(TelaPaciente tela, Paciente paciente){
        this.paciente = paciente;
        this.tela = tela;
    }

    public void excluirConta()
    {
        PacienteController.excluir(paciente.getCpf());
        tela.dispose();
    }

    public void editarDados(String nome, String cpf, String senha, String dataNascimento, String contato, String novocpf) {
        if (PacienteController.editar(nome, cpf, senha, dataNascimento, contato, paciente.getCpf())) {
            if (!cpf.isEmpty()) {
                paciente.setCpf(novocpf);
            }
            tela.dispose();
            new TelaPaciente(paciente);
        } else {
            JOptionPane.showMessageDialog(tela, "CPF inválido ou já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void tabelaIniciar(DefaultTableModel consultasTabela)
    {
        List<Consulta> consultas = ConsultaController.lista();

        for(Consulta consulta: consultas)
            if(consulta.getCpfPaciente().matches(paciente.getCpf()))
            {
                Medico medico = MedicoController.procurar(consulta.getCpfMedico());
                if(medico != null)
                    consultasTabela.addRow(new Object[] { medico.getNome(), medico.getEspecializacao(), consulta.getData(), consulta.getHorario(), medico.getCpf() });
            }
    }

    public void excluirTabela(String cpfCliente, String cpfMedico, String data, String horario)
    {
        ConsultaController.excluir(cpfCliente, cpfMedico, data, horario);
        new TelaPaciente(paciente);
        tela.dispose();
    }

    public void criarConsulta(String cpfMedico, String data, String horario)
    {
        if (!data.isEmpty() && !horario.isEmpty()) {
            ConsultaController.cadastrar(paciente.getCpf(), cpfMedico, data, horario);
            tela.dispose();
            new TelaPaciente(paciente);
        }
        else
            JOptionPane.showMessageDialog(tela, "Dado(s) inválido(s).", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
}