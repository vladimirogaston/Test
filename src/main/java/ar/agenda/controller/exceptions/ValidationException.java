package ar.agenda.controller.exceptions;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String detail) {
        super(detail);
    }
}
