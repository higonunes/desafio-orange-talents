package com.orangetalents.desafio.dto.veiculo;

import com.orangetalents.desafio.domain.Veiculo;
import com.orangetalents.desafio.service.RodizioService;

public class VeiculoUsuarioDTO {

    private String marca, modelo;
    private Integer anoModelo;

    public VeiculoUsuarioDTO(Veiculo veiculo) {
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.anoModelo = veiculo.getAnoModelo();
    }

    public String getDiaRodizio() {
        return RodizioService.diaDoRodizio(this.anoModelo).getDescricao();
    }

    public boolean isRodizioAtivo() {
        return RodizioService.isDiaRodizio(this.anoModelo);
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
}
