package com.orangetalents.desafio.controller;

import com.orangetalents.desafio.dto.veiculo.VeiculoNovoDTO;
import com.orangetalents.desafio.dto.veiculo.VeiculoUsuarioDTO;
import com.orangetalents.desafio.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario/veiculo")
public class UsuarioVeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Void> inserirVeiculo(@RequestBody @Valid VeiculoNovoDTO veiculoNovoDTO) {
        veiculoService.insert(veiculoNovoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<VeiculoUsuarioDTO>> listaVeiculos(@PageableDefault Pageable pageable,
                                                                 @RequestParam(value = "rodizioAtivo",
                                                                               defaultValue = "false") Boolean rodizioAtivo) {
        if (rodizioAtivo) {
            return ResponseEntity.ok(veiculoService.listaVeiculoRodizioAtivo(pageable));
        } else {
            return ResponseEntity.ok(veiculoService.listaVeiculoUsuario(pageable));
        }
    }

}
