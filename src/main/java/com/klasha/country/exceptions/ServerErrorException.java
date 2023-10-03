package com.klasha.country.exceptions;

public class ServerErrorException extends RuntimeException {

    public ServerErrorException(final String exception) {
        super(exception);
    }
}
