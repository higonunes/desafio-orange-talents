package com.orangetalents.desafio.exceptions.handler;

public class ErroPadrao {

    private Long timeStamp;
    private String erro;
    private String messagem;
    private String caminho;

    public ErroPadrao(Long timeStamp, String erro, String messagem, String caminho) {
        this.timeStamp = timeStamp;
        this.erro = erro;
        this.messagem = messagem;
        this.caminho = caminho;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
