package service;

import model.enums.Jogo;
import model.enums.Posicao;
import model.jogador.Jogador;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelos rankings do sistema.
 */
public class RankingService {

    private final JogadorRepository jogadorRepository;
    private final TimeRepository timeRepository;

    public RankingService(JogadorRepository jogadorRepository, TimeRepository timeRepository) {
        this.jogadorRepository = jogadorRepository;
        this.timeRepository = timeRepository;
    }

    /**
     * Retorna ranking geral de jogadores (todos os jogos) ordenado por pontuação decrescente.
     */
    public List<Jogador> rankingGeralJogadores() {
        List<Jogador> lista = jogadorRepository.listarJogadores();
        lista.sort(Comparator.comparingDouble(Jogador::getPontuacao).reversed());
        return lista;
    }

    /**
     * Retorna ranking de jogadores filtrado por jogo, ordenado por pontuação decrescente.
     */
    public List<Jogador> rankingPorJogo(Jogo jogo) {
        return jogadorRepository.listarJogadores().stream()
                .filter(j -> j.getJogo() == jogo)
                .sorted(Comparator.comparingDouble(Jogador::getPontuacao).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Retorna ranking de jogadores filtrado por posição, ordenado por pontuação decrescente.
     */
    public List<Jogador> rankingPorPosicao(Posicao posicao) {
        return jogadorRepository.listarJogadores().stream()
                .filter(j -> j.getPosicao() == posicao)
                .sorted(Comparator.comparingDouble(Jogador::getPontuacao).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Retorna ranking de times ordenado por pontuação decrescente.
     */
    public List<Time> rankingTimes() {
        List<Time> lista = timeRepository.listarTimes();
        lista.sort(Comparator.comparingDouble(Time::getPontuacao).reversed());
        return lista;
    }
}
