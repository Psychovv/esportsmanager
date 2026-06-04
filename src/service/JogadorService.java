package service;

import model.enums.Jogo;
import model.jogador.Jogador;
import repository.JogadorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço de negócio para operações com jogadores.
 * PESSOA 3 — implementar todos os métodos marcados com TODO.
 */
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    /**
     * Cadastra um jogador no sistema.
     * TODO Pessoa 3: validar dados antes de delegar ao repositório.
     * (ex: nick não pode ser vazio, jogador não pode já estar cadastrado)
     */
    public void cadastrarJogador(Jogador jogador) {
        // TODO: validações + jogadorRepository.adicionarJogador(jogador)
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 3");
    }

    /**
     * Remove um jogador pelo ID.
     * TODO Pessoa 3: lançar JogadorNaoEncontradoException se não existir.
     */
    public void removerJogador(int id) {
        // TODO: chamar repositório, tratar exceção
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 3");
    }

    /**
     * Busca jogador por nick.
     * TODO Pessoa 3: lançar JogadorNaoEncontradoException se não encontrar.
     */
    public Jogador buscarPorNick(String nick) {
        // TODO: delegar ao repositório e tratar Optional
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 3");
    }

    /**
     * Lista todos os jogadores de um jogo específico.
     * TODO Pessoa 3: implementar filtro por jogo.
     */
    public List<Jogador> listarPorJogo(Jogo jogo) {
        // TODO: usar stream + filter
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 3");
    }

    /**
     * Lista todos os jogadores cadastrados.
     */
    public List<Jogador> listarTodos() {
        return jogadorRepository.listarJogadores();
    }
}
