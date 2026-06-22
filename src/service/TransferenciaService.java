package service;

import exception.JogadorNaoEncontradoException;
import exception.TimeNaoEncontradoException;
import exception.TransferenciaInvalidaException;
import model.jogador.Jogador;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;

import java.util.Optional;

/**
 * Serviço responsável por transferências de jogadores entre times.
 */
public class TransferenciaService {

    private final JogadorRepository jogadorRepository;
    private final TimeRepository timeRepository;

    public TransferenciaService(JogadorRepository jogadorRepository, TimeRepository timeRepository) {
        this.jogadorRepository = jogadorRepository;
        this.timeRepository = timeRepository;
    }

    /**
     * Transfere um jogador para um novo time.
     *
     * @param idJogador     ID do jogador a ser transferido
     * @param idTimeDestino ID do time de destino
     */
    public void transferirJogador(int idJogador, int idTimeDestino)
            throws JogadorNaoEncontradoException, TimeNaoEncontradoException, TransferenciaInvalidaException {

        Jogador jogador = jogadorRepository.buscarPorId(idJogador)
                .orElseThrow(() -> new JogadorNaoEncontradoException(idJogador));

        Time destino = timeRepository.buscarPorId(idTimeDestino)
                .orElseThrow(() -> new TimeNaoEncontradoException(idTimeDestino));

        // Valida se o jogo do jogador é compatível com o jogo do time
        if (jogador.getJogo() != destino.getJogo()) {
            throw new TransferenciaInvalidaException(
                    "Jogador de " + jogador.getJogo() + " não pode entrar em um time de " + destino.getJogo() + ".");
        }

        // Remove do time de origem, se houver
        if (jogador.getTimeAtual() != null) {
            Optional<Time> origem = timeRepository.buscarPorNome(jogador.getTimeAtual());
            origem.ifPresent(time -> time.removerJogador(jogador));
        }

        // Adiciona no time de destino (retorna false se o time já estiver com 5 jogadores)
        boolean adicionado = destino.adicionarJogador(jogador);
        if (!adicionado) {
            throw new TransferenciaInvalidaException(
                    "Time " + destino.getNome() + " já está com o elenco completo (5 jogadores).");
        }

        jogador.setTimeAtual(destino.getNome());
    }

    /**
     * Remove um jogador do seu time atual (dispensa), deixando-o sem time.
     */
    public void dispensarJogador(int idJogador) throws JogadorNaoEncontradoException {
        Jogador jogador = jogadorRepository.buscarPorId(idJogador)
                .orElseThrow(() -> new JogadorNaoEncontradoException(idJogador));

        if (jogador.getTimeAtual() != null) {
            Optional<Time> time = timeRepository.buscarPorNome(jogador.getTimeAtual());
            time.ifPresent(t -> t.removerJogador(jogador));
            jogador.setTimeAtual(null);
        }
    }
}
