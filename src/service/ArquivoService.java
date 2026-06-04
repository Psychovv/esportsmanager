package service;

import model.jogador.Jogador;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;

import java.io.*;
import java.util.List;

/**
 * Serviço responsável por salvar e carregar dados em arquivos de texto.
 * PESSOA 2 (Sprint 2) — implementar usando FileWriter/BufferedWriter/FileReader/BufferedReader.
 */
public class ArquivoService {

    private static final String ARQUIVO_JOGADORES = "jogadores.txt";
    private static final String ARQUIVO_TIMES = "times.txt";

    private final JogadorRepository jogadorRepository;
    private final TimeRepository timeRepository;

    public ArquivoService(JogadorRepository jogadorRepository, TimeRepository timeRepository) {
        this.jogadorRepository = jogadorRepository;
        this.timeRepository = timeRepository;
    }

    /**
     * Salva todos os jogadores no arquivo de texto.
     * TODO Pessoa 2 Sprint 2: implementar com FileWriter + BufferedWriter.
     * Sugestão: salvar cada jogador em uma linha com campos separados por "|"
     */
    public void salvarJogadores() {
        // TODO: abrir FileWriter(ARQUIVO_JOGADORES), BufferedWriter, escrever linha por jogador
        throw new UnsupportedOperationException("Não implementado ainda — Sprint 2");
    }

    /**
     * Carrega jogadores do arquivo de texto para o repositório.
     * TODO Pessoa 2 Sprint 2: implementar com FileReader + BufferedReader.
     */
    public void carregarJogadores() {
        // TODO: abrir FileReader(ARQUIVO_JOGADORES), ler linha por linha, reconstruir objetos
        throw new UnsupportedOperationException("Não implementado ainda — Sprint 2");
    }

    /**
     * Salva todos os times no arquivo de texto.
     * TODO Pessoa 2 Sprint 2: implementar.
     */
    public void salvarTimes() {
        throw new UnsupportedOperationException("Não implementado ainda — Sprint 2");
    }

    /**
     * Carrega times do arquivo de texto para o repositório.
     * TODO Pessoa 2 Sprint 2: implementar.
     */
    public void carregarTimes() {
        throw new UnsupportedOperationException("Não implementado ainda — Sprint 2");
    }
}
