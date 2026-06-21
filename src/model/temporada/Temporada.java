package model.temporada;

import model.enums.Jogo;


public class Temporada {

    private int id;
    private String nome;
    private int ano;
    private Jogo jogo;

    // Atributos adicionados (Pessoa 1)
    private String vencedor;
    private boolean encerrada;

    public Temporada(int id, String nome, int ano, Jogo jogo) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.jogo = jogo;
        this.encerrada = false; // A temporada começa em andamento por padrão
        this.vencedor = "Nenhum"; // Sem vencedor inicialmente
    }

    // Getters e Setters base
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    // Novos Getters e Setters
    public String getVencedor() { return vencedor; }
    public void setVencedor(String vencedor) { this.vencedor = vencedor; }

    public boolean isEncerrada() { return encerrada; }
    public void setEncerrada(boolean encerrada) { this.encerrada = encerrada; }

    //metodo para finalizar a temporada
    public void finalizarCampeonato(String timeVencedor) {
        this.encerrada = true;
        this.vencedor = timeVencedor;
    }

    @Override
    public String toString() {
        String status = encerrada ? "[Encerrada | Vencedor: " + vencedor + "]" : "[Em andamento]";
        return nome + " (" + ano + ") | Jogo: " + jogo + " " + status;
    }
}