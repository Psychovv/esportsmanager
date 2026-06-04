package model.jogador;

import model.enums.Jogo;
import model.enums.Posicao;

/**
 * Representa um jogador de Counter-Strike.
 * PESSOA 1 — implementar atributos específicos do CS (ex: rating, abates, etc.)
 */
public class JogadorCS extends Jogador {

    // TODO Pessoa 1: adicionar stats específicas de CS
    // Exemplos: double rating, int abatesPorPartida, double percentualHS
    private double rating;

    public JogadorCS(int id, String nick, String nomeReal, int idade, Posicao posicao, double salario, double rating) {
        super(id, nick, nomeReal, idade, posicao, salario);
        this.rating = rating;
    }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public Jogo getJogo() {
        return Jogo.CS;
    }

    /**
     * TODO Pessoa 1: definir fórmula de pontuação real para ranking.
     * Por enquanto usa o rating diretamente.
     */
    @Override
    public double getPontuacao() {
        return rating;
    }

    @Override
    public String exibir() {
        return toString() + " | Rating CS: " + rating;
    }
}
