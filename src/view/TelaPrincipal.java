package view;

import javax.swing.*;
import java.awt.*;

/**
 * Janela principal da aplicação Swing.
 * PESSOA 4 (Sprint 2) — implementar layout e navegação entre telas.
 */
public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Esports Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // TODO Pessoa 4 Sprint 2: montar layout com JPanel, JButton para navegar entre telas
        JLabel placeholder = new JLabel("Tela Principal — a implementar", SwingConstants.CENTER);
        add(placeholder, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
