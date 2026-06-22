package service;

import exception.JogadorNaoEncontradoException;
import model.enums.Jogo;
import model.jogador.Jogador;
import repository.JogadorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço de negócio para operações com jogadores.
 */
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    /**
     * Cadastra um jogador no sistema, validando nick duplicado e campos vazios.
     */
    public void cadastrarJogador(Jogador jogador) {
        if (jogador.getNick() == null || jogador.getNick().trim().isEmpty()) {
            throw new IllegalArgumentException("O nick do jogador não pode ser vazio.");
        }

        boolean nickJaExiste = jogadorRepository.buscarPorNick(jogador.getNick()).isPresent();
        if (nickJaExiste) {
            throw new IllegalArgumentException("Já existe um jogador cadastrado com o nick '" + jogador.getNick() + "'.");
        }

        jogadorRepository.adicionarJogador(jogador);
    }

    /**
     * Remove um jogador pelo ID.
     */
    public void removerJogador(int id) throws JogadorNaoEncontradoException {
        boolean removido = jogadorRepository.removerJogador(id);
        if (!removido) {
            throw new JogadorNaoEncontradoException(id);
        }
    }

    /**
     * Busca jogador por nick.
     */
    public Jogador buscarPorNick(String nick) throws JogadorNaoEncontradoException {
        Optional<Jogador> resultado = jogadorRepository.buscarPorNick(nick);
        return resultado.orElseThrow(() -> new JogadorNaoEncontradoException("nick", nick));
    }

    /**
     * Busca jogador por ID.
     */
    public Jogador buscarPorId(int id) throws JogadorNaoEncontradoException {
        Optional<Jogador> resultado = jogadorRepository.buscarPorId(id);
        return resultado.orElseThrow(() -> new JogadorNaoEncontradoException(id));
    }

    /**
     * Lista todos os jogadores de um jogo específico.
     */
    public List<Jogador> listarPorJogo(Jogo jogo) {
        return jogadorRepository.listarJogadores().stream()
                .filter(j -> j.getJogo() == jogo)
                .collect(Collectors.toList());
    }

    /**
     * Lista todos os jogadores cadastrados.
     */
    public List<Jogador> listarTodos() {
        return jogadorRepository.listarJogadores();
    }
}
