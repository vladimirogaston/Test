package ar.agenda.controller.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -6113168792898073825L;

    public BadRequestException(String detail) {
        super("Bad request" + " : " + detail);
    }
}
