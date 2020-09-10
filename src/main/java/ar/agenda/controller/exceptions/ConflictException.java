package ar.agenda.controller.exceptions;

public class ConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConflictException(String detail) {
        super("Conflict exception: " + detail);
    }
}
