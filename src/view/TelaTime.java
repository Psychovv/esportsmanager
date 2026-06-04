package view;

import javax.swing.*;
import java.awt.*;

/**
 * Tela de cadastro e gerenciamento de times.
 * PESSOA 4 (Sprint 2) — implementar formulário e tabela.
 */
public class TelaTime extends JFrame {

    public TelaTime() {
        setTitle("Gerenciar Times");
        setSize(700, 500);
        setLocationRelativeTo(null);

        // TODO Pessoa 4 Sprint 2: JTable com times, botão para adicionar/remover jogadores
        JLabel placeholder = new JLabel("Tela Time — a implementar", SwingConstants.CENTER);
        add(placeholder, BorderLayout.CENTER);
    }
}
