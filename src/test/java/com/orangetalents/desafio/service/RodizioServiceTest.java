package com.orangetalents.desafio.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RodizioServiceTest {

    @Test
    void deveRetornarDiaSemanaExtensoDeAcordoAno() {
        String dia = RodizioService.nomeDiaRodizio(2000);
        Assertions.assertEquals(dia, "Segunda-feira");
    }



}
