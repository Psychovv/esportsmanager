package exception;

/**
 * Lançada quando uma transferência não pode ser realizada.
 */
public class TransferenciaInvalidaException extends Exception {

    public TransferenciaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
