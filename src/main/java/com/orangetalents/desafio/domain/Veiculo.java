package com.orangetalents.desafio.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca, modelo;
    private Integer anoModelo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, Integer anoModelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return getId().equals(veiculo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
