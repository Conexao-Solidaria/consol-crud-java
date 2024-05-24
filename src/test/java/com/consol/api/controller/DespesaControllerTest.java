package com.consol.api.controller;

import com.consol.api.entity.Despesa;
import com.consol.api.entity.Familia;
import com.consol.api.service.DespesaService;
import com.consol.api.utils.DespesaEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DespesaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DespesaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DespesaService despesaService;

    @Nested
    @DisplayName("GET /despesas")
    class Listar {

        @Test
        @DisplayName("Se houver despesas: " +
                "deve retornar 200 e listar todas as despesas")
        void deveRetornar200EListarTodasAsDespesas() throws Exception {

            Mockito.when(despesaService.listar()).thenReturn(List.of(
                    Despesa.builder()
                            .id(1)
                            .familia(new Familia())
                            .tipo("Aluguel")
                            .gasto(1000.0)
                            .build(),
                    Despesa.builder()
                            .id(2)
                            .familia(new Familia())
                            .tipo("Mercado")
                            .gasto(500.0)
                            .build()
            ));

            mockMvc.perform(MockMvcRequestBuilders.get(DespesaEnum.BASE_URL.PATH)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].tipo").value("Aluguel"))
                    .andExpect(jsonPath("$[0].gasto").value(1000.0))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].tipo").value("Mercado"))
                    .andExpect(jsonPath("$[1].gasto").value(500.0));

            Mockito.verify(despesaService, Mockito.times(1)).listar();
        }

        @Test
        @DisplayName("Se não houver despesas: " +
                "deve retornar 204 e uma lista vazia")
        void deveRetornar204EListaVazia() throws Exception {

            Mockito.when(despesaService.listar()).thenReturn(Collections.emptyList());

            mockMvc.perform(MockMvcRequestBuilders.get(DespesaEnum.BASE_URL.PATH)
                            .contentType("application/json"))
                    .andExpect(status().isNoContent())
                    .andExpect(jsonPath("$").isEmpty());

            Mockito.verify(despesaService, Mockito.times(1)).listar();
        }
    }

    @Nested
    @DisplayName("POST /despesas")
    class Salvar {

        @Test
        @DisplayName("Se os dados estiverem corretos: " +
                "deve retornar 201, a URI e salvar a despesa")
        void deveRetornar201ESalvarADespesa() throws Exception {
//            TODO
        }

        @Test
        @DisplayName("Se o id da familia for nulo: " +
                "deve retornar 400 e uma mensagem de erro")
        void deveRetornarBadRequestIdFamilia() throws Exception {
//            TODO
        }

        @Test
        @DisplayName("Se o tipo for nulo: " +
                "deve retornar 400 e uma mensagem de erro")
        void deveRetornarBadRequestTipo() throws Exception {
//            TODO
        }

        @Test
        @DisplayName("Se o gasto for nulo: " +
                "deve retornar 400 e uma mensagem de erro")
        void deveRetornarBadRequestGasto() throws Exception {
//            TODO
        }

        @Test
        @DisplayName("Se o gasto for menor que zero: " +
                "deve retornar 400 e uma mensagem de erro")
        void deveRetornarBadRequestGastoMenorQueZero() throws Exception {
//            TODO
        }
    }

    @Nested
    @DisplayName("GET /despesas/{id}")
    class BuscarPorId {

//            TODO
    }

    @Nested
    @DisplayName("GET /despesas/filtro")
    class Filtro {

//            TODO
    }

    @Nested
    @DisplayName("PATCH /despesas/{id}")
    class AlterarValor {

//            TODO
    }

    @Nested
    @DisplayName("DELETE /despesas/{id}")
    class Deletar {

//            TODO
    }

}
