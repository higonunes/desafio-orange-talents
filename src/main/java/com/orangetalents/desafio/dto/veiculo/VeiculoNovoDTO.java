package com.orangetalents.desafio.dto.veiculo;

import com.orangetalents.desafio.domain.Veiculo;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VeiculoNovoDTO {

    @NotEmpty
    private String marca, modelo;

    @NotNull
    @Range(min = 1900, max = 9999, message = "Deve conter quatro dígitos")
    private Integer anoModelo;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Veiculo toVeiculo() {
        return new Veiculo(marca, modelo, anoModelo);
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
}
