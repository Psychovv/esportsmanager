package exception;

/**
 * Lançada quando um jogador não é encontrado no repositório.
 */
public class JogadorNaoEncontradoException extends Exception {

    public JogadorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public JogadorNaoEncontradoException(int id) {
        super("Jogador com ID " + id + " não encontrado.");
    }

    public JogadorNaoEncontradoException(String campo, String valor) {
        super("Jogador com " + campo + " '" + valor + "' não encontrado.");
    }
}
