package es.lareira.denodo.application.domain.model.exceptions;

public class NoPurchasesFoundException extends RuntimeException {
    public NoPurchasesFoundException() {
        super("No purchases  has been found for current parameters.");
    }

}
