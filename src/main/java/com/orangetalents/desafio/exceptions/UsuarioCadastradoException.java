package com.orangetalents.desafio.exceptions;

public class UsuarioCadastradoException extends RuntimeException {

    public UsuarioCadastradoException(String msg) {
        super(msg);
    }

    public UsuarioCadastradoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
