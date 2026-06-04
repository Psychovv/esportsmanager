package view;

import javax.swing.*;
import java.awt.*;

/**
 * Tela de cadastro e listagem de jogadores.
 * PESSOA 4 (Sprint 2) — implementar formulário e tabela.
 */
public class TelaJogador extends JFrame {

    public TelaJogador() {
        setTitle("Gerenciar Jogadores");
        setSize(700, 500);
        setLocationRelativeTo(null);

        // TODO Pessoa 4 Sprint 2: JTable para listar, JTextField para cadastro, JButton para ações
        JLabel placeholder = new JLabel("Tela Jogador — a implementar", SwingConstants.CENTER);
        add(placeholder, BorderLayout.CENTER);
    }
}
