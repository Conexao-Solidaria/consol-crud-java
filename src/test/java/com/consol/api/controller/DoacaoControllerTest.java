package com.consol.api.controller;

import com.consol.api.entity.Doacao;
import com.consol.api.entity.Donatario;
import com.consol.api.entity.Instituicao;
import com.consol.api.service.DoacaoService;
import com.consol.api.utils.DoacaoEnum;
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

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DoacaoControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Controller - Doações")
public class DoacaoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private DoacaoService doacaoService;
    
    @Nested
    @DisplayName("GET /doacoes")
    class listar {
        // Se os dados estiverem corretos, 200 e listar todas as doacoes - OK
        // Se não houver doacoes, 204 e lista vazia - OK
        @Test
        @DisplayName("Se os dados estiverem corretos: " +
                "Deve retornar 200 e listar todas as doacoes")
        void deveListarTodasAsDoacoes () throws Exception {

            Mockito.when(doacaoService.listar()).thenReturn(List.of(
                    Doacao.builder()
                            .id(1)
                            .peso(5.0)
                            .descricao("Doação 1")
                            .dataDoacao(LocalDate.parse("2024-05-04"))
                            .instituicao(new Instituicao())
                            .donatario(new Donatario())
                            .build(),
                    Doacao.builder()
                            .id(2)
                            .peso(10.0)
                            .descricao("Doação 2")
                            .dataDoacao(LocalDate.parse("2024-06-03"))
                            .instituicao(new Instituicao())
                            .donatario(new Donatario())
                            .build()
                    ));

            mockMvc.perform(MockMvcRequestBuilders.get(DoacaoEnum.BASE_URL.PATH)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].peso").value(5.0))
                    .andExpect(jsonPath("$[0].descricao").value("Doação 1"))
                    .andExpect(jsonPath("$[0].dataDoacao").value("2024-05-04"))

                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].peso").value(10.0))
                    .andExpect(jsonPath("$[1].descricao").value("Doação 2"))
                    .andExpect(jsonPath("$[1].dataDoacao").value(LocalDate.parse("2024-06-03")));

            Mockito.verify(doacaoService, Mockito.times(1)).listar();
        }

        @Test
        @DisplayName("Se não houver doacoes: 204 e lista vazia")
        void deveRetornarListaVazia() throws Exception {
            Mockito.when(doacaoService.listar()).thenReturn(List.of());

            mockMvc.perform(MockMvcRequestBuilders.get(DoacaoEnum.BASE_URL.PATH)
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());

            Mockito.verify(doacaoService, Mockito.times(1)).listar();
        }
    }
    
    @Nested
    @DisplayName("POST /doacoes")
    class cadastrar{


        
    }
    
}
