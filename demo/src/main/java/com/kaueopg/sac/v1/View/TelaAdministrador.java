package com.kaueopg.sac.v1.View;

import com.kaueopg.sac.v1.Controller.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdministrador extends JFrame {

    private TelaAdministradorController control;
    private JTabbedPane tabelaGeral;

    public TelaAdministrador() {
        control = new TelaAdministradorController(this);
        configurarJanela();
        criaTabela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Tela do Administrador");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void criaTabela() {
        tabelaGeral = new JTabbedPane();

        String[] colunasMedico = {"Nome", "CPF", "Senha", "Especialização", "Valor"};
        String[] colunasPaciente = {"Nome", "CPF", "Senha", "Data de Nascimento", "Contato"};
        String[] colunasConsulta = {"CPF Paciente", "CPF Médico", "Data", "Horário"};
        String[] colunasAdministrador = {"Nome", "CPF", "Senha"};

        DefaultTableModel modeloMedicos = new DefaultTableModel(colunasMedico, 0);
        DefaultTableModel modeloPacientes = new DefaultTableModel(colunasPaciente, 0);
        DefaultTableModel modeloConsultas = new DefaultTableModel(colunasConsulta, 0);
        DefaultTableModel modeloAdministradores = new DefaultTableModel(colunasAdministrador, 0);

        JTable tabelaMedicos = new JTable(modeloMedicos);
        JTable tabelaPacientes = new JTable(modeloPacientes);
        JTable tabelaConsultas = new JTable(modeloConsultas);
        JTable tabelaAdministradores = new JTable(modeloAdministradores);

        JPanel painelMedicos = criarPainel(colunasMedico, modeloMedicos, tabelaMedicos, "medico");
        JPanel painelPacientes = criarPainel(colunasPaciente, modeloPacientes, tabelaPacientes, "paciente");
        JPanel painelConsultas = criarPainel(colunasConsulta, modeloConsultas, tabelaConsultas, "consulta");
        JPanel painelAdministradores = criarPainel(colunasAdministrador, modeloAdministradores, tabelaAdministradores, "administrador");

        tabelaGeral.addTab("Médicos", painelMedicos);
        tabelaGeral.addTab("Pacientes", painelPacientes);
        tabelaGeral.addTab("Consultas", painelConsultas);
        tabelaGeral.addTab("Administradores", painelAdministradores);

        add(tabelaGeral);
    }

    private JPanel criarPainel(String[] colunas, DefaultTableModel modelo, JTable tabela, String tipoTabela) {
        JPanel painel = new JPanel(new BorderLayout());
        modelo = new DefaultTableModel(colunas, 0);
        control.carregarDados(modelo, tipoTabela);
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        botoes(scrollPane, painel, tipoTabela, modelo, tabela);
        return painel;
    }

    private void botoes(JScrollPane scrollPane, JPanel painel, String tipoTabela, DefaultTableModel modelo, JTable tabela) {
        JPanel painelBotoes = new JPanel();
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tipoTabela) {
                    case "medico":
                        control.adicionarMedico();
                        break;
                    case "paciente":
                        control.adicionarPaciente();
                        break;
                    case "consulta":
                        control.adicionarConsulta();
                        break;
                    case "administrador":
                        control.adicionarAdministrador();
                        break;
                }
            }
        });
        painelBotoes.add(botaoAdicionar);

        if (!tipoTabela.equals("consulta")) {
            JButton botaoEditar = new JButton("Editar");
            botaoEditar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (tipoTabela) {
                        case "medico":
                            control.editarMedico(tabela);
                            break;
                        case "paciente":
                            control.editarPaciente(tabela);
                            break;
                        case "administrador":
                            control.editarAdministrador(tabela);
                            break;
                    }
                }
            });
            painelBotoes.add(botaoEditar);
        }

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabela.getSelectedRow();
                if (selectedRow >= 0) {
                    control.excluir(tabela, selectedRow, tipoTabela);
                }
            }
        });
        painelBotoes.add(botaoExcluir);

        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);
    }
}