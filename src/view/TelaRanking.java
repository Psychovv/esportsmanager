package view;

import model.enums.Jogo;
import model.enums.Posicao;
import model.jogador.Jogador;
import model.time.Time;
import service.RankingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de exibição do ranking de jogadores e times.
 */
public class TelaRanking extends JFrame {

    private final RankingService rankingService;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private JComboBox<String> filtroBox;

    public TelaRanking(RankingService rankingService) {
        this.rankingService = rankingService;

        setTitle("Ranking");
        setSize(600, 450);
        setLocationRelativeTo(null);

        modeloTabela = new DefaultTableModel(new Object[]{"#", "Nome", "Detalhe", "Pontuação"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabela = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabela);

        JPanel painelTopo = new JPanel(new FlowLayout());
        filtroBox = new JComboBox<>(new String[]{
                "Geral (Jogadores)", "CS", "Valorant", "LOL", "Times"
        });
        JButton btnAtualizar = new JButton("Atualizar Ranking");
        btnAtualizar.addActionListener(e -> atualizarTabela());

        painelTopo.add(new JLabel("Filtro:"));
        painelTopo.add(filtroBox);
        painelTopo.add(btnAtualizar);

        add(painelTopo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        String filtro = (String) filtroBox.getSelectedItem();

        if ("Times".equals(filtro)) {
            List<Time> times = rankingService.rankingTimes();
            int posicao = 1;
            for (Time t : times) {
                modeloTabela.addRow(new Object[]{
                        posicao++, t.getNome(), t.getJogo(), String.format("%.2f", t.getPontuacao())
                });
            }
            return;
        }

        List<Jogador> jogadores;
        switch (filtro) {
            case "CS" -> jogadores = rankingService.rankingPorJogo(Jogo.CS);
            case "Valorant" -> jogadores = rankingService.rankingPorJogo(Jogo.VALORANT);
            case "LOL" -> jogadores = rankingService.rankingPorJogo(Jogo.LOL);
            default -> jogadores = rankingService.rankingGeralJogadores();
        }

        int posicao = 1;
        for (Jogador j : jogadores) {
            modeloTabela.addRow(new Object[]{
                    posicao++, j.getNick(), j.getJogo() + " | " + j.getPosicao(), String.format("%.2f", j.getPontuacao())
            });
        }
    }
}
