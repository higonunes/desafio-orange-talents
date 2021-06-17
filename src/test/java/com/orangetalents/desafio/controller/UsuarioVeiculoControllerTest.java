package com.orangetalents.desafio.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioVeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @BeforeAll
    void login() throws Exception {
        URI uri = new URI("/auth");
        String requestBody = "{\n" +
                "    \"email\": \"higo.sousaa@gmail.com\",\n" +
                "    \"senha\": \"12345\"\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers
                        .header()
                        .exists("Authorization")
        ).andExpect(
                MockMvcResultMatchers
                        .status()
                        .isOk()
        ).andReturn();

        token = result.getResponse().getHeader("Authorization");
    }

    @Test
    void deveSerPossívelInserirVeiculoNoCadastroDoUsuarioLogado() throws Exception {
        URI uri = new URI("/usuario/veiculo");
        String requestBody = "{\n" +
                "    \"marca\": \"VW - VolksWagen\",\n" +
                "    \"modelo\": \"AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut\",\n" +
                "    \"anoModelo\": 2001\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .header("Authorization", token)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .status()
                .isCreated());
    }

    @Test
    void naoDeveSerPossívelInserirVeiculoNoCadastroDoUsuarioNaoLogado() throws Exception {
        URI uri = new URI("/usuario/veiculo");
        String requestBody = "{\n" +
                "    \"marca\": \"VW - VolksWagen\",\n" +
                "    \"modelo\": \"AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut\",\n" +
                "    \"anoModelo\": 2001\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .status()
                .isForbidden());
    }

    @Test
    void deveSerPossívelInserirApenasAnosValidos() throws Exception {
        URI uri = new URI("/usuario/veiculo");
        String requestBody = "{\n" +
                "    \"marca\": \"VW - VolksWagen\",\n" +
                "    \"modelo\": \"AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut\",\n" +
                "    \"anoModelo\": 201\n" +
                "}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .header("Authorization", token)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .status()
                .isBadRequest()
        ).andReturn();

        String msg = "Deve conter quatro dígitos";
        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(msg));
    }

    @Test
    void deveTrazerApenasRodizioAtivo() throws Exception {
        URI uri = new URI("/usuario/veiculo");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .queryParam("rodizioAtivo", "true")
                .header("Authorization", token)
        ).andExpect(MockMvcResultMatchers
                .status()
                .isOk()
        ).andReturn();

        String msg = "Deve conter quatro dígitos";
        assertFalse(result.getResponse().getContentAsString().contains("\"rodizioAtivo\": false"));
    }
}