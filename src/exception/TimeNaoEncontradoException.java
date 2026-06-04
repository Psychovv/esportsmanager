package exception;

/**
 * Lançada quando um time não é encontrado no repositório.
 * PESSOA 3 (Sprint 2) — integrar nos services.
 */
public class TimeNaoEncontradoException extends Exception {

    public TimeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public TimeNaoEncontradoException(int id) {
        super("Time com ID " + id + " não encontrado.");
    }
}
