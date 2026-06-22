package view;

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
import repository.TimeRepository;
import service.JogadorService;
import service.TransferenciaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de cadastro, listagem e transferência de jogadores.
 */
public class TelaJogador extends JFrame {

    private final JogadorService jogadorService;
    private final TimeRepository timeRepo;
    private final TransferenciaService transferenciaService;

    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public TelaJogador(JogadorService jogadorService, TimeRepository timeRepo, TransferenciaService transferenciaService) {
        this.jogadorService = jogadorService;
        this.timeRepo = timeRepo;
        this.transferenciaService = transferenciaService;

        setTitle("Gerenciar Jogadores");
        setSize(800, 500);
        setLocationRelativeTo(null);

        // Tabela
        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nick", "Jogo", "Posição", "Time", "Pontuação"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabela = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabela);

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnCadastrar = new JButton("Cadastrar Jogador");
        JButton btnRemover = new JButton("Remover Selecionado");
        JButton btnTransferir = new JButton("Transferir Selecionado");
        JButton btnAtualizar = new JButton("Atualizar Lista");

        btnCadastrar.addActionListener(e -> abrirFormularioCadastro());
        btnRemover.addActionListener(e -> removerSelecionado());
        btnTransferir.addActionListener(e -> transferirSelecionado());
        btnAtualizar.addActionListener(e -> atualizarTabela());

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnTransferir);
        painelBotoes.add(btnAtualizar);

        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Jogador j : jogadorService.listarTodos()) {
            modeloTabela.addRow(new Object[]{
                    j.getId(), j.getNick(), j.getJogo(), j.getPosicao(),
                    j.getTimeAtual() != null ? j.getTimeAtual() : "Sem time",
                    String.format("%.2f", j.getPontuacao())
            });
        }
    }

    private int idSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um jogador na tabela primeiro.");
            return -1;
        }
        return (int) modeloTabela.getValueAt(linha, 0);
    }

    private void removerSelecionado() {
        int id = idSelecionado();
        if (id == -1) return;

        try {
            jogadorService.removerJogador(id);
            atualizarTabela();
        } catch (JogadorNaoEncontradoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void transferirSelecionado() {
        int id = idSelecionado();
        if (id == -1) return;

        List<Time> times = timeRepo.listarTimes();
        if (times.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum time cadastrado ainda.");
            return;
        }

        String[] nomesTimes = times.stream().map(Time::getNome).toArray(String[]::new);
        String escolha = (String) JOptionPane.showInputDialog(this, "Transferir para qual time?",
                "Transferência", JOptionPane.QUESTION_MESSAGE, null, nomesTimes, nomesTimes[0]);

        if (escolha == null) return;

        Time destino = times.stream().filter(t -> t.getNome().equals(escolha)).findFirst().orElse(null);
        if (destino == null) return;

        try {
            transferenciaService.transferirJogador(id, destino.getId());
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Jogador transferido com sucesso!");
        } catch (JogadorNaoEncontradoException | TimeNaoEncontradoException | TransferenciaInvalidaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void abrirFormularioCadastro() {
        JTextField nickField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField idadeField = new JTextField();
        JComboBox<Jogo> jogoBox = new JComboBox<>(Jogo.values());
        JComboBox<Posicao> posicaoBox = new JComboBox<>(Posicao.values());
        JTextField salarioField = new JTextField();
        JTextField stat1Field = new JTextField(); // rating / acs / kda
        JTextField stat2Field = new JTextField(); // abates / agente / campeao
        JTextField stat3Field = new JTextField(); // hs% / kda / cs por min

        JLabel stat1Label = new JLabel("Stat 1:");
        JLabel stat2Label = new JLabel("Stat 2:");
        JLabel stat3Label = new JLabel("Stat 3:");

        atualizarLabelsStats(jogoBox.getItemAt(0), stat1Label, stat2Label, stat3Label);
        jogoBox.addActionListener(e ->
                atualizarLabelsStats((Jogo) jogoBox.getSelectedItem(), stat1Label, stat2Label, stat3Label));

        JPanel painel = new JPanel(new GridLayout(0, 2, 5, 5));
        painel.add(new JLabel("Nick:"));         painel.add(nickField);
        painel.add(new JLabel("Nome real:"));    painel.add(nomeField);
        painel.add(new JLabel("Idade:"));        painel.add(idadeField);
        painel.add(new JLabel("Jogo:"));         painel.add(jogoBox);
        painel.add(new JLabel("Posição:"));      painel.add(posicaoBox);
        painel.add(new JLabel("Salário:"));      painel.add(salarioField);
        painel.add(stat1Label);                  painel.add(stat1Field);
        painel.add(stat2Label);                  painel.add(stat2Field);
        painel.add(stat3Label);                  painel.add(stat3Field);

        int resultado = JOptionPane.showConfirmDialog(this, painel, "Cadastrar Jogador",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado != JOptionPane.OK_OPTION) return;

        try {
            String nick = nickField.getText().trim();
            String nome = nomeField.getText().trim();
            int idade = Integer.parseInt(idadeField.getText().trim());
            Jogo jogo = (Jogo) jogoBox.getSelectedItem();
            Posicao posicao = (Posicao) posicaoBox.getSelectedItem();
            double salario = Double.parseDouble(salarioField.getText().trim().replace(",", "."));
            double s1 = Double.parseDouble(stat1Field.getText().trim().replace(",", "."));

            Jogador novoJogador;
            switch (jogo) {
                case CS -> {
                    double s2 = Double.parseDouble(stat2Field.getText().trim().replace(",", "."));
                    double s3 = Double.parseDouble(stat3Field.getText().trim().replace(",", "."));
                    novoJogador = new JogadorCS(0, nick, nome, idade, posicao, salario, s1, s2, s3);
                }
                case VALORANT -> {
                    String agente = stat2Field.getText().trim();
                    double kda = Double.parseDouble(stat3Field.getText().trim().replace(",", "."));
                    novoJogador = new JogadorValorant(0, nick, nome, idade, posicao, salario, s1, agente, kda);
                }
                case LOL -> {
                    String campeao = stat2Field.getText().trim();
                    double cs = Double.parseDouble(stat3Field.getText().trim().replace(",", "."));
                    novoJogador = new JogadorLOL(0, nick, nome, idade, posicao, salario, s1, campeao, cs);
                }
                default -> { return; }
            }

            jogadorService.cadastrarJogador(novoJogador);
            atualizarTabela();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique se os campos numéricos foram preenchidos corretamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void atualizarLabelsStats(Jogo jogo, JLabel l1, JLabel l2, JLabel l3) {
        switch (jogo) {
            case CS -> {
                l1.setText("Rating (ex: 1.15):");
                l2.setText("Abates/partida:");
                l3.setText("% Headshot (ex: 0.45):");
            }
            case VALORANT -> {
                l1.setText("ACS (ex: 245.0):");
                l2.setText("Agente principal:");
                l3.setText("KDA (ex: 1.5):");
            }
            case LOL -> {
                l1.setText("KDA (ex: 4.2):");
                l2.setText("Campeão principal:");
                l3.setText("CS/min (ex: 8.5):");
            }
        }
    }
}
