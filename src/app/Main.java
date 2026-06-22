package app;

import exception.JogadorNaoEncontradoException;
import exception.TimeNaoEncontradoException;
import exception.TransferenciaInvalidaException;
import model.enums.Jogo;
import model.enums.Posicao;
import model.jogador.Jogador;
import model.jogador.JogadorCS;
import model.jogador.JogadorLOL;
import model.jogador.JogadorValorant;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;
import service.ArquivoService;
import service.JogadorService;
import service.RankingService;
import service.TransferenciaService;
import thread.RankingThread;

import java.util.List;
import java.util.Scanner;

/**
 * Ponto de entrada da aplicação — Menu de console.
 * Usa os repositórios e services reais (Sprint 1 + Sprint 2 completas).
 */
public class Main {

    // Repositórios compartilhados
    static JogadorRepository jogadorRepo = new JogadorRepository();
    static TimeRepository timeRepo = new TimeRepository();

    // Services
    static JogadorService jogadorService = new JogadorService(jogadorRepo);
    static TransferenciaService transferenciaService = new TransferenciaService(jogadorRepo, timeRepo);
    static RankingService rankingService = new RankingService(jogadorRepo, timeRepo);
    static ArquivoService arquivoService = new ArquivoService(jogadorRepo, timeRepo);

    static Scanner scanner = new Scanner(System.in);

    static final String LINHA  = "============================================";
    static final String LINHA2 = "--------------------------------------------";

