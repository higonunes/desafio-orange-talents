package com.orangetalents.desafio.exceptions;

public class NaoEncontradoException extends RuntimeException {

    public NaoEncontradoException(String msg) {
        super(msg);
    }

    public NaoEncontradoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
