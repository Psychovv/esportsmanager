package view;

import model.enums.Jogo;
import model.jogador.Jogador;
import model.time.Time;
import repository.TimeRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de cadastro e visualização de times.
 */
public class TelaTime extends JFrame {

    private final TimeRepository timeRepo;
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public TelaTime(TimeRepository timeRepo) {
        this.timeRepo = timeRepo;

        setTitle("Gerenciar Times");
        setSize(750, 450);
        setLocationRelativeTo(null);

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Tag", "Jogo", "País", "Fundação", "Jogadores", "Pontuação"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabela = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabela);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnCadastrar = new JButton("Cadastrar Time");
        JButton btnVerElenco = new JButton("Ver Elenco do Selecionado");
        JButton btnAtualizar = new JButton("Atualizar Lista");

        btnCadastrar.addActionListener(e -> abrirFormularioCadastro());
        btnVerElenco.addActionListener(e -> verElencoSelecionado());
        btnAtualizar.addActionListener(e -> atualizarTabela());

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnVerElenco);
        painelBotoes.add(btnAtualizar);

        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Time t : timeRepo.listarTimes()) {
            modeloTabela.addRow(new Object[]{
                    t.getId(), t.getNome(), t.getTag(), t.getJogo(), t.getPais(), t.getAnoFundacao(),
                    t.getJogadores().size() + "/5", String.format("%.2f", t.getPontuacao())
            });
        }
    }

    private void abrirFormularioCadastro() {
        JTextField nomeField = new JTextField();
        JTextField tagField = new JTextField();
        JComboBox<Jogo> jogoBox = new JComboBox<>(Jogo.values());
        JTextField paisField = new JTextField();
        JTextField anoField = new JTextField();

        JPanel painel = new JPanel(new GridLayout(0, 2, 5, 5));
        painel.add(new JLabel("Nome:"));            painel.add(nomeField);
        painel.add(new JLabel("Tag:"));              painel.add(tagField);
        painel.add(new JLabel("Jogo:"));             painel.add(jogoBox);
        painel.add(new JLabel("País:"));             painel.add(paisField);
        painel.add(new JLabel("Ano de fundação:"));  painel.add(anoField);

        int resultado = JOptionPane.showConfirmDialog(this, painel, "Cadastrar Time",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado != JOptionPane.OK_OPTION) return;

        try {
            String nome = nomeField.getText().trim();
            String tag = tagField.getText().trim().toUpperCase();
            Jogo jogo = (Jogo) jogoBox.getSelectedItem();
            String pais = paisField.getText().trim();
            int ano = Integer.parseInt(anoField.getText().trim());

            Time novoTime = new Time(0, nome, tag, jogo, pais, ano);
            timeRepo.adicionarTime(novoTime);
            atualizarTabela();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique se o ano foi preenchido corretamente.");
        }
    }

    private void verElencoSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um time na tabela primeiro.");
            return;
        }
        int id = (int) modeloTabela.getValueAt(linha, 0);

        timeRepo.buscarPorId(id).ifPresent(time -> {
            StringBuilder sb = new StringBuilder();
            sb.append(time.getNome()).append(" [").append(time.getTag()).append("]\n\n");
            if (time.getJogadores().isEmpty()) {
                sb.append("Nenhum jogador escalado.");
            } else {
                for (Jogador j : time.getJogadores()) {
                    sb.append("- ").append(j.getNick()).append(" (").append(j.getPosicao()).append(")\n");
                }
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Elenco", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
