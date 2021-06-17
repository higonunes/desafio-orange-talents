package com.orangetalents.desafio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class AutenticacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornar401CasoUsuarioNaoEstejaCadastrado() throws Exception {
        URI uri = new URI("/auth");
        String requestBody = "{\n" +
                "    \"email\": \"higo.sousa@gmail.com\",\n" +
                "    \"senha\": \"12345\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .status()
                .isUnauthorized());
    }

    @Test
    void deveRetornar200CasoUsuarioEstejaCadastrado() throws Exception {
        URI uri = new URI("/auth");
        String requestBody = "{\n" +
                "    \"email\": \"higo.sousaa@gmail.com\",\n" +
                "    \"senha\": \"12345\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .status()
                .is(200));
    }

    @Test
    void deveRetornarTokenCasoUsuarioEstejaCadastrado() throws Exception {
        URI uri = new URI("/auth");
        String requestBody = "{\n" +
                "    \"email\": \"higo.sousaa@gmail.com\",\n" +
                "    \"senha\": \"12345\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers
                .header()
                .exists("Authorization"));

    }
}