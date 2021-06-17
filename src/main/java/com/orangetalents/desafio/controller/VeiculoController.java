package com.orangetalents.desafio.controller;

import com.orangetalents.desafio.dto.veiculo.VeiculoFipeDTO;
import com.orangetalents.desafio.dto.veiculo.VeiculoModeloFipeDTO;
import com.orangetalents.desafio.dto.veiculo.VeiculoObjetoFipeDTO;
import com.orangetalents.desafio.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Cacheable("marcas")
    @GetMapping("/{tipoVeiculo}/marcas")
    public ResponseEntity<List<VeiculoObjetoFipeDTO>> getListaMarcas(@PathVariable String tipoVeiculo) {
        List<VeiculoObjetoFipeDTO> lista = veiculoService.listaMarcas(tipoVeiculo);
        return ResponseEntity.ok(lista);
    }

    @Cacheable("modelos")
    @GetMapping("/{tipoVeiculo}/marcas/{idmarca}/modelos")
    public ResponseEntity<VeiculoModeloFipeDTO> getListaModelo(@PathVariable String tipoVeiculo, @PathVariable Long idmarca) {
        VeiculoModeloFipeDTO modelos = veiculoService.listaMarcaModelos(tipoVeiculo, idmarca);
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/{tipoVeiculo}/marcas/{idmarca}/modelos/{idmodelo}/anos")
    public ResponseEntity<List<VeiculoObjetoFipeDTO>> getListaAnos(@PathVariable String tipoVeiculo, @PathVariable Long idmarca, @PathVariable Long idmodelo) {
        List<VeiculoObjetoFipeDTO> lista = veiculoService.listaModeloAnos(tipoVeiculo, idmarca, idmodelo);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{tipoVeiculo}/marcas/{idmarca}/modelos/{idmodelo}/anos/{idano}")
    public ResponseEntity<VeiculoFipeDTO> getModelo(@PathVariable String tipoVeiculo, @PathVariable Long idmarca, @PathVariable Long idmodelo, @PathVariable String idano) {
        VeiculoFipeDTO veiculo = veiculoService.getModelo(tipoVeiculo, idmarca, idmodelo, idano);
        return ResponseEntity.ok(veiculo);
    }
}
