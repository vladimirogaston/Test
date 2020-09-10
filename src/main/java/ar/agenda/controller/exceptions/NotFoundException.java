package ar.agenda.controller.exceptions;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

    private static final String msg = "Not found exception";

    public NotFoundException(String detail) {
        super(msg + " " + detail);
    }
}
