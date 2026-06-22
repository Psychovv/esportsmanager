package exception;

/**
 * Lançada quando um time não é encontrado no repositório.
 */
public class TimeNaoEncontradoException extends Exception {

    public TimeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public TimeNaoEncontradoException(int id) {
        super("Time com ID " + id + " não encontrado.");
    }
}
