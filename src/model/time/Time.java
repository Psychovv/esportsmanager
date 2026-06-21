package model.time;

import interfaces.Exibivel;
import interfaces.Ranqueavel;
import model.enums.Jogo;
import model.jogador.Jogador;

import java.util.ArrayList;
import java.util.List;

public class Time implements Ranqueavel, Exibivel {

    private int id;
    private String nome;
    private String tag;       // ex: "FURIA", "LOUD"
    private Jogo jogo;
    private List<Jogador> jogadores;

    // Atributos adicionados (Pessoa 1)
    private String pais;
    private int anoFundacao;

    public Time(int id, String nome, String tag, Jogo jogo, String pais, int anoFundacao) {
        this.id = id;
        this.nome = nome;
        this.tag = tag;
        this.jogo = jogo;
        this.pais = pais;
        this.anoFundacao = anoFundacao;
        this.jogadores = new ArrayList<>();
    }

    /**
     * Adiciona o jogador apenas se for do mesmo jogo do time e se houver vaga.
     */
    public boolean adicionarJogador(Jogador jogador) {
        if (jogador.getJogo() == this.jogo && jogadores.size() < 5) {
            jogadores.add(jogador);
            return true; // Sucesso ao adicionar
        }
        return false; // Falha (jogo diferente ou time lotado)
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    /**
     * Pontuação do time = média das pontuações dos jogadores.
     * Refinamento: Se o time não tiver 5 jogadores, sofre uma leve penalidade (20%) na média geral.
     */
    @Override
    public double getPontuacao() {
        if (jogadores.isEmpty()) return 0;

        double media = jogadores.stream()
                .mapToDouble(Jogador::getPontuacao)
                .average()
                .orElse(0);

        // Aplica a penalidade de roster incompleto
        if (jogadores.size() < 5) {
            return media * 0.8;
        }

        return media;
    }

    @Override
    public String exibir() {
        StringBuilder sb = new StringBuilder();
        sb.append(toString()).append("\n");
        sb.append("Elenco ativo:\n");
        if (jogadores.isEmpty()) {
            sb.append("  (Nenhum jogador escalado)\n");
        } else {
            for (Jogador j : jogadores) {
                // Chama o exibir() específico de cada jogador (CS, Valorant ou LoL)
                sb.append("  - ").append(j.exibir()).append("\n");
            }
        }
        return sb.toString();
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

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public int getAnoFundacao() { return anoFundacao; }
    public void setAnoFundacao(int anoFundacao) { this.anoFundacao = anoFundacao; }

    @Override
    public String toString() {
        return "[" + jogo + "] " + tag + " - " + nome + " (" + pais + ", desde " + anoFundacao + ")"
                + " | Jogadores: " + jogadores.size() + "/5"
                + " | Pontuação: " + String.format("%.2f", getPontuacao());
    }
}