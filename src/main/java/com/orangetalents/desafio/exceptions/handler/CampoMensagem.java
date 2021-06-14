package com.orangetalents.desafio.exceptions.handler;

public class CampoMensagem {

    private String nomeCampo;
    private String messagem;

    public CampoMensagem() {
    }

    public CampoMensagem(String nomeCampo, String messagem) {
        super();
        this.nomeCampo = nomeCampo;
        this.messagem = messagem;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}
