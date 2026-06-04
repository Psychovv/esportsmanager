package app;

import model.enums.Jogo;
import model.enums.Posicao;
import model.jogador.Jogador;
import model.jogador.JogadorCS;
import model.jogador.JogadorLOL;
import model.jogador.JogadorValorant;
import model.time.Time;
import repository.JogadorRepository;
import repository.TimeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ponto de entrada da aplicação — Menu de console.
 * Pessoa 4 — Sprint 1
 *
 * ATENÇÃO: enquanto P2 e P3 não implementarem os services/repositórios,
 * os dados são gerenciados direto aqui com ArrayLists temporários.
 * Quando estiverem prontos, trocar pelas chamadas dos services.
 */
public class Main {

    // --- Dados temporários (remover quando P2/P3 estiverem prontos) ---
    static ArrayList<Jogador> jogadores = new ArrayList<>();
    static ArrayList<Time> times = new ArrayList<>();
    static int proximoIdJogador = 1;
    static int proximoIdTime = 1;

    static Scanner scanner = new Scanner(System.in);

    // Separadores visuais
    static final String LINHA  = "============================================";
    static final String LINHA2 = "--------------------------------------------";

    public static void main(String[] args) {
        carregarDadosDeTeste(); // dados fake pra demonstração

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = lerInt();

            switch (opcao) {
                case 1 -> menuJogadores();
                case 2 -> menuTimes();
                case 3 -> transferirJogador();
                case 4 -> listarJogadores();
                case 0 -> System.out.println("\nSaindo do sistema. Até mais!");
                default -> System.out.println("  [!] Opção inválida. Tente novamente.");
            }
        }
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

    // =========================================================
    //  JOGADORES
    // =========================================================

