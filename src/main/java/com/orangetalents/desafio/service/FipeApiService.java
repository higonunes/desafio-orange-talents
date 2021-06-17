package com.orangetalents.desafio.service;

import com.orangetalents.desafio.dto.veiculo.VeiculoFipeDTO;
import com.orangetalents.desafio.dto.veiculo.VeiculoModeloFipeDTO;
import com.orangetalents.desafio.dto.veiculo.VeiculoObjetoFipeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "tabelaFipe", url = "https://parallelum.com.br/fipe/api/v1")
public interface FipeApiService {

    @GetMapping("/{tipoVeiculo}/marcas")
    List<VeiculoObjetoFipeDTO> getListaMarcaPorVeiculo(@PathVariable("tipoVeiculo") String tipoVeiculo);

    @GetMapping("/{tipoVeiculo}/marcas/{idmarca}/modelos")
    VeiculoModeloFipeDTO getListaMarcaModelos(@PathVariable("tipoVeiculo") String tipoVeiculo, @PathVariable("idmarca") Long idmarca);

    @GetMapping("/{tipoVeiculo}/marcas/{idmarca}/modelos/{idmodelo}/anos")
    List<VeiculoObjetoFipeDTO> getListaModeloAnos(@PathVariable("tipoVeiculo") String tipoVeiculo, @PathVariable("idmarca") Long idmarca, @PathVariable("idmodelo") Long idmodelo);

    @GetMapping("/{tipoVeiculo}/marcas/{idmarca}/modelos/{idmodelo}/anos/{idano}")
    Object getModelo(@PathVariable("tipoVeiculo") String tipoVeiculo, @PathVariable("idmarca") Long idmarca, @PathVariable("idmodelo") Long idmodelo, @PathVariable("idano") String idano);

}
