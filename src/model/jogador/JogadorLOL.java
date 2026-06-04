package model.jogador;

import model.enums.Jogo;
import model.enums.Posicao;

/**
 * Representa um jogador de League of Legends.
 * PESSOA 1 — implementar atributos específicos do LOL (ex: KDA, campeão principal, etc.)
 */
public class JogadorLOL extends Jogador {

    // TODO Pessoa 1: adicionar stats específicas de LOL
    // Exemplos: double kda, String campeoaPrincipal, double cspormin
    private double kda;
    private String campeaoPrincipal;

    public JogadorLOL(int id, String nick, String nomeReal, int idade, Posicao posicao, double salario, double kda, String campeaoPrincipal) {
        super(id, nick, nomeReal, idade, posicao, salario);
        this.kda = kda;
        this.campeaoPrincipal = campeaoPrincipal;
    }

    public double getKda() { return kda; }
    public void setKda(double kda) { this.kda = kda; }

    public String getCampeaoPrincipal() { return campeaoPrincipal; }
    public void setCampeaoPrincipal(String campeaoPrincipal) { this.campeaoPrincipal = campeaoPrincipal; }

    @Override
    public Jogo getJogo() {
        return Jogo.LOL;
    }

    /**
     * TODO Pessoa 1: ajustar fórmula de pontuação.
     */
    @Override
    public double getPontuacao() {
        return kda;
    }

    @Override
    public String exibir() {
        return toString() + " | KDA: " + kda + " | Campeão: " + campeaoPrincipal;
    }
}
