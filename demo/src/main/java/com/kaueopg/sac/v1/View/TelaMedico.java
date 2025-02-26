package com.kaueopg.sac.v1.View;

import com.kaueopg.sac.v1.Model.*;
import com.kaueopg.sac.v1.Controller.TelaMedicoController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class TelaMedico extends JFrame {

    private JPanel painelDados = new JPanel();
    private JPanel painelBotoes = new JPanel();
    private JPanel painelAgendamentos = new JPanel();
    private JPanel painelBotoesConsultas = new JPanel(); 
    private Border borda = BorderFactory.createLineBorder(Color.GRAY, 1);

    private JTextField nome;
    private JTextField cpf;
    private JPasswordField senha;
    private TelaMedicoController control;
    private Medico medico;

    public TelaMedico(Medico medico) {
        this.medico = medico;
        control = new TelaMedicoController(this, medico);

        configurarJanela();
        criaPainelDados();
        criaPainelConsultas();

        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void configurarJanela() {
        setTitle("Médico - Consultas Marcadas");
        setSize(600, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void criaPainelDados() {
        dados();
        botaoAlterarSenha();
        painelDados.add(painelBotoes);
        getContentPane().add(painelDados, BorderLayout.WEST);
    }

    private void dados() {
        painelDados.setBorder(BorderFactory.createTitledBorder("Informações do Médico"));
        painelDados.setLayout(new GridLayout(4, 1, 10, 10));

        nome = new JTextField();
        nome.setBorder(BorderFactory.createTitledBorder(borda, "Nome"));
        painelDados.add(nome);
        nome.setText(medico.getNome());

        cpf = new JTextField();
        cpf.setBorder(BorderFactory.createTitledBorder(borda, "CPF"));
        painelDados.add(cpf);
        cpf.setText(medico.getCpf());

        senha = new JPasswordField();
        senha.setBorder(BorderFactory.createTitledBorder(borda, "Senha"));
        painelDados.add(senha);
        senha.setText(medico.getSenha());
    }

    private void botaoAlterarSenha() {
        JButton botaoEditar = new JButton("Editar Dados");
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarSenha();
            }
        });
        painelBotoes.add(botaoEditar);
    }

    private void criaPainelConsultas() {
        campos();

        String[] colunas = { "CPF do Paciente", "Data", "Hora" };
        DefaultTableModel consultasTabela = new DefaultTableModel(colunas, 0);
        JTable consultas = new JTable(consultasTabela);

        painelAgendamentos.add(new JScrollPane(consultas), BorderLayout.CENTER);
        getContentPane().add(painelAgendamentos, BorderLayout.EAST);

        botoesConsultas(consultas);
        painelAgendamentos.add(painelBotoesConsultas, BorderLayout.SOUTH);

        control.tabela(consultasTabela, consultas, (JButton) painelBotoesConsultas.getComponent(0)); 
    }

    private void campos() {
        painelAgendamentos.setBorder(BorderFactory.createTitledBorder("Consultas Marcadas"));
        painelAgendamentos.setPreferredSize(new Dimension(300, 350));
        painelAgendamentos.setLayout(new BorderLayout());
    }

    private void botoesConsultas(JTable consultas) {
        JButton botaoExibirProntuario = new JButton("Exibir Prontuário");
        botaoExibirProntuario.setEnabled(false); 

        consultas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                botaoExibirProntuario.setEnabled(consultas.getSelectedRow() >= 0);
            }
        });

        botaoExibirProntuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = consultas.getSelectedRow();
                if (selectedRow >= 0) {
                    String cpfPaciente = (String) consultas.getValueAt(selectedRow, 0); 
                    control.visualizarProntuario(cpfPaciente); 
                }
            }
        });

        painelBotoesConsultas.add(botaoExibirProntuario);
    }

    private void alterarSenha() {
        JDialog aba = new JDialog(TelaMedico.this, "Alterar Senha - Médico", true);
        aba.setLayout(new GridLayout(4, 2, 5, 10));
        aba.setSize(300, 200);
        aba.setLocationRelativeTo(TelaMedico.this);

        JPasswordField novaSenha = new JPasswordField();

        aba.add(new JLabel("Senha:"));
        aba.add(novaSenha);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.alterarSenha(new String(novaSenha.getPassword()));
            }
        });

        aba.add(new JLabel());
        aba.add(botaoSalvar);
        aba.setVisible(true);
    }
}