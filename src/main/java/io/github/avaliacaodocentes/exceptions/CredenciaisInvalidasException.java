package io.github.avaliacaodocentes.exceptions;

public class CredenciaisInvalidasException extends Exception {

    public CredenciaisInvalidasException() {
        super("Credencias Invalidas");
    }

    public CredenciaisInvalidasException(String message) {
        super(message);
    }
}
