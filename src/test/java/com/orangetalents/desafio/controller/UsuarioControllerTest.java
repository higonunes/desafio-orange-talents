package com.orangetalents.desafio.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void deveSerPossivelInserirUsuarioSemEmalOuCpfJaCadastrado() throws Exception {
        URI uri = new URI("/usuario");
        String requestBody = "{\n" +
                "    \"nome\": \"Higo\",\n" +
                "    \"senha\": \"12345\",\n" +
                "    \"email\":\"higo.sousa@gmail.com\",\n" +
                "    \"cpf\": \"61013762304\",\n" +
                "    \"dataNascimento\": \"01/04/1997\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .status()
                .isCreated());
    }

    @Test
    @Order(2)
    void naoDeveSerPossivelInserirUsuarioSemEmalOuCpfJaCadastrado() throws Exception {
        URI uri = new URI("/usuario");
        String requestBody = "{\n" +
                "    \"nome\": \"Higo\",\n" +
                "    \"senha\": \"12345\",\n" +
                "    \"email\":\"higo.sousa@gmail.com\",\n" +
                "    \"cpf\": \"61013762304\",\n" +
                "    \"dataNascimento\": \"01/04/1997\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .status()
                .isBadRequest());
    }

    @Test
    @Order(2)
    void naoDeveSerPossivelInserirUmCpfDeTamanhoDiferenteDeOnze() throws Exception {
        URI uri = new URI("/usuario");
        String requestBody = "{\n" +
                "    \"nome\": \"Higo\",\n" +
                "    \"senha\": \"12345\",\n" +
                "    \"email\":\"higo.sousa@gmail.com\",\n" +
                "    \"cpf\": \"6101362304\",\n" +
                "    \"dataNascimento\": \"01/04/1997\"\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .status()
                .isBadRequest()).andReturn();

        String msg = "O CPF deve ter 11 dígitos";
        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(msg));
    }
}