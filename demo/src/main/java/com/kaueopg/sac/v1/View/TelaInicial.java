package com.kaueopg.sac.v1.View;

import com.kaueopg.sac.v1.Controller.TelaInicialController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    private JPanel painel = new JPanel();
    private Border borda = BorderFactory.createLineBorder(Color.GRAY, 1);

    private JTextField cpf;
    private JPasswordField senha;

    private TelaInicialController control = new TelaInicialController(TelaInicial.this);

    public TelaInicial() {
        configurarJanela();
        JPanel painel = criaPainel();
        add(painel);
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Tela Inicial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel criaPainel() {
        painel.setLayout(new GridLayout(5, 1, 10, 10));
        campos();
        botoes();
        return painel;
    }

    public void campos() {
        JLabel labelBemVindo = new JLabel("Bem-vindo ao SAC", JLabel.CENTER);
        painel.add(labelBemVindo);

        cpf = new JTextField();
        cpf.setBorder(BorderFactory.createTitledBorder(borda, "CPF"));
        painel.add(cpf);

        senha = new JPasswordField();
        senha.setBorder(BorderFactory.createTitledBorder(borda, "Senha"));
        painel.add(senha);
    }

    private void botoes() {
        JButton botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBorder(borda);
        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.entrar(cpf.getText(),new String(senha.getPassword()));
            }
        });
        painel.add(botaoEntrar);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBorder(borda);
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.cadastrar();
            }
        });
        painel.add(botaoCadastrar);
    }

}