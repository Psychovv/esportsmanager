package repository;

import model.time.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TimeRepository {

    private final ArrayList<Time> times = new ArrayList<>();
    private int proximoId = 1;

    /**
     * Adiciona um time à lista e atribui um ID automático.
     */
    public void adicionarTime(Time time) {
        time.setId(proximoId++);
        times.add(time);
    }

    /**
     * Busca um time pelo ID.
     */
    public Optional<Time> buscarPorId(int id) {
        return times.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    /**
     * Busca um time pelo nome.
     */
    public Optional<Time> buscarPorNome(String nome) {
        return times.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    /**
     * Remove um time pelo ID.
     * Retorna true se removido, false se não encontrado.
     */
    public boolean removerTime(int id) {
        return times.removeIf(t -> t.getId() == id);
    }

    /**
     * Retorna todos os times cadastrados.
     * Retorna uma cópia da lista para evitar modificação externa indevida.
     */
    public List<Time> listarTimes() {
        return new ArrayList<>(times);
    }

    /**
     * Retorna a lista interna.
     */
    public ArrayList<Time> getLista() {
        return times;
    }
}