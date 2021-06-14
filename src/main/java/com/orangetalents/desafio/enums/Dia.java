package com.orangetalents.desafio.enums;

public enum Dia {
    SEGUNDA(1, "segunda-feira"),
    TERCA(2, "terça-feira"),
    QUARTA(2, "quarta-feira"),
    QUINTA(2, "quinta-feira"),
    SEXTA(2, "sexta-feira"),
    ;

    private final int id;
    private final String descricao;

    Dia(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Dia toEnum(Integer id) {
        if (id == null) return null;

        for (Dia x : Dia.values()) {
            if (id.equals(x.id)) return x;
        }

        throw new IllegalArgumentException("ID inválido");
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
