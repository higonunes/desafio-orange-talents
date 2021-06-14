package com.orangetalents.desafio.exceptions.handler;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao {

    public static final long serialVersionUID = 1L;

    private final List<CampoMensagem> list = new ArrayList<>();

    public ErroValidacao(Long timeStamp, String error, String message, String path) {
        super(timeStamp, error, message, path);
    }

    public List<CampoMensagem> getErros() {
        return list;
    }

    public void addErro(String campoMensagem, String messagem) {
        list.add(new CampoMensagem(campoMensagem, messagem));
    }
}
