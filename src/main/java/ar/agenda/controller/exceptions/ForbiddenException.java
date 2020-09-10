package ar.agenda.controller.exceptions;

@SuppressWarnings("serial")
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String detail) {
        super("Forbidden exception" + " " + detail);
    }
}
