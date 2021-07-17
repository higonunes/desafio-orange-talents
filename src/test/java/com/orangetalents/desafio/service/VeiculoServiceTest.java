package com.orangetalents.desafio.service;

import com.orangetalents.desafio.domain.Veiculo;
import com.orangetalents.desafio.dto.veiculo.VeiculoUsuarioDTO;
import com.orangetalents.desafio.repository.VeiculoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    @Test
    void deveRetornarVeiculoUsuarioDTO() {
        Veiculo veiculo = new Veiculo();
        veiculo.setAnoModelo(2001);
        veiculo.setMarca("Ford");
        veiculo.setModelo("Ka");
        veiculo.setId(1L);

        VeiculoUsuarioDTO veiculoUsuarioDTO = new VeiculoUsuarioDTO(veiculo);
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        VeiculoUsuarioDTO veiculoUsuarioDTOService = veiculoService.getVeiculo(1L);

        Assertions.assertEquals(veiculoUsuarioDTO.getAnoModelo(),veiculoUsuarioDTOService.getAnoModelo());
        Assertions.assertEquals(veiculoUsuarioDTO.getMarca(),veiculoUsuarioDTOService.getMarca());
    }

}
