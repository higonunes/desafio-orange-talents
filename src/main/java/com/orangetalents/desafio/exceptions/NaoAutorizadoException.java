package com.orangetalents.desafio.exceptions;

public class NaoAutorizadoException extends RuntimeException {

    public NaoAutorizadoException(String msg) {
        super(msg);
    }

    public NaoAutorizadoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
