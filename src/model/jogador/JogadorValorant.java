package model.jogador;

import model.enums.Jogo;
import model.enums.Posicao;

/**
 * Representa um jogador de Valorant.
 * PESSOA 1 — implementar atributos específicos do Valorant (ex: agente principal, ACS, etc.)
 */
public class JogadorValorant extends Jogador {

    // TODO Pessoa 1: adicionar stats específicas de Valorant
    // Exemplos: String agentePrincipal, double acs, double kda
    private double acs; // Average Combat Score
    private String agentePrincipal;

    public JogadorValorant(int id, String nick, String nomeReal, int idade, Posicao posicao, double salario, double acs, String agentePrincipal) {
        super(id, nick, nomeReal, idade, posicao, salario);
        this.acs = acs;
        this.agentePrincipal = agentePrincipal;
    }

    public double getAcs() { return acs; }
    public void setAcs(double acs) { this.acs = acs; }

    public String getAgentePrincipal() { return agentePrincipal; }
    public void setAgentePrincipal(String agentePrincipal) { this.agentePrincipal = agentePrincipal; }

    @Override
    public Jogo getJogo() {
        return Jogo.VALORANT;
    }

    /**
     * TODO Pessoa 1: ajustar fórmula de pontuação.
     */
    @Override
    public double getPontuacao() {
        return acs;
    }

    @Override
    public String exibir() {
        return toString() + " | ACS: " + acs + " | Agente: " + agentePrincipal;
    }
}
