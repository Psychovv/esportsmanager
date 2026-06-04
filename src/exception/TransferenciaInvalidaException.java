package exception;

/**
 * Lançada quando uma transferência não pode ser realizada.
 * PESSOA 3 (Sprint 2) — integrar no TransferenciaService.
 */
public class TransferenciaInvalidaException extends Exception {

    public TransferenciaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