    static void cadastrarJogador() {
        System.out.println("\n" + LINHA2);
        System.out.println("  CADASTRAR JOGADOR");
        System.out.println(LINHA2);

        System.out.print("  Nick: ");
        String nick = scanner.nextLine().trim();
        if (nick.isEmpty()) { System.out.println("  [!] Nick não pode ser vazio."); return; }

        if (buscarPorNick(nick) != null) {
            System.out.println("  [!] Já existe um jogador com esse nick.");
            return;
        }

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
                novoJogador = new JogadorCS(proximoIdJogador++, nick, nomeReal, idade, posicao, salario, rating);
            }
            case VALORANT -> {
                System.out.print("  ACS (Average Combat Score, ex: 245.0): ");
                double acs = lerDouble();
                System.out.print("  Agente principal: ");
                String agente = scanner.nextLine().trim();
                novoJogador = new JogadorValorant(proximoIdJogador++, nick, nomeReal, idade, posicao, salario, acs, agente);
            }
            case LOL -> {
                System.out.print("  KDA (ex: 4.2): ");
                double kda = lerDouble();
                System.out.print("  Campeão principal: ");
                String campeao = scanner.nextLine().trim();
                novoJogador = new JogadorLOL(proximoIdJogador++, nick, nomeReal, idade, posicao, salario, kda, campeao);
            }
            default -> { return; }
        }

        jogadores.add(novoJogador);
        System.out.println("\n  [✓] Jogador '" + nick + "' cadastrado com sucesso! ID: " + novoJogador.getId());
    }

    static void buscarJogadorPorNick() {
        System.out.println("\n" + LINHA2);
        System.out.print("  Nick do jogador: ");
        String nick = scanner.nextLine().trim();

        Jogador j = buscarPorNick(nick);
        if (j == null) {
            System.out.println("  [!] Jogador '" + nick + "' não encontrado.");
        } else {
            System.out.println("\n  " + j.exibir());
        }
    }

    static void removerJogador() {
        if (jogadores.isEmpty()) { System.out.println("  [!] Nenhum jogador cadastrado."); return; }

        listarJogadores();
        System.out.print("\n  ID do jogador a remover: ");
        int id = lerInt();

        Jogador alvo = buscarPorId(id);
        if (alvo == null) {
            System.out.println("  [!] Jogador com ID " + id + " não encontrado.");
            return;
        }

        // Remove do time se estiver em um
        if (alvo.getTimeAtual() != null) {
            Time time = buscarTimePorNome(alvo.getTimeAtual());
            if (time != null) time.removerJogador(alvo);
        }

        jogadores.remove(alvo);
        System.out.println("  [✓] Jogador '" + alvo.getNick() + "' removido.");
    }

    static void listarJogadores() {
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
        if (nome.isEmpty()) { System.out.println("  [!] Nome não pode ser vazio."); return; }

        System.out.print("  Tag (ex: FURIA): ");
        String tag = scanner.nextLine().trim().toUpperCase();

        Jogo jogo = escolherJogo();
        if (jogo == null) return;

        Time novoTime = new Time(proximoIdTime++, nome, tag, jogo);
        times.add(novoTime);
        System.out.println("\n  [✓] Time '" + nome + "' cadastrado! ID: " + novoTime.getId());
    }

    static void listarTimes() {
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
        if (times.isEmpty()) { System.out.println("  [!] Nenhum time cadastrado."); return; }

        listarTimes();
        System.out.print("\n  ID do time: ");
        int id = lerInt();

        Time time = buscarTimePorId(id);
        if (time == null) { System.out.println("  [!] Time não encontrado."); return; }

        System.out.println("\n  Time: " + time.getNome() + " [" + time.getTag() + "]");
        System.out.println(LINHA2);
        if (time.getJogadores().isEmpty()) {
            System.out.println("  Nenhum jogador neste time.");
        } else {
            for (Jogador j : time.getJogadores()) {
                System.out.println("  » " + j.getNick() + " | " + j.getPosicao());
            }
        }
    }

    // =========================================================
    //  TRANSFERÊNCIA
    // =========================================================

    static void transferirJogador() {
        System.out.println("\n" + LINHA);
        System.out.println("  TRANSFERIR JOGADOR");
        System.out.println(LINHA);

        if (jogadores.isEmpty()) { System.out.println("  [!] Nenhum jogador cadastrado."); return; }
        if (times.isEmpty())     { System.out.println("  [!] Nenhum time cadastrado."); return; }

        listarJogadores();
        System.out.print("\n  ID do jogador: ");
        int idJogador = lerInt();
        Jogador jogador = buscarPorId(idJogador);
        if (jogador == null) { System.out.println("  [!] Jogador não encontrado."); return; }

        listarTimes();
        System.out.print("\n  ID do time de destino: ");
        int idTime = lerInt();
        Time destino = buscarTimePorId(idTime);
        if (destino == null) { System.out.println("  [!] Time não encontrado."); return; }

        // Validar jogo compatível
        if (!jogador.getJogo().equals(destino.getJogo())) {
            System.out.println("  [!] Incompatível: jogador é de " + jogador.getJogo()
                    + " mas o time é de " + destino.getJogo() + ".");
            return;
        }

        // Remover do time anterior
        if (jogador.getTimeAtual() != null) {
            Time origem = buscarTimePorNome(jogador.getTimeAtual());
            if (origem != null) origem.removerJogador(jogador);
        }

        // Adicionar no novo time
        destino.adicionarJogador(jogador);
        jogador.setTimeAtual(destino.getNome());

        System.out.println("\n  [✓] " + jogador.getNick() + " transferido para " + destino.getNome() + "!");
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
            System.out.println("    1 » Top   2 » Jungle   3 » Mid   4 » ADC   5 » Support");
            System.out.print("  Escolha: ");
            return switch (lerInt()) {
                case 1 -> Posicao.TOP;
                case 2 -> Posicao.JUNGLE;
                case 3 -> Posicao.MID;
                case 4 -> Posicao.ADC;
                case 5 -> Posicao.SUP;
                default -> { System.out.println("  [!] Posição inválida."); yield null; }
            };
        } else {
            System.out.println("  Posição:");
            System.out.println("    1 » Duelista   2 » Suporte   3 » Sniper   4 » Capitão   5 » Entry");
            System.out.print("  Escolha: ");
            return switch (lerInt()) {
                case 1 -> Posicao.DUELISTA;
                case 2 -> Posicao.SUPORTE;
                case 3 -> Posicao.SNIPER;
                case 4 -> Posicao.CAPITAO;
                case 5 -> Posicao.ENTRY;
                default -> { System.out.println("  [!] Posição inválida."); yield null; }
            };
        }
    }

    // =========================================================
    //  HELPERS DE BUSCA (temporários — substituir pelos services)
    // =========================================================

    static Jogador buscarPorNick(String nick) {
        for (Jogador j : jogadores)
            if (j.getNick().equalsIgnoreCase(nick)) return j;
        return null;
    }

    static Jogador buscarPorId(int id) {
        for (Jogador j : jogadores)
            if (j.getId() == id) return j;
        return null;
    }

    static Time buscarTimePorId(int id) {
        for (Time t : times)
            if (t.getId() == id) return t;
        return null;
    }

    static Time buscarTimePorNome(String nome) {
        for (Time t : times)
            if (t.getNome().equalsIgnoreCase(nome)) return t;
        return null;
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
    //  DADOS DE TESTE (remover quando P2/P3 estiverem prontos)
    // =========================================================

    static void carregarDadosDeTeste() {
        // Times
        Time furia  = new Time(proximoIdTime++, "FURIA Esports", "FURIA", Jogo.CS);
        Time loud   = new Time(proximoIdTime++, "LOUD",          "LOUD",  Jogo.VALORANT);
        Time pain   = new Time(proximoIdTime++, "paiN Gaming",   "paiN",  Jogo.LOL);
        times.add(furia);
        times.add(loud);
        times.add(pain);

        // Jogadores CS
        Jogador fallen = new JogadorCS(proximoIdJogador++, "FalleN",  "Gabriel Toledo",  32, Posicao.SNIPER,   50000, 1.08);
        Jogador kscerato = new JogadorCS(proximoIdJogador++, "kscerato","Kaike Cerato",   25, Posicao.ENTRY,    45000, 1.21);

        // Jogadores Valorant
        Jogador aspas  = new JogadorValorant(proximoIdJogador++, "aspas",  "Erick Santos", 21, Posicao.DUELISTA, 40000, 285.3, "Jett");
        Jogador saadhak = new JogadorValorant(proximoIdJogador++, "saadhak","Matias Delipetro",24,Posicao.CAPITAO,38000,210.5,"Viper");

        // Jogadores LOL
        Jogador tinowns = new JogadorLOL(proximoIdJogador++, "Tinowns", "Antonio Lira",  26, Posicao.MID,      35000, 5.2, "Azir");
        Jogador cariok  = new JogadorLOL(proximoIdJogador++, "Cariok",  "Marcos Oliveira",28,Posicao.JUNGLE,   33000, 4.8, "Vi");

        // Adicionar times
        fallen.setTimeAtual(furia.getNome());    furia.adicionarJogador(fallen);
        kscerato.setTimeAtual(furia.getNome());  furia.adicionarJogador(kscerato);
        aspas.setTimeAtual(loud.getNome());      loud.adicionarJogador(aspas);
        saadhak.setTimeAtual(loud.getNome());    loud.adicionarJogador(saadhak);
        tinowns.setTimeAtual(pain.getNome());    pain.adicionarJogador(tinowns);
        cariok.setTimeAtual(pain.getNome());     pain.adicionarJogador(cariok);

        jogadores.add(fallen);
        jogadores.add(kscerato);
        jogadores.add(aspas);
        jogadores.add(saadhak);
        jogadores.add(tinowns);
        jogadores.add(cariok);

        System.out.println(LINHA);
        System.out.println("   ESPORTS MANAGER  |  Sprint 1");
        System.out.println(LINHA);
        System.out.println("  [i] " + jogadores.size() + " jogadores e " + times.size() + " times carregados.");
    }
}
