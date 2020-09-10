package ar.agenda.controller.exceptions;

public class MissmatchException extends RuntimeException {

    public MissmatchException(String detail) {
        super("Mismatch exception" + " " + detail);
    }
}
