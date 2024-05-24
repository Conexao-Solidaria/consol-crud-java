package com.consol.api.controller;

import com.consol.api.entity.Donatario;
import com.consol.api.service.DonatarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DonatarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class DonatarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DonatarioService donatarioService;

    @Nested
    @DisplayName("GET /donatarios")
    class listar {

        @Test
        @DisplayName("Se os dados estiverem corretos: " +
                "Deve retornar 200 e listar todos os donatários")
        void deveListarTodosOsDonatarios() throws Exception {

            Mockito.when(donatarioService.listar()).thenReturn(
                    List.of(
                            new Donatario(),
                            new Donatario()));

            mockMvc.perform(MockMvcRequestBuilders.get("/donatarios")
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(2));
        }

        @Test
        @DisplayName("Se não houver donatários: " +
                "Deve retornar 204 e uma lista vazia")
        void deveRetornarListaVazia() throws Exception {

            Mockito.when(donatarioService.listar()).thenReturn(Collections.emptyList());

            mockMvc.perform(MockMvcRequestBuilders.get("/donatarios")
                            .contentType("application/json"))
                    .andExpect(status().isNoContent())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }
    }
}