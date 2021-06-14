package com.orangetalents.desafio.dto.veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoModeloFipeDTO {

    private List<VeiculoObjetoFipeDTO> anos = new ArrayList<>();
    private List<VeiculoObjetoFipeDTO> modelos = new ArrayList<>();

    public List<VeiculoObjetoFipeDTO> getAnos() {
        return anos;
    }

    public void setAnos(List<VeiculoObjetoFipeDTO> anos) {
        this.anos = anos;
    }

    public List<VeiculoObjetoFipeDTO> getModelos() {
        return modelos;
    }

    public void setModelos(List<VeiculoObjetoFipeDTO> modelos) {
        this.modelos = modelos;
    }
}
