package ar.agenda.controller.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -6113168792898073825L;

    private static final String BAD_REQUEST = "Bad request";

    public BadRequestException(String detail) {
        super(BAD_REQUEST + " : " + detail);
    }
}
