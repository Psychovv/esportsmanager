package repository;

import model.time.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo armazenamento em memória dos times.
 * PESSOA 2 — implementar todos os métodos marcados com TODO.
 */
public class TimeRepository {

    private final ArrayList<Time> times = new ArrayList<>();
    private int proximoId = 1;

    /**
     * Adiciona um time à lista e atribui um ID automático.
     * TODO Pessoa 2: implementar.
     */
    public void adicionarTime(Time time) {
        // TODO: atribuir time.setId(proximoId++) e adicionar à lista
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Busca um time pelo ID.
     * TODO Pessoa 2: implementar.
     */
    public Optional<Time> buscarPorId(int id) {
        // TODO: percorrer lista e retornar Optional com o time encontrado
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Busca um time pelo nome (case-insensitive).
     * TODO Pessoa 2: implementar.
     */
    public Optional<Time> buscarPorNome(String nome) {
        // TODO: percorrer lista comparando nome
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Remove um time pelo ID.
     * TODO Pessoa 2: implementar.
     */
    public boolean removerTime(int id) {
        // TODO: usar removeIf ou iterator
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    /**
     * Retorna todos os times cadastrados.
     * TODO Pessoa 2: implementar.
     */
    public List<Time> listarTimes() {
        // TODO: retornar cópia da lista
        throw new UnsupportedOperationException("Não implementado ainda — Pessoa 2");
    }

    public ArrayList<Time> getLista() {
        return times;
    }
}
