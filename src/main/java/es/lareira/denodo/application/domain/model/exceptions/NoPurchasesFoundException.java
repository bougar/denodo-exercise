package es.lareira.denodo.application.domain.model.exceptions;

public class NoPurchasesFoundException extends RuntimeException {
    public NoPurchasesFoundException() {
        super("No purchases found. Please try again with different parameters.");
    }

}
