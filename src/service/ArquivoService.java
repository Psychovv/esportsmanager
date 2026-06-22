package service;

import model.enums.Jogo;
import model.enums.Posicao;
import model.jogador.Jogador;
import model.jogador.JogadorCS;
import model.jogador.JogadorLOL;
import model.jogador.JogadorValorant;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;

import java.io.*;
import java.util.List;

/**
 * Serviço responsável por salvar e carregar dados em arquivos de texto.
 * Formato simples: um registro por linha, campos separados por "|".
 */
public class ArquivoService {

    private static final String ARQUIVO_JOGADORES = "jogadores.txt";
    private static final String ARQUIVO_TIMES = "times.txt";
    private static final String SEP = "\\|";

    private final JogadorRepository jogadorRepository;
    private final TimeRepository timeRepository;

    public ArquivoService(JogadorRepository jogadorRepository, TimeRepository timeRepository) {
        this.jogadorRepository = jogadorRepository;
        this.timeRepository = timeRepository;
    }

    /**
     * Salva todos os jogadores no arquivo de texto.
     * Formato por jogo:
     *   CS:       CS|id|nick|nomeReal|idade|posicao|salario|timeAtual|rating|abatesPorPartida|percentualHS
     *   VALORANT: VALORANT|id|nick|nomeReal|idade|posicao|salario|timeAtual|acs|agentePrincipal|kda
     *   LOL:      LOL|id|nick|nomeReal|idade|posicao|salario|timeAtual|kda|campeaoPrincipal|csPorMin
     */
    public void salvarJogadores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_JOGADORES))) {
            for (Jogador j : jogadorRepository.listarJogadores()) {
                String time = j.getTimeAtual() != null ? j.getTimeAtual() : "NULL";
                StringBuilder linha = new StringBuilder();

                if (j instanceof JogadorCS cs) {
                    linha.append("CS|").append(cs.getId()).append("|").append(cs.getNick()).append("|")
                            .append(cs.getNomeReal()).append("|").append(cs.getIdade()).append("|")
                            .append(cs.getPosicao()).append("|").append(cs.getSalario()).append("|")
                            .append(time).append("|").append(cs.getRating()).append("|")
                            .append(cs.getAbatesPorPartida()).append("|").append(cs.getPercentualHS());
                } else if (j instanceof JogadorValorant val) {
                    linha.append("VALORANT|").append(val.getId()).append("|").append(val.getNick()).append("|")
                            .append(val.getNomeReal()).append("|").append(val.getIdade()).append("|")
                            .append(val.getPosicao()).append("|").append(val.getSalario()).append("|")
                            .append(time).append("|").append(val.getAcs()).append("|")
                            .append(val.getAgentePrincipal()).append("|").append(val.getKda());
                } else if (j instanceof JogadorLOL lol) {
                    linha.append("LOL|").append(lol.getId()).append("|").append(lol.getNick()).append("|")
                            .append(lol.getNomeReal()).append("|").append(lol.getIdade()).append("|")
                            .append(lol.getPosicao()).append("|").append(lol.getSalario()).append("|")
                            .append(time).append("|").append(lol.getKda()).append("|")
                            .append(lol.getCampeaoPrincipal()).append("|").append(lol.getCsPorMin());
                } else {
                    continue; // tipo desconhecido, ignora
                }

                bw.write(linha.toString());
                bw.newLine();
            }
            System.out.println("[ArquivoService] Jogadores salvos em " + ARQUIVO_JOGADORES);
        } catch (IOException e) {
            System.out.println("[ArquivoService] Erro ao salvar jogadores: " + e.getMessage());
        }
    }

    /**
     * Carrega jogadores do arquivo de texto para o repositório.
     * Substitui completamente a lista atual do repositório.
     */
    public void carregarJogadores() {
        File arquivo = new File(ARQUIVO_JOGADORES);
        if (!arquivo.exists()) {
            System.out.println("[ArquivoService] Arquivo " + ARQUIVO_JOGADORES + " não encontrado.");
            return;
        }

        jogadorRepository.getLista().clear();
        int maiorId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] campos = linha.split(SEP);

                String tipo = campos[0];
                int id = Integer.parseInt(campos[1]);
                String nick = campos[2];
                String nomeReal = campos[3];
                int idade = Integer.parseInt(campos[4]);
                Posicao posicao = Posicao.valueOf(campos[5]);
                double salario = Double.parseDouble(campos[6]);
                String timeAtual = campos[7].equals("NULL") ? null : campos[7];

                Jogador jogador;
                switch (tipo) {
                    case "CS" -> jogador = new JogadorCS(id, nick, nomeReal, idade, posicao, salario,
                            Double.parseDouble(campos[8]), Double.parseDouble(campos[9]), Double.parseDouble(campos[10]));
                    case "VALORANT" -> jogador = new JogadorValorant(id, nick, nomeReal, idade, posicao, salario,
                            Double.parseDouble(campos[8]), campos[9], Double.parseDouble(campos[10]));
                    case "LOL" -> jogador = new JogadorLOL(id, nick, nomeReal, idade, posicao, salario,
                            Double.parseDouble(campos[8]), campos[9], Double.parseDouble(campos[10]));
                    default -> jogador = null;
                }
                if (jogador == null) continue;

                jogador.setTimeAtual(timeAtual);
                jogadorRepository.getLista().add(jogador);
                if (id > maiorId) maiorId = id;
            }
            System.out.println("[ArquivoService] Jogadores carregados de " + ARQUIVO_JOGADORES);
        } catch (IOException e) {
            System.out.println("[ArquivoService] Erro ao carregar jogadores: " + e.getMessage());
        }
    }

    /**
     * Salva todos os times no arquivo de texto.
     * Formato: id|nome|tag|jogo|pais|anoFundacao
     */
    public void salvarTimes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_TIMES))) {
            for (Time t : timeRepository.listarTimes()) {
                String linha = t.getId() + "|" + t.getNome() + "|" + t.getTag() + "|"
                        + t.getJogo() + "|" + t.getPais() + "|" + t.getAnoFundacao();
                bw.write(linha);
                bw.newLine();
            }
            System.out.println("[ArquivoService] Times salvos em " + ARQUIVO_TIMES);
        } catch (IOException e) {
            System.out.println("[ArquivoService] Erro ao salvar times: " + e.getMessage());
        }
    }

    /**
     * Carrega times do arquivo de texto para o repositório.
     * Obs: os jogadores de cada time precisam ser religados depois de carregarJogadores(),
     * usando o campo timeAtual de cada jogador.
     */
    public void carregarTimes() {
        File arquivo = new File(ARQUIVO_TIMES);
        if (!arquivo.exists()) {
            System.out.println("[ArquivoService] Arquivo " + ARQUIVO_TIMES + " não encontrado.");
            return;
        }

        timeRepository.getLista().clear();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] campos = linha.split(SEP);

                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String tag = campos[2];
                Jogo jogo = Jogo.valueOf(campos[3]);
                String pais = campos[4];
                int anoFundacao = Integer.parseInt(campos[5]);

                Time time = new Time(id, nome, tag, jogo, pais, anoFundacao);
                timeRepository.getLista().add(time);
            }
            System.out.println("[ArquivoService] Times carregados de " + ARQUIVO_TIMES);
        } catch (IOException e) {
            System.out.println("[ArquivoService] Erro ao carregar times: " + e.getMessage());
        }
    }

    /**
     * Reconecta cada jogador carregado ao seu time (com base no campo timeAtual).
     * Chamar depois de carregarJogadores() e carregarTimes().
     */
    public void religarJogadoresAosTimes() {
        for (Jogador j : jogadorRepository.listarJogadores()) {
            if (j.getTimeAtual() != null) {
                timeRepository.buscarPorNome(j.getTimeAtual())
                        .ifPresent(time -> time.adicionarJogador(j));
            }
        }
    }
}
