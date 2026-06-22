package view;

import repository.JogadorRepository;
import repository.TimeRepository;
import service.ArquivoService;
import service.JogadorService;
import service.RankingService;
import service.TransferenciaService;
import thread.RankingThread;

import javax.swing.*;
import java.awt.*;

/**
 * Janela principal da aplicação Swing.
 * Cria os repositórios/services compartilhados e abre as outras telas a partir daqui.
 */
public class TelaPrincipal extends JFrame {

    private final JogadorRepository jogadorRepo = new JogadorRepository();
    private final TimeRepository timeRepo = new TimeRepository();

    private final JogadorService jogadorService = new JogadorService(jogadorRepo);
    private final TransferenciaService transferenciaService = new TransferenciaService(jogadorRepo, timeRepo);
    private final RankingService rankingService = new RankingService(jogadorRepo, timeRepo);
    private final ArquivoService arquivoService = new ArquivoService(jogadorRepo, timeRepo);

    public TelaPrincipal() {
        setTitle("Esports Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JLabel titulo = new JLabel("ESPORTS MANAGER", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JButton btnJogadores = new JButton("Gerenciar Jogadores");
        JButton btnTimes = new JButton("Gerenciar Times");
        JButton btnRanking = new JButton("Ver Ranking");
        JButton btnSair = new JButton("Sair");

        btnJogadores.addActionListener(e ->
                new TelaJogador(jogadorService, timeRepo, transferenciaService).setVisible(true));

        btnTimes.addActionListener(e ->
                new TelaTime(timeRepo).setVisible(true));

        btnRanking.addActionListener(e ->
                new TelaRanking(rankingService).setVisible(true));

        btnSair.addActionListener(e -> System.exit(0));

        add(titulo, BorderLayout.NORTH);
        painel.add(btnJogadores);
        painel.add(btnTimes);
        painel.add(btnRanking);
        painel.add(btnSair);
        add(painel, BorderLayout.CENTER);

        // Inicia a thread de ranking em segundo plano (requisito de Threads)
        RankingThread rankingThread = new RankingThread(rankingService);
        rankingThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
