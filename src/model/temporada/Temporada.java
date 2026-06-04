package model.temporada;

import model.enums.Jogo;

/**
 * Representa uma temporada/campeonato.
 * PESSOA 1 — pode expandir conforme necessidade do projeto.
 */
public class Temporada {

    private int id;
    private String nome;
    private int ano;
    private Jogo jogo;

    // TODO Pessoa 1: adicionar atributos se necessário (ex: String vencedor, boolean encerrada)

    public Temporada(int id, String nome, int ano, Jogo jogo) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.jogo = jogo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    @Override
    public String toString() {
        return nome + " (" + ano + ") | Jogo: " + jogo;
    }
}
