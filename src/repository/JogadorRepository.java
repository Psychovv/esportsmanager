package repository;

import model.jogador.Jogador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo armazenamento em memória dos jogadores.
 * PESSOA 2 — implementar todos os métodos marcados com TODO.
 */
public class JogadorRepository {

    private final ArrayList<Jogador> jogadores = new ArrayList<>();
    private int proximoId = 1;

    /**
     * Adiciona um jogador à lista e atribui um ID automático.
     * TODO Pessoa 2: implementar.
     */
    public void adicionarJogador(Jogador jogador) {
        // TODO: atribuir jogador.setId(proximoId++) e adicionar à lista
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Busca um jogador pelo ID.
     * TODO Pessoa 2: implementar.
     */
    public Optional<Jogador> buscarPorId(int id) {
        // TODO: percorrer lista e retornar Optional com o jogador encontrado
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Busca um jogador pelo nick (case-insensitive).
     * TODO Pessoa 2: implementar.
     */
    public Optional<Jogador> buscarPorNick(String nick) {
        // TODO: percorrer lista comparando nick
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Remove um jogador pelo ID.
     * TODO Pessoa 2: implementar. Retorna true se removido, false se não encontrado.
     */
    public boolean removerJogador(int id) {
        // TODO: usar removeIf ou iterator
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Retorna todos os jogadores cadastrados.
     * TODO Pessoa 2: implementar.
     */
    public List<Jogador> listarJogadores() {
        // TODO: retornar cópia da lista para evitar modificação externa
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Retorna a lista interna (uso interno dos services).
     */
    public ArrayList<Jogador> getLista() {
        return jogadores;
    }
}
