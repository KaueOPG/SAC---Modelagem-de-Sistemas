package com.kaueopg.sac.v1.Controller;

import com.kaueopg.sac.v1.Model.*;
import com.kaueopg.sac.v1.View.*;

import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaMedicoController {

    private TelaMedico tela;
    private Medico medico;

    public TelaMedicoController(TelaMedico tela, Medico medico) {
        this.tela = tela;
        this.medico = medico;
    }

    public void alterarSenha(String senhaNova) {
        if (senhaNova.isBlank()) {
            JOptionPane.showMessageDialog(tela, "Digite uma senha válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        MedicoController.editar(medico.getNome(), medico.getCpf(), senhaNova, medico.getEspecializacao(), medico.getValor(), medico.getCpf());
        tela.dispose();
        new TelaMedico(medico);
    }

    public void tabela(DefaultTableModel consultasTabela, JTable tabelaConsultas, JButton botaoExibirProntuario) {
        List<Consulta> consultas = ConsultaController.lista();

        for (Consulta consulta : consultas) {
            if (consulta.getCpfMedico().matches(medico.getCpf())) {
                consultasTabela.addRow(new Object[]{consulta.getCpfPaciente(), consulta.getData(), consulta.getHorario()});
            }
        }

        tabelaConsultas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabelaConsultas.getSelectedRow();
                botaoExibirProntuario.setEnabled(selectedRow >= 0); 
            }
        });

        botaoExibirProntuario.addActionListener(e -> {
            int selectedRow = tabelaConsultas.getSelectedRow();
            if (selectedRow >= 0) {
                String cpfPaciente = (String) tabelaConsultas.getValueAt(selectedRow, 0); 
                visualizarProntuario(cpfPaciente); 
            }
        });
    }

    public void visualizarProntuario(String cpfPaciente) {

        Paciente paciente = PacienteController.procurar(cpfPaciente);
        if (paciente == null) {
            JOptionPane.showMessageDialog(tela, "Paciente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Prontuario prontuario = paciente.getProntuario();
        if (prontuario == null) {
            JOptionPane.showMessageDialog(tela, "Prontuário não disponível.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        exibirProntuario(prontuario, paciente);
    }

    private void exibirProntuario(Prontuario prontuario, Paciente paciente) {
        JDialog dialog = new JDialog(tela, "Prontuário do Paciente", true);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(tela);

        JTextField tipoSanguineo = new JTextField(prontuario.getTipoSanguineo());
        JTextField alergias = new JTextField(String.join(", ", prontuario.getAlergias()));
        JTextField cirurgias = new JTextField(String.join(", ", prontuario.getCirurgias()));
        JTextField medicamentos = new JTextField(String.join(", ", prontuario.getMedicamentos()));
        JTextField outros = new JTextField(prontuario.getOutros());

        dialog.add(new JLabel("Tipo Sanguíneo:"));
        dialog.add(tipoSanguineo);

        dialog.add(new JLabel("Alergias:"));
        dialog.add(alergias);

        dialog.add(new JLabel("Cirurgias:"));
        dialog.add(cirurgias);

        dialog.add(new JLabel("Medicamentos:"));
        dialog.add(medicamentos);

        dialog.add(new JLabel("Outras Informações:"));
        dialog.add(outros);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> {
            prontuario.setTipoSanguineo(tipoSanguineo.getText());
            prontuario.setAlergias(Arrays.asList(alergias.getText().split(", ")));
            prontuario.setCirurgias(Arrays.asList(cirurgias.getText().split(", ")));
            prontuario.setMedicamentos(Arrays.asList(medicamentos.getText().split(", ")));
            prontuario.setOutros(outros.getText());

            paciente.setProntuario(prontuario);
            PacienteController.editarProntuario(prontuario, paciente.getCpf());

            JOptionPane.showMessageDialog(dialog, "Prontuário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });

        dialog.add(new JLabel()); 
        dialog.add(botaoSalvar);

        dialog.setVisible(true);
    }
}