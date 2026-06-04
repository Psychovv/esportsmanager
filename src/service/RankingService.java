package service;

import model.enums.Jogo;
import model.enums.Posicao;
import model.jogador.Jogador;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelos rankings do sistema.
 * PESSOA 1 (Sprint 2) — implementar todos os métodos marcados com TODO.
 */
public class RankingService {

    private final JogadorRepository jogadorRepository;
    private final TimeRepository timeRepository;

    public RankingService(JogadorRepository jogadorRepository, TimeRepository timeRepository) {
        this.jogadorRepository = jogadorRepository;
        this.timeRepository = timeRepository;
    }

    /**
     * Retorna ranking geral de jogadores (todos os jogos) ordenado por pontuação.
     * TODO Pessoa 1 Sprint 2: usar Collections.sort() ou Comparator.
     */
    public List<Jogador> rankingGeralJogadores() {
        // TODO: pegar lista, ordenar por getPontuacao() decrescente
        throw new UnsupportedOperationException("Não implementado ainda — Sprint 2");
    }

    /**
     * Retorna ranking de jogadores filtrado por jogo.
     * TODO Pessoa 1 Sprint 2: implementar.
     */
    public List<Jogador> rankingPorJogo(Jogo jogo) {
        // TODO: filtrar por jogo + ordenar
        throw new UnsupportedOperationException("Não implementado ainda — Sprint 2");
    }

    /**
     * Retorna ranking de jogadores filtrado por posição.
     * TODO Pessoa 1 Sprint 2: implementar.
     */
    public List<Jogador> rankingPorPosicao(Posicao posicao) {
        // TODO: filtrar por posicao + ordenar
        throw new UnsupportedOperationException("Não implementado ainda — Sprint 2");
    }

    /**
     * Retorna ranking de times ordenado por pontuação.
     * TODO Pessoa 1 Sprint 2: implementar.
     */
    public List<Time> rankingTimes() {
        // TODO: pegar lista de times, ordenar por getPontuacao() decrescente
        throw new UnsupportedOperationException("Não implementado ainda — Sprint 2");
    }
}
