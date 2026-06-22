package thread;

import model.jogador.Jogador;
import service.RankingService;

import java.util.List;

/**
 * Thread que atualiza o ranking automaticamente a cada 10 segundos.
 * Requisito obrigatório da disciplina.
 */
public class RankingThread extends Thread {

    private final RankingService rankingService;
    private boolean rodando = true;

    public RankingThread(RankingService rankingService) {
        this.rankingService = rankingService;
        setDaemon(true); // thread encerra junto com o programa principal
    }

    @Override
    public void run() {
        System.out.println("[RankingThread] Iniciada — atualizando a cada 10 segundos.");
        while (rodando) {
            try {
                atualizarRanking();
                Thread.sleep(10000); // 10 segundos
            } catch (InterruptedException e) {
                System.out.println("[RankingThread] Interrompida.");
                rodando = false;
            }
        }
    }

    private void atualizarRanking() {
        List<Jogador> ranking = rankingService.rankingGeralJogadores();
        System.out.println("\n[RankingThread] Ranking atualizado em " + new java.util.Date() + ":");
        int posicao = 1;
        for (Jogador j : ranking) {
            System.out.println("  " + posicao + "º - " + j.getNick() + " (" + String.format("%.2f", j.getPontuacao()) + " pts)");
            posicao++;
        }
    }

    public void encerrar() {
        rodando = false;
        interrupt();
    }
}
