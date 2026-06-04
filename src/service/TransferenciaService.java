package service;

import model.jogador.Jogador;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;

/**
 * Serviço responsável por transferências de jogadores entre times.
 * PESSOA 3 — implementar todos os métodos marcados com TODO.
 */
public class TransferenciaService {

    private final JogadorRepository jogadorRepository;
    private final TimeRepository timeRepository;

    public TransferenciaService(JogadorRepository jogadorRepository, TimeRepository timeRepository) {
        this.jogadorRepository = jogadorRepository;
        this.timeRepository = timeRepository;
    }

    /**
     * Transfere um jogador de um time para outro.
     * TODO Pessoa 3: implementar e lançar TransferenciaInvalidaException quando:
     *   - Jogador não pertence ao time de origem
     *   - Time de destino não existe
     *   - Jogo do jogador é diferente do jogo do time de destino
     *
     * @param idJogador     ID do jogador a ser transferido
     * @param idTimeOrigem  ID do time de origem (pode ser -1 se sem time)
     * @param idTimeDestino ID do time de destino
     */
    public void transferirJogador(int idJogador, int idTimeOrigem, int idTimeDestino) {
        // TODO: buscar jogador e times, validar, remover de origem, adicionar em destino
        // Lembrar de atualizar jogador.setTimeAtual(...)
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 3");
    }

    /**
     * Remove um jogador do seu time atual (dispensa).
     * TODO Pessoa 3: implementar.
     */
    public void dispensarJogador(int idJogador) {
        // TODO: buscar jogador, remover do time, setar timeAtual como null
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 3");
    }
}
