package ar.agenda.controller.exceptions;

@SuppressWarnings("serial")
public class ForbiddenException extends RuntimeException {

    private static final String msg = "Forbidden exception";

    public ForbiddenException(String detail) {
        super(msg + " " + detail);
    }
}
