package repository;

import model.jogador.Jogador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JogadorRepository {

    private final ArrayList<Jogador> jogadores = new ArrayList<>();
    private int proximoId = 1;

    /**
     * Adiciona um jogador à lista e atribui um ID automático.
     */
    public void adicionarJogador(Jogador jogador) {
        jogador.setId(proximoId++);
        jogadores.add(jogador);
    }

    /**
     * Busca um jogador pelo ID usando Streams.
     */
    public Optional<Jogador> buscarPorId(int id) {
        return jogadores.stream()
                .filter(j -> j.getId() == id)
                .findFirst();
    }

    /**
     * Busca um jogador pelo nick (case-insensitive).
     */
    public Optional<Jogador> buscarPorNick(String nick) {
        return jogadores.stream()
                .filter(j -> j.getNick().equalsIgnoreCase(nick))
                .findFirst();
    }

    /**
     * Remove um jogador pelo ID. Retorna true se removido, false se não encontrado.
     */
    public boolean removerJogador(int id) {
        // O removeIf já percorre a lista e remove o elemento que bater com a condição.
        // Ele retorna true se pelo menos um elemento foi removido.
        return jogadores.removeIf(j -> j.getId() == id);
    }

    /**
     * Retorna todos os jogadores cadastrados.
     * Retorna uma cópia da lista para evitar que serviços externos modifiquem o array original.
     */
    public List<Jogador> listarJogadores() {
        return new ArrayList<>(jogadores);
    }

    /**
     * Retorna a lista interna (uso interno dos services).
     */
    public ArrayList<Jogador> getLista() {
        return jogadores;
    }
}