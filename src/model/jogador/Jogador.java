package model.jogador;

import interfaces.Exibivel;
import interfaces.Ranqueavel;
import model.enums.Jogo;
import model.enums.Posicao;

/**
 * Classe abstrata base para todos os jogadores.
 * PESSOA 1 — implementar atributos e métodos concretos.
 */
public abstract class Jogador implements Ranqueavel, Exibivel {

    private int id;
    private String nick;
    private String nomeReal;
    private int idade;
    private Posicao posicao;
    private double salario;
    private String timeAtual; // nome do time ou null se sem time

    // TODO Pessoa 1: adicionar outros atributos relevantes (ex: nacionalidade, etc.)

    public Jogador(int id, String nick, String nomeReal, int idade, Posicao posicao, double salario) {
        this.id = id;
        this.nick = nick;
        this.nomeReal = nomeReal;
        this.idade = idade;
        this.posicao = posicao;
        this.salario = salario;
        this.timeAtual = null;
    }

    /**
     * Retorna o jogo ao qual o jogador pertence.
     * Cada subclasse deve sobrescrever esse método.
     */
    public abstract Jogo getJogo();

    // --- Getters e Setters ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }

    public String getNomeReal() { return nomeReal; }
    public void setNomeReal(String nomeReal) { this.nomeReal = nomeReal; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public Posicao getPosicao() { return posicao; }
    public void setPosicao(Posicao posicao) { this.posicao = posicao; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public String getTimeAtual() { return timeAtual; }
    public void setTimeAtual(String timeAtual) { this.timeAtual = timeAtual; }

    @Override
    public String toString() {
        return "[" + getJogo() + "] " + nick + " (" + nomeReal + ") | Posição: " + posicao
                + " | Time: " + (timeAtual != null ? timeAtual : "Sem time")
                + " | Pontuação: " + getPontuacao();
    }
}
