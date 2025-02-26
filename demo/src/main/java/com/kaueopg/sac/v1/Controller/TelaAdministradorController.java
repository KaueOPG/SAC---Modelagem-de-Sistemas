package com.kaueopg.sac.v1.Controller;

import com.kaueopg.sac.v1.Model.*;
import com.kaueopg.sac.v1.View.TelaAdministrador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.util.List;

public class TelaAdministradorController {

    private TelaAdministrador tela;

    public TelaAdministradorController(TelaAdministrador tela) {
        this.tela = tela;
    }

    public void carregarDados(DefaultTableModel modelo, String tipoTabela) {
        modelo.setRowCount(0);

        if (tipoTabela.equals("medico")) {
            List<Medico> medicos = MedicoController.lista();
            for (Medico medico : medicos) {
                modelo.addRow(new Object[]{
                        medico.getNome(),
                        medico.getCpf(),
                        medico.getSenha(),
                        medico.getEspecializacao(),
                        medico.getValor()
                });
            }
        } else if (tipoTabela.equals("paciente")) {
            List<Paciente> pacientes = PacienteController.lista();
            for (Paciente paciente : pacientes) {
                modelo.addRow(new Object[]{
                        paciente.getNome(),
                        paciente.getCpf(),
                        paciente.getSenha(),
                        paciente.getDataNascimento(),
                        paciente.getContato()
                });
            }
        } else if (tipoTabela.equals("consulta")) {
            List<Consulta> consultas = ConsultaController.lista();
            for (Consulta consulta : consultas) {
                modelo.addRow(new Object[]{
                        consulta.getCpfPaciente(),
                        consulta.getCpfMedico(),
                        consulta.getData(),
                        consulta.getHorario()
                });
            }
        }
    }

