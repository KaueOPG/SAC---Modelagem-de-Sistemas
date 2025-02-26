package com.kaueopg.sac.v1.View;

import com.kaueopg.sac.v1.Controller.TelaCadastroController;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.Border;

public class TelaCadastro extends JFrame {
    private JPanel painel = new JPanel();
    private Border borda = BorderFactory.createLineBorder(Color.GRAY, 1);

    private JTextField nome;
    private JTextField cpf;
    private JPasswordField senha;
    private JTextField dataNascimento;
    private JTextField contato;
    private TelaCadastroController control = new TelaCadastroController(this);

    public TelaCadastro() {
        configurarJanela();
        add(criarPainel());
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Tela de Cadastro");
        setSize(400, 500); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel criarPainel() {
        painel.setLayout(new GridLayout(7, 1, 10, 10)); 
        campos();
        botoes();
        return painel;
    }

    private void campos() {
        nome = new JTextField();
        nome.setBorder(BorderFactory.createTitledBorder(borda, "Nome"));
        painel.add(nome);

        cpf = new JTextField();
        cpf.setBorder(BorderFactory.createTitledBorder(borda, "CPF (Formato: 000.000.000-00)"));
        painel.add(cpf);

        senha = new JPasswordField();
        senha.setBorder(BorderFactory.createTitledBorder(borda, "Senha"));
        painel.add(senha);

        dataNascimento = new JTextField();
        dataNascimento.setBorder(BorderFactory.createTitledBorder(borda, "Data de Nascimento (Formato: DD/MM/AAAA)"));
        painel.add(dataNascimento);

        contato = new JTextField();
        contato.setBorder(BorderFactory.createTitledBorder(borda, "Contato (Telefone ou Email)"));
        painel.add(contato);
    }

    private void botoes() {
        JButton botaoFinalizar = new JButton("Finalizar Cadastro");
        botaoFinalizar.setBorder(borda);
        botaoFinalizar.addActionListener((ActionEvent e) -> 
            control.finalizar(cpf.getText(), nome.getText(), new String(senha.getPassword()), dataNascimento.getText(), contato.getText())
        );
        painel.add(botaoFinalizar);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBorder(borda);
        botaoCancelar.addActionListener((ActionEvent e) -> control.cancelar());
        painel.add(botaoCancelar);
    }

    public String getDataNascimento() {
        return dataNascimento.getText();
    }

    public String getContato() {
        return contato.getText();
    }
}