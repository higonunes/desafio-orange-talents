package com.orangetalents.desafio.repository;

import com.orangetalents.desafio.domain.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class VeiculoRepositoryTest {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Test
    void deveTrazerApenasAnosComFinalPassadoNoArray() {
        List<Veiculo> listaVeiculos = veiculoRepository.findByRodizioVeiculo(1L, Arrays.asList("1"), null).getContent();
        String finalNumero = listaVeiculos.get(0).getAnoModelo().toString().substring(3);
        Assertions.assertTrue(listaVeiculos.stream().anyMatch(x -> x.getAnoModelo().toString().substring(3) != "1"));

        listaVeiculos = veiculoRepository.findByRodizioVeiculo(1L, Arrays.asList("5"), null).getContent();
        Assertions.assertTrue(listaVeiculos.stream().anyMatch(x -> x.getAnoModelo().toString().substring(3) != "5"));

        listaVeiculos = veiculoRepository.findByRodizioVeiculo(1L, Arrays.asList("7"), null).getContent();
        Assertions.assertTrue(listaVeiculos.stream().anyMatch(x -> x.getAnoModelo().toString().substring(3) != "7"));

        listaVeiculos = veiculoRepository.findByRodizioVeiculo(1L, Arrays.asList("9"), null).getContent();
        Assertions.assertTrue(listaVeiculos.stream().anyMatch(x -> x.getAnoModelo().toString().substring(3) != "9"));
    }
}