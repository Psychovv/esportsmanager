package model.jogador;

import model.enums.Jogo;
import model.enums.Posicao;

/**
 * Representa um jogador de Counter-Strike.
 * PESSOA 1 — implementar atributos específicos do CS (ex: rating, abates, etc.)
 */
public class JogadorCS extends Jogador {

    // Atributos específicos de CS adicionados
    private double rating;
    private double abatesPorPartida; // Média de kills por game (KPG)
    private double percentualHS;     // Ex: 0.50 para 50% de Headshot

    public JogadorCS(int id, String nick, String nomeReal, int idade, Posicao posicao, double salario, double rating, double abatesPorPartida, double percentualHS) {
        super(id, nick, nomeReal, idade, posicao, salario);
        this.rating = rating;
        this.abatesPorPartida = abatesPorPartida;
        this.percentualHS = percentualHS;
    }

    // Getters e Setters
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public double getAbatesPorPartida() { return abatesPorPartida; }
    public void setAbatesPorPartida(double abatesPorPartida) { this.abatesPorPartida = abatesPorPartida; }

    public double getPercentualHS() { return percentualHS; }
    public void setPercentualHS(double percentualHS) { this.percentualHS = percentualHS; }

    @Override
    public Jogo getJogo() {
        return Jogo.CS; // Certifique-se de que no seu Enum está "CS" e não "CSGO" como eu havia sugerido antes
    }

    /**
     * Fórmula de pontuação real para ranking.
     * Leva em consideração o impacto do rating, volume de abates e precisão (HS%).
     */
    @Override
    public double getPontuacao() {
        // Fórmula de exemplo:
        // Rating tem um peso forte (x50), abates somam pontos diretos (x2), e HS% dá um bônus (x100)
        // Ex: Rating 1.15, 18 abates, 0.45 HS = (57.5) + (36) + (45) = 138.5 de pontuação
        return (this.rating * 50) + (this.abatesPorPartida * 2) + (this.percentualHS * 100);
    }

    @Override
    public String exibir() {
        // Exibe os dados base do jogador e concatena com as stats específicas do CS
        return super.toString() + String.format(" | Rating: %.2f | Abates/Partida: %.1f | HS: %.0f%%",
                rating, abatesPorPartida, (percentualHS * 100));
    }
}