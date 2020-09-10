package ar.agenda.controller.exceptions;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

    public NotFoundException(String detail) {
        super("Not found exception" + " " + detail);
    }
}