    public static void main(String[] args) {
        carregarDadosDeTeste();

        // Inicia a thread que atualiza o ranking a cada 10 segundos em segundo plano
        RankingThread rankingThread = new RankingThread(rankingService);
        rankingThread.start();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = lerInt();

            switch (opcao) {
                case 1 -> menuJogadores();
                case 2 -> menuTimes();
                case 3 -> transferirJogador();
                case 4 -> listarJogadores();
                case 5 -> menuRanking();
                case 6 -> menuArquivos();
                case 0 -> System.out.println("\nSaindo do sistema. Até mais!");
                default -> System.out.println("  [!] Opção inválida. Tente novamente.");
            }
        }
        rankingThread.encerrar();
    }

    // =========================================================
    //  MENUS
    // =========================================================

    static void exibirMenuPrincipal() {
        System.out.println("\n" + LINHA);
        System.out.println("         ESPORTS MANAGER — MENU PRINCIPAL");
        System.out.println(LINHA);
        System.out.println("  1  » Gerenciar Jogadores");
        System.out.println("  2  » Gerenciar Times");
        System.out.println("  3  » Transferir Jogador");
        System.out.println("  4  » Listar Todos os Jogadores");
        System.out.println("  5  » Ranking");
        System.out.println("  6  » Salvar / Carregar Arquivos");
        System.out.println("  0  » Sair");
        System.out.println(LINHA2);
        System.out.print("  Escolha: ");
    }

    static void menuJogadores() {
        System.out.println("\n" + LINHA);
        System.out.println("           GERENCIAR JOGADORES");
        System.out.println(LINHA);
        System.out.println("  1  » Cadastrar Jogador");
        System.out.println("  2  » Buscar Jogador por Nick");
        System.out.println("  3  » Remover Jogador");
        System.out.println("  0  » Voltar");
        System.out.println(LINHA2);
        System.out.print("  Escolha: ");

        int opcao = lerInt();
        switch (opcao) {
            case 1 -> cadastrarJogador();
            case 2 -> buscarJogadorPorNick();
            case 3 -> removerJogador();
            case 0 -> {}
            default -> System.out.println("  [!] Opção inválida.");
        }
    }

    static void menuTimes() {
        System.out.println("\n" + LINHA);
        System.out.println("             GERENCIAR TIMES");
        System.out.println(LINHA);
        System.out.println("  1  » Cadastrar Time");
        System.out.println("  2  » Listar Times");
        System.out.println("  3  » Ver jogadores de um Time");
        System.out.println("  0  » Voltar");
        System.out.println(LINHA2);
        System.out.print("  Escolha: ");

        int opcao = lerInt();
        switch (opcao) {
            case 1 -> cadastrarTime();
            case 2 -> listarTimes();
            case 3 -> verJogadoresDoTime();
            case 0 -> {}
            default -> System.out.println("  [!] Opção inválida.");
        }
    }

    static void menuRanking() {
        System.out.println("\n" + LINHA);
        System.out.println("                 RANKING");
        System.out.println(LINHA);
        System.out.println("  1  » Ranking Geral de Jogadores");
        System.out.println("  2  » Ranking por Jogo");
        System.out.println("  3  » Ranking por Posição");
        System.out.println("  4  » Ranking de Times");
        System.out.println("  0  » Voltar");
        System.out.println(LINHA2);
        System.out.print("  Escolha: ");

        int opcao = lerInt();
        switch (opcao) {
            case 1 -> exibirRankingJogadores(rankingService.rankingGeralJogadores());
            case 2 -> {
                Jogo jogo = escolherJogo();
                if (jogo != null) exibirRankingJogadores(rankingService.rankingPorJogo(jogo));
            }
            case 3 -> {
                System.out.print("  Digite a posição exatamente (ex: TOP, AWPER, DUELISTA): ");
                String posInput = scanner.nextLine().trim().toUpperCase();
                try {
                    Posicao posicao = Posicao.valueOf(posInput);
                    exibirRankingJogadores(rankingService.rankingPorPosicao(posicao));
                } catch (IllegalArgumentException e) {
                    System.out.println("  [!] Posição inválida.");
                }
            }
            case 4 -> exibirRankingTimes(rankingService.rankingTimes());
            case 0 -> {}
            default -> System.out.println("  [!] Opção inválida.");
        }
    }

    static void menuArquivos() {
        System.out.println("\n" + LINHA);
        System.out.println("            SALVAR / CARREGAR");
        System.out.println(LINHA);
        System.out.println("  1  » Salvar Jogadores e Times em arquivo");
        System.out.println("  2  » Carregar Jogadores e Times do arquivo");
        System.out.println("  0  » Voltar");
        System.out.println(LINHA2);
        System.out.print("  Escolha: ");

        int opcao = lerInt();
        switch (opcao) {
            case 1 -> {
                arquivoService.salvarJogadores();
                arquivoService.salvarTimes();
            }
            case 2 -> {
                arquivoService.carregarTimes();
                arquivoService.carregarJogadores();
                arquivoService.religarJogadoresAosTimes();
                System.out.println("  [✓] Dados carregados e jogadores religados aos times.");
            }
            case 0 -> {}
            default -> System.out.println("  [!] Opção inválida.");
        }
    }

    // =========================================================
    //  JOGADORES
    // =========================================================

    static void cadastrarJogador() {
        System.out.println("\n" + LINHA2);
        System.out.println("  CADASTRAR JOGADOR");
        System.out.println(LINHA2);

        System.out.print("  Nick: ");
        String nick = scanner.nextLine().trim();

        System.out.print("  Nome real: ");
        String nomeReal = scanner.nextLine().trim();

        System.out.print("  Idade: ");
        int idade = lerInt();

        Jogo jogo = escolherJogo();
        if (jogo == null) return;

        Posicao posicao = escolherPosicao(jogo);
        if (posicao == null) return;

        System.out.print("  Salário (R$): ");
        double salario = lerDouble();

        Jogador novoJogador;

        switch (jogo) {
            case CS -> {
                System.out.print("  Rating (ex: 1.15): ");
                double rating = lerDouble();
                System.out.print("  Abates por partida (ex: 18): ");
                double abates = lerDouble();
                System.out.print("  Percentual de Headshot (ex: 0.45 para 45%%): ");
                double hs = lerDouble();
                novoJogador = new JogadorCS(0, nick, nomeReal, idade, posicao, salario, rating, abates, hs);
            }
            case VALORANT -> {
                System.out.print("  ACS (ex: 245.0): ");
                double acs = lerDouble();
                System.out.print("  Agente principal: ");
                String agente = scanner.nextLine().trim();
                System.out.print("  KDA (ex: 1.5): ");
                double kda = lerDouble();
                novoJogador = new JogadorValorant(0, nick, nomeReal, idade, posicao, salario, acs, agente, kda);
            }
            case LOL -> {
                System.out.print("  KDA (ex: 4.2): ");
                double kda = lerDouble();
                System.out.print("  Campeão principal: ");
                String campeao = scanner.nextLine().trim();
                System.out.print("  CS por minuto (ex: 8.5): ");
                double cs = lerDouble();
                novoJogador = new JogadorLOL(0, nick, nomeReal, idade, posicao, salario, kda, campeao, cs);
            }
            default -> { return; }
        }

        try {
            jogadorService.cadastrarJogador(novoJogador);
            System.out.println("\n  [✓] Jogador '" + nick + "' cadastrado com sucesso! ID: " + novoJogador.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("  [!] " + e.getMessage());
        }
    }

    static void buscarJogadorPorNick() {
        System.out.println("\n" + LINHA2);
        System.out.print("  Nick do jogador: ");
        String nick = scanner.nextLine().trim();

        try {
            Jogador j = jogadorService.buscarPorNick(nick);
            System.out.println("\n  " + j.exibir());
        } catch (JogadorNaoEncontradoException e) {
            System.out.println("  [!] " + e.getMessage());
        }
    }

    static void removerJogador() {
        if (jogadorRepo.listarJogadores().isEmpty()) { System.out.println("  [!] Nenhum jogador cadastrado."); return; }

        listarJogadores();
        System.out.print("\n  ID do jogador a remover: ");
        int id = lerInt();

        try {
            Jogador alvo = jogadorService.buscarPorId(id);
            // Remove do time se estiver em um, antes de remover do repositório
            if (alvo.getTimeAtual() != null) {
                timeRepo.buscarPorNome(alvo.getTimeAtual()).ifPresent(time -> time.removerJogador(alvo));
            }
            jogadorService.removerJogador(id);
            System.out.println("  [✓] Jogador '" + alvo.getNick() + "' removido.");
        } catch (JogadorNaoEncontradoException e) {
            System.out.println("  [!] " + e.getMessage());
        }
    }

    static void listarJogadores() {
        List<Jogador> jogadores = jogadorService.listarTodos();
        System.out.println("\n" + LINHA);
        System.out.println("  JOGADORES CADASTRADOS (" + jogadores.size() + ")");
        System.out.println(LINHA);

        if (jogadores.isEmpty()) {
            System.out.println("  Nenhum jogador cadastrado.");
            return;
        }

        for (Jogador j : jogadores) {
            System.out.println("  [ID " + j.getId() + "] " + j.exibir());
            System.out.println(LINHA2);
        }
    }

    // =========================================================
    //  TIMES
    // =========================================================

    static void cadastrarTime() {
        System.out.println("\n" + LINHA2);
        System.out.println("  CADASTRAR TIME");
        System.out.println(LINHA2);

        System.out.print("  Nome do time: ");
        String nome = scanner.nextLine().trim();

        System.out.print("  Tag (ex: FURIA): ");
        String tag = scanner.nextLine().trim().toUpperCase();

        Jogo jogo = escolherJogo();
        if (jogo == null) return;

        System.out.print("  País: ");
        String pais = scanner.nextLine().trim();

        System.out.print("  Ano de fundação: ");
        int ano = lerInt();

        Time novoTime = new Time(0, nome, tag, jogo, pais, ano);
        timeRepo.adicionarTime(novoTime);
        System.out.println("\n  [✓] Time '" + nome + "' cadastrado! ID: " + novoTime.getId());
    }

    static void listarTimes() {
        List<Time> times = timeRepo.listarTimes();
        System.out.println("\n" + LINHA);
        System.out.println("  TIMES CADASTRADOS (" + times.size() + ")");
        System.out.println(LINHA);

        if (times.isEmpty()) {
            System.out.println("  Nenhum time cadastrado.");
            return;
        }

        for (Time t : times) {
            System.out.println("  [ID " + t.getId() + "] " + t);
            System.out.println(LINHA2);
        }
    }

    static void verJogadoresDoTime() {
        if (timeRepo.listarTimes().isEmpty()) { System.out.println("  [!] Nenhum time cadastrado."); return; }

        listarTimes();
        System.out.print("\n  ID do time: ");
        int id = lerInt();

        var timeOpt = timeRepo.buscarPorId(id);
        if (timeOpt.isEmpty()) { System.out.println("  [!] Time não encontrado."); return; }

        Time time = timeOpt.get();
        System.out.println("\n  " + time.exibir());
    }

    // =========================================================
    //  TRANSFERÊNCIA
    // =========================================================

    static void transferirJogador() {
        System.out.println("\n" + LINHA);
        System.out.println("  TRANSFERIR JOGADOR");
        System.out.println(LINHA);

        if (jogadorRepo.listarJogadores().isEmpty()) { System.out.println("  [!] Nenhum jogador cadastrado."); return; }
        if (timeRepo.listarTimes().isEmpty())        { System.out.println("  [!] Nenhum time cadastrado."); return; }

        listarJogadores();
        System.out.print("\n  ID do jogador: ");
        int idJogador = lerInt();

        listarTimes();
        System.out.print("\n  ID do time de destino: ");
        int idTime = lerInt();

        try {
            transferenciaService.transferirJogador(idJogador, idTime);
            Jogador jogador = jogadorService.buscarPorId(idJogador);
            System.out.println("\n  [✓] " + jogador.getNick() + " transferido para " + jogador.getTimeAtual() + "!");
        } catch (JogadorNaoEncontradoException | TimeNaoEncontradoException | TransferenciaInvalidaException e) {
            System.out.println("  [!] " + e.getMessage());
        }
    }

    // =========================================================
    //  RANKING — EXIBIÇÃO
    // =========================================================

    static void exibirRankingJogadores(List<Jogador> ranking) {
        System.out.println("\n" + LINHA);
        System.out.println("  RANKING DE JOGADORES");
        System.out.println(LINHA);

        if (ranking.isEmpty()) {
            System.out.println("  Nenhum jogador encontrado.");
            return;
        }

        int posicao = 1;
        for (Jogador j : ranking) {
            System.out.println("  " + posicao + "º - " + j.getNick() + " (" + j.getJogo() + ") | "
                    + String.format("%.2f", j.getPontuacao()) + " pts");
            posicao++;
        }
    }

    static void exibirRankingTimes(List<Time> ranking) {
        System.out.println("\n" + LINHA);
        System.out.println("  RANKING DE TIMES");
        System.out.println(LINHA);

        if (ranking.isEmpty()) {
            System.out.println("  Nenhum time encontrado.");
            return;
        }

        int posicao = 1;
        for (Time t : ranking) {
            System.out.println("  " + posicao + "º - " + t.getTag() + " (" + t.getJogo() + ") | "
                    + String.format("%.2f", t.getPontuacao()) + " pts");
            posicao++;
        }
    }

    // =========================================================
    //  HELPERS DE SELEÇÃO
    // =========================================================

    static Jogo escolherJogo() {
        System.out.println("  Jogo:");
        System.out.println("    1 » CS");
        System.out.println("    2 » Valorant");
        System.out.println("    3 » League of Legends");
        System.out.print("  Escolha: ");
        return switch (lerInt()) {
            case 1 -> Jogo.CS;
            case 2 -> Jogo.VALORANT;
            case 3 -> Jogo.LOL;
            default -> { System.out.println("  [!] Jogo inválido."); yield null; }
        };
    }

    static Posicao escolherPosicao(Jogo jogo) {
        if (jogo == Jogo.LOL) {
            System.out.println("  Posição:");
            System.out.println("    1 » Top   2 » Jungle   3 » Mid   4 » ADC   5 » Suporte");
            System.out.print("  Escolha: ");
            return switch (lerInt()) {
                case 1 -> Posicao.TOP;
                case 2 -> Posicao.JUNGLE;
                case 3 -> Posicao.MID;
                case 4 -> Posicao.ADC;
                case 5 -> Posicao.SUPORTELOL;
                default -> { System.out.println("  [!] Posição inválida."); yield null; }
            };
        } else {
            System.out.println("  Posição:");
            System.out.println("    1 » Capitão     2 » Fragger     3 » Suporte");
            System.out.println("    4 » Awper       5 » Lurker      6 » Duelista");
            System.out.println("    7 » Iniciador   8 » Controlador 9 » Sentinela");
            System.out.print("  Escolha: ");
            return switch (lerInt()) {
                case 1 -> Posicao.CAPITAO;
                case 2 -> Posicao.FRAGGER;
                case 3 -> Posicao.SUPORTE;
                case 4 -> Posicao.AWPER;
                case 5 -> Posicao.LURKER;
                case 6 -> Posicao.DUELISTA;
                case 7 -> Posicao.INICIADOR;
                case 8 -> Posicao.CONTROLADOR;
                case 9 -> Posicao.SENTINELA;
                default -> { System.out.println("  [!] Posição inválida."); yield null; }
            };
        }
    }

    // =========================================================
    //  LEITURA SEGURA DE INPUT
    // =========================================================

    static int lerInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // =========================================================
    //  DADOS DE TESTE
    // =========================================================

    static void carregarDadosDeTeste() {
        Time furia = new Time(0, "FURIA Esports", "FURIA", Jogo.CS, "Brasil", 2017);
        Time loud  = new Time(0, "LOUD",          "LOUD",  Jogo.VALORANT, "Brasil", 2019);
        Time pain  = new Time(0, "paiN Gaming",   "paiN",  Jogo.LOL, "Brasil", 2002);
        timeRepo.adicionarTime(furia);
        timeRepo.adicionarTime(loud);
        timeRepo.adicionarTime(pain);

        Jogador fallen   = new JogadorCS(0, "FalleN",   "Gabriel Toledo",  32, Posicao.AWPER,    50000, 1.08, 16.5, 0.42);
        Jogador kscerato = new JogadorCS(0, "kscerato",  "Kaike Cerato",    25, Posicao.FRAGGER,  45000, 1.21, 19.0, 0.50);

        Jogador aspas    = new JogadorValorant(0, "aspas",   "Erick Santos",     21, Posicao.DUELISTA, 40000, 285.3, "Jett",  1.45);
        Jogador saadhak  = new JogadorValorant(0, "saadhak", "Matias Delipetro", 24, Posicao.CONTROLADOR, 38000, 210.5, "Viper", 1.10);

        Jogador tinowns  = new JogadorLOL(0, "Tinowns", "Antonio Lira",   26, Posicao.MID,    35000, 5.2, "Azir", 8.7);
        Jogador cariok   = new JogadorLOL(0, "Cariok",  "Marcos Oliveira",28, Posicao.JUNGLE, 33000, 4.8, "Vi",   6.2);

        jogadorService.cadastrarJogador(fallen);
        jogadorService.cadastrarJogador(kscerato);
        jogadorService.cadastrarJogador(aspas);
        jogadorService.cadastrarJogador(saadhak);
        jogadorService.cadastrarJogador(tinowns);
        jogadorService.cadastrarJogador(cariok);

        fallen.setTimeAtual(furia.getNome());     furia.adicionarJogador(fallen);
        kscerato.setTimeAtual(furia.getNome());   furia.adicionarJogador(kscerato);
        aspas.setTimeAtual(loud.getNome());       loud.adicionarJogador(aspas);
        saadhak.setTimeAtual(loud.getNome());     loud.adicionarJogador(saadhak);
        tinowns.setTimeAtual(pain.getNome());     pain.adicionarJogador(tinowns);
        cariok.setTimeAtual(pain.getNome());      pain.adicionarJogador(cariok);

        System.out.println(LINHA);
        System.out.println("   ESPORTS MANAGER");
        System.out.println(LINHA);
        System.out.println("  [i] " + jogadorService.listarTodos().size() + " jogadores e "
                + timeRepo.listarTimes().size() + " times carregados.");
    }
}
