package view;

import javax.swing.*;
import java.awt.*;


public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Esports Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel placeholder = new JLabel("Tela Principal — a implementar", SwingConstants.CENTER);
        add(placeholder, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
