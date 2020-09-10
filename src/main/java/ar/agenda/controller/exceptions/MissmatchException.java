package ar.agenda.controller.exceptions;

@SuppressWarnings("serial")
public class MissmatchException extends RuntimeException {

    private static final String msg = "Missmatch exception";

    public MissmatchException(String detail) {
        super(msg + " " + detail);
    }
}
