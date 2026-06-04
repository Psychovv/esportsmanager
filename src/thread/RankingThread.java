package thread;

import service.RankingService;

/**
 * Thread que atualiza o ranking automaticamente a cada 10 segundos.
 * Requisito obrigatório da disciplina.
 * TODO (Sprint 2, após tudo pronto): descomentar a linha de atualização real.
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
        // TODO Sprint 2: chamar rankingService.rankingGeralJogadores() e exibir/logar
        System.out.println("[RankingThread] Ranking atualizado em: " + new java.util.Date());
    }

    public void encerrar() {
        rodando = false;
        interrupt();
    }
}
