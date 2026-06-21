package model.jogador;

import model.enums.Jogo;
import model.enums.Posicao;

/**
 * Representa um jogador de Valorant.
 * PESSOA 1 — implementar atributos específicos do Valorant (ex: agente principal, ACS, etc.)
 */
public class JogadorValorant extends Jogador {

    // Atributos específicos de Valorant adicionados
    private double acs; // Average Combat Score
    private String agentePrincipal;
    private double kda; // Kill/Death/Assist ratio

    public JogadorValorant(int id, String nick, String nomeReal, int idade, Posicao posicao, double salario, double acs, String agentePrincipal, double kda) {
        super(id, nick, nomeReal, idade, posicao, salario);
        this.acs = acs;
        this.agentePrincipal = agentePrincipal;
        this.kda = kda;
    }

    // Getters e Setters
    public double getAcs() { return acs; }
    public void setAcs(double acs) { this.acs = acs; }

    public String getAgentePrincipal() { return agentePrincipal; }
    public void setAgentePrincipal(String agentePrincipal) { this.agentePrincipal = agentePrincipal; }

    public double getKda() { return kda; }
    public void setKda(double kda) { this.kda = kda; }

    @Override
    public Jogo getJogo() {
        return Jogo.VALORANT;
    }

    /**
     * Fórmula de pontuação real para ranking do Valorant.
     * Combina o ACS (impacto geral em combate) e o KDA.
     */
    @Override
    public double getPontuacao() {
        // Fórmula de exemplo:
        // O ACS naturalmente é um número mais alto (ex: 200~300), então recebe um peso de 0.5
        // O KDA é um número menor (ex: 1.2), então recebe um multiplicador maior (x20)
        // Ex: ACS 250, KDA 1.5 -> (125) + (30) = 155.0 de pontuação
        return (this.acs * 0.5) + (this.kda * 20);
    }

    @Override
    public String exibir() {
        // Retorna os dados base formatados junto com as stats de Valorant
        return super.toString() + String.format(" | ACS: %.1f | KDA: %.2f | Agente: %s",
                acs, kda, agentePrincipal);
    }
}