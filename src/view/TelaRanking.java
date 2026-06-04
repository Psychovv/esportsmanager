package view;

import javax.swing.*;
import java.awt.*;

/**
 * Tela de exibição do ranking.
 * PESSOA 4 (Sprint 2) — implementar tabela de ranking com JTable.
 */
public class TelaRanking extends JFrame {

    public TelaRanking() {
        setTitle("Ranking");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // TODO Pessoa 4 Sprint 2: JTable com ranking, filtros por jogo/posição (JComboBox)
        JLabel placeholder = new JLabel("Tela Ranking — a implementar", SwingConstants.CENTER);
        add(placeholder, BorderLayout.CENTER);
    }
}
