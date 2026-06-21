package model.jogador;

import model.enums.Jogo;
import model.enums.Posicao;

/**
 * Representa um jogador de League of Legends.
 * PESSOA 1 — implementar atributos específicos do LOL (ex: KDA, campeão principal, etc.)
 */
public class JogadorLOL extends Jogador {

    // Atributos específicos de LOL adicionados
    private double kda;
    private String campeaoPrincipal;
    private double csPorMin; // Creep Score por minuto (Farm)

    public JogadorLOL(int id, String nick, String nomeReal, int idade, Posicao posicao, double salario, double kda, String campeaoPrincipal, double csPorMin) {
        super(id, nick, nomeReal, idade, posicao, salario);
        this.kda = kda;
        this.campeaoPrincipal = campeaoPrincipal;
        this.csPorMin = csPorMin;
    }

    // Getters e Setters
    public double getKda() { return kda; }
    public void setKda(double kda) { this.kda = kda; }

    public String getCampeaoPrincipal() { return campeaoPrincipal; }
    public void setCampeaoPrincipal(String campeaoPrincipal) { this.campeaoPrincipal = campeaoPrincipal; }

    public double getCsPorMin() { return csPorMin; }
    public void setCsPorMin(double csPorMin) { this.csPorMin = csPorMin; }

    @Override
    public Jogo getJogo() {
        return Jogo.LOL;
    }

    /**
     * Fórmula de pontuação real para ranking do LOL.
     * Combina o KDA (peso maior) com a eficiência de farm (CS/min).
     */
    @Override
    public double getPontuacao() {
        // Fórmula de exemplo:
        // KDA tem peso grande (x15) e o Farm tem um peso secundário (x5)
        // Ex: KDA 3.5, CS/Min 8.5 -> (52.5) + (42.5) = 95.0 de pontuação
        return (this.kda * 15) + (this.csPorMin * 5);
    }

    @Override
    public String exibir() {
        // Retorna os dados base formatados junto com as stats de LoL
        return super.toString() + String.format(" | KDA: %.2f | CS/Min: %.1f | Campeão Principal: %s",
                kda, csPorMin, campeaoPrincipal);
    }
}