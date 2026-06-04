package app;

import model.enums.Jogo;
import model.enums.Posicao;
import model.jogador.JogadorCS;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;
import service.JogadorService;
import service.RankingService;
import service.TransferenciaService;

import java.util.Scanner;

/**
 * Ponto de entrada da aplicação — Menu de console.
 * PESSOA 4 — implementar os menus e chamar os services corretos.
 */
public class Main {

    // Repositórios compartilhados
    static JogadorRepository jogadorRepo = new JogadorRepository();
    static TimeRepository timeRepo = new TimeRepository();

    // Services
    static JogadorService jogadorService = new JogadorService(jogadorRepo);
    static TransferenciaService transferenciaService = new TransferenciaService(jogadorRepo, timeRepo);
    static RankingService rankingService = new RankingService(jogadorRepo, timeRepo);

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Esports Manager ===");

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> cadastrarJogador();
                case 2 -> cadastrarTime();
                case 3 -> transferirJogador();
                case 4 -> listarJogadores();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Cadastrar Jogador");
        System.out.println("2 - Cadastrar Time");
        System.out.println("3 - Transferir Jogador");
        System.out.println("4 - Listar Jogadores");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    static void cadastrarJogador() {
        // TODO Pessoa 4: ler dados do scanner e chamar jogadorService.cadastrarJogador()
        System.out.println("[TODO] Cadastrar Jogador — Pessoa 4 implementa aqui");
    }

    static void cadastrarTime() {
        // TODO Pessoa 4: ler dados do scanner e chamar timeRepo.adicionarTime()
        System.out.println("[TODO] Cadastrar Time — Pessoa 4 implementa aqui");
    }

    static void transferirJogador() {
        // TODO Pessoa 4: ler IDs e chamar transferenciaService.transferirJogador()
        System.out.println("[TODO] Transferir Jogador — Pessoa 4 implementa aqui");
    }

    static void listarJogadores() {
        // TODO Pessoa 4: chamar jogadorService.listarTodos() e exibir
        System.out.println("[TODO] Listar Jogadores — Pessoa 4 implementa aqui");
    }
}
