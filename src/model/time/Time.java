package model.time;

import interfaces.Exibivel;
import interfaces.Ranqueavel;
import model.enums.Jogo;
import model.jogador.Jogador;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um time de esports.
 * PESSOA 1 — pode adicionar mais atributos (ex: país, fundação, etc.)
 */
public class Time implements Ranqueavel, Exibivel {

    private int id;
    private String nome;
    private String tag;       // ex: "FURIA", "LOUD"
    private Jogo jogo;
    private List<Jogador> jogadores;

    // TODO Pessoa 1: adicionar outros atributos (ex: String pais, int anoFundacao)

    public Time(int id, String nome, String tag, Jogo jogo) {
        this.id = id;
        this.nome = nome;
        this.tag = tag;
        this.jogo = jogo;
        this.jogadores = new ArrayList<>();
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    /**
     * Pontuação do time = média das pontuações dos jogadores.
     * TODO Pessoa 1: refinar a fórmula se necessário.
     */
    @Override
    public double getPontuacao() {
        if (jogadores.isEmpty()) return 0;
        return jogadores.stream()
                .mapToDouble(Jogador::getPontuacao)
                .average()
                .orElse(0);
    }

    @Override
    public String exibir() {
        return toString();
    }

    // --- Getters e Setters ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public List<Jogador> getJogadores() { return jogadores; }

    @Override
    public String toString() {
        return "[" + jogo + "] " + tag + " - " + nome
                + " | Jogadores: " + jogadores.size()
                + " | Pontuação: " + String.format("%.2f", getPontuacao());
    }
}