    public void adicionarMedico() {
        JDialog dialog = new JDialog(tela, "Adicionar Médico", true);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(tela);

        JTextField nome = new JTextField();
        JTextField cpf = new JTextField();
        JPasswordField senha = new JPasswordField();
        JTextField especializacao = new JTextField();
        JTextField valor = new JTextField();

        dialog.add(new JLabel("Nome:"));
        dialog.add(nome);
        dialog.add(new JLabel("CPF:"));
        dialog.add(cpf);
        dialog.add(new JLabel("Senha:"));
        dialog.add(senha);
        dialog.add(new JLabel("Especialização:"));
        dialog.add(especializacao);
        dialog.add(new JLabel("Valor da Consulta:"));
        dialog.add(valor);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> {
            MedicoController.adicionar(nome.getText(), cpf.getText(), new String(senha.getPassword()), especializacao.getText(), Double.parseDouble(valor.getText()));
            dialog.dispose();
            JOptionPane.showMessageDialog(tela, "Médico adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        dialog.add(new JLabel());
        dialog.add(botaoSalvar);
        dialog.setVisible(true);
    }

    public void adicionarPaciente() {
        JDialog dialog = new JDialog(tela, "Adicionar Paciente", true);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(tela);

        JTextField nome = new JTextField();
        JTextField cpf = new JTextField();
        JPasswordField senha = new JPasswordField();
        JTextField dataNascimento = new JTextField();
        JTextField contato = new JTextField();

        dialog.add(new JLabel("Nome:"));
        dialog.add(nome);
        dialog.add(new JLabel("CPF:"));
        dialog.add(cpf);
        dialog.add(new JLabel("Senha:"));
        dialog.add(senha);
        dialog.add(new JLabel("Data de Nascimento:"));
        dialog.add(dataNascimento);
        dialog.add(new JLabel("Contato:"));
        dialog.add(contato);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> {
            PacienteController.adicionar(nome.getText(), cpf.getText(), new String(senha.getPassword()), dataNascimento.getText(), contato.getText());
            dialog.dispose();
            JOptionPane.showMessageDialog(tela, "Paciente adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        dialog.add(new JLabel());
        dialog.add(botaoSalvar);
        dialog.setVisible(true);
    }

    public void adicionarConsulta() {
        JDialog dialog = new JDialog(tela, "Adicionar Consulta", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(tela);

        JTextField cpfPaciente = new JTextField();
        JTextField cpfMedico = new JTextField();
        JTextField data = new JTextField();
        JTextField horario = new JTextField();

        dialog.add(new JLabel("CPF do Paciente:"));
        dialog.add(cpfPaciente);
        dialog.add(new JLabel("CPF do Médico:"));
        dialog.add(cpfMedico);
        dialog.add(new JLabel("Data:"));
        dialog.add(data);
        dialog.add(new JLabel("Horário:"));
        dialog.add(horario);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> {
            ConsultaController.cadastrar(cpfPaciente.getText(), cpfMedico.getText(), data.getText(), horario.getText());
            dialog.dispose();
            JOptionPane.showMessageDialog(tela, "Consulta adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        dialog.add(new JLabel());
        dialog.add(botaoSalvar);
        dialog.setVisible(true);
    }

    public void adicionarAdministrador() {
        JDialog dialog = new JDialog(tela, "Adicionar Administrador", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(tela);
    
        JTextField nome = new JTextField();
        JTextField cpf = new JTextField();
        JPasswordField senha = new JPasswordField();
    
        dialog.add(new JLabel("Nome:"));
        dialog.add(nome);
        dialog.add(new JLabel("CPF:"));
        dialog.add(cpf);
        dialog.add(new JLabel("Senha:"));
        dialog.add(senha);
    
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> {
            Administrador admim = new Administrador(nome.getText(), cpf.getText(), new String(senha.getPassword()));
            AdministradorController.adicionar(admim);
            dialog.dispose();
            JOptionPane.showMessageDialog(tela, "Administrador adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });
    
        dialog.add(new JLabel());
        dialog.add(botaoSalvar);
        dialog.setVisible(true);
    }

    public void editarMedico(JTable tabela) {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow >= 0) {
            String cpf = (String) tabela.getValueAt(selectedRow, 1);
            Medico medico = MedicoController.procurar(cpf);
            if (medico != null) {
                JDialog dialog = new JDialog(tela, "Editar Médico", true);
                dialog.setLayout(new GridLayout(6, 2, 10, 10));
                dialog.setSize(400, 300);
                dialog.setLocationRelativeTo(tela);

                JTextField nome = new JTextField(medico.getNome());
                JTextField cpfField = new JTextField(medico.getCpf());
                JPasswordField senha = new JPasswordField(medico.getSenha());
                JTextField especializacao = new JTextField(medico.getEspecializacao());
                JTextField valor = new JTextField(String.valueOf(medico.getValor()));

                dialog.add(new JLabel("Nome:"));
                dialog.add(nome);
                dialog.add(new JLabel("CPF:"));
                dialog.add(cpfField);
                dialog.add(new JLabel("Senha:"));
                dialog.add(senha);
                dialog.add(new JLabel("Especialização:"));
                dialog.add(especializacao);
                dialog.add(new JLabel("Valor da Consulta:"));
                dialog.add(valor);

                JButton botaoSalvar = new JButton("Salvar");
                botaoSalvar.addActionListener(e -> {
                    MedicoController.editar(nome.getText(), cpfField.getText(), new String(senha.getPassword()), especializacao.getText(), Double.parseDouble(valor.getText()), cpf);
                    dialog.dispose();
                    JOptionPane.showMessageDialog(tela, "Médico atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                });

                dialog.add(new JLabel());
                dialog.add(botaoSalvar);
                dialog.setVisible(true);
            }
        }
    }

    public void editarPaciente(JTable tabela) {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow >= 0) {
            String cpf = (String) tabela.getValueAt(selectedRow, 1);
            Paciente paciente = PacienteController.procurar(cpf);
            if (paciente != null) {
                JDialog dialog = new JDialog(tela, "Editar Paciente", true);
                dialog.setLayout(new GridLayout(6, 2, 10, 10));
                dialog.setSize(400, 300);
                dialog.setLocationRelativeTo(tela);

                JTextField nome = new JTextField(paciente.getNome());
                JTextField cpfField = new JTextField(paciente.getCpf());
                JPasswordField senha = new JPasswordField(paciente.getSenha());
                JTextField dataNascimento = new JTextField(paciente.getDataNascimento());
                JTextField contato = new JTextField(paciente.getContato());

                dialog.add(new JLabel("Nome:"));
                dialog.add(nome);
                dialog.add(new JLabel("CPF:"));
                dialog.add(cpfField);
                dialog.add(new JLabel("Senha:"));
                dialog.add(senha);
                dialog.add(new JLabel("Data de Nascimento:"));
                dialog.add(dataNascimento);
                dialog.add(new JLabel("Contato:"));
                dialog.add(contato);

                JButton botaoSalvar = new JButton("Salvar");
                botaoSalvar.addActionListener(e -> {
                    PacienteController.editar(nome.getText(), cpfField.getText(), new String(senha.getPassword()), dataNascimento.getText(), contato.getText(), cpf);
                    dialog.dispose();
                    JOptionPane.showMessageDialog(tela, "Paciente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                });

                dialog.add(new JLabel());
                dialog.add(botaoSalvar);
                dialog.setVisible(true);
            }
        }
    }

    public void editarAdministrador(JTable tabela) {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow >= 0) {
            String cpf = (String) tabela.getValueAt(selectedRow, 1);
            Administrador administrador = AdministradorController.procurar(cpf);
            if (administrador != null) {
                JDialog dialog = new JDialog(tela, "Editar Administrador", true);
                dialog.setLayout(new GridLayout(4, 2, 10, 10));
                dialog.setSize(400, 250);
                dialog.setLocationRelativeTo(tela);
    
                JTextField nome = new JTextField(administrador.getNome());
                JTextField cpfField = new JTextField(administrador.getCpf());
                JPasswordField senha = new JPasswordField(administrador.getSenha());
    
                dialog.add(new JLabel("Nome:"));
                dialog.add(nome);
                dialog.add(new JLabel("CPF:"));
                dialog.add(cpfField);
                dialog.add(new JLabel("Senha:"));
                dialog.add(senha);
    
                JButton botaoSalvar = new JButton("Salvar");
                botaoSalvar.addActionListener(e -> {
                    AdministradorController.editar(nome.getText(), cpfField.getText(), new String(senha.getPassword()), cpf);
                    dialog.dispose();
                    JOptionPane.showMessageDialog(tela, "Administrador atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                });
    
                dialog.add(new JLabel());
                dialog.add(botaoSalvar);
                dialog.setVisible(true);
            }
        }
    }

    public void excluir(JTable tabela, int selectedRow, String tipoTabela) {
        if (selectedRow >= 0) {
            if (tipoTabela.equals("medico")) {

                String cpf = (String) tabela.getValueAt(selectedRow, 1); 
                MedicoController.excluir(cpf);
                JOptionPane.showMessageDialog(tela, "Médico excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else if (tipoTabela.equals("paciente")) {
    
                String cpf = (String) tabela.getValueAt(selectedRow, 1); 
                PacienteController.excluir(cpf);
                JOptionPane.showMessageDialog(tela, "Paciente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else if (tipoTabela.equals("consulta")) {

                String cpfPaciente = (String) tabela.getValueAt(selectedRow, 0); 
                String cpfMedico = (String) tabela.getValueAt(selectedRow, 1); 
                String data = (String) tabela.getValueAt(selectedRow, 2); 
                String horario = (String) tabela.getValueAt(selectedRow, 3); 

                ConsultaController.excluir(cpfPaciente, cpfMedico, data, horario);
                JOptionPane.showMessageDialog(tela, "Consulta excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (tipoTabela.equals("administrador")) {
                String cpf = (String) tabela.getValueAt(selectedRow, 1);
                AdministradorController.excluir(cpf);
                JOptionPane.showMessageDialog(tela, "Administrador excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tela, "Selecione um registro para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}