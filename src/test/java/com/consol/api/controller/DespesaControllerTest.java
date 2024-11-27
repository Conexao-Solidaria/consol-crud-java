//package com.consol.api.controller;
//
//import com.consol.api.dto.despesa.DespesaConsultaDto;
//import com.consol.api.entity.Despesa;
//import com.consol.api.entity.Familia;
//import com.consol.api.service.DespesaService;
//import com.consol.api.utils.DespesaEnum;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(DespesaController.class)
//@AutoConfigureMockMvc(addFilters = false)
//public class DespesaControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DespesaService despesaService;
//
//    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//
//    @Nested
//    @DisplayName("GET /despesas")
//    class Listar {
//
//        @Test
//        @DisplayName("Se houver despesas: " +
//                "deve retornar 200 e listar todas as despesas")
//        void deveRetornar200EListarTodasAsDespesas() throws Exception {
//
//            Mockito.when(despesaService.listar()).thenReturn(List.of(
//                    Despesa.builder()
//                            .id(1)
//                            .familia(new Familia())
//                            .tipo("Aluguel")
//                            .gasto(1000.0)
//                            .build(),
//                    Despesa.builder()
//                            .id(2)
//                            .familia(new Familia())
//                            .tipo("Mercado")
//                            .gasto(500.0)
//                            .build()
//            ));
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DespesaEnum.BASE_URL.PATH)
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$[0].id").value(1))
//                    .andExpect(jsonPath("$[0].tipo").value("Aluguel"))
//                    .andExpect(jsonPath("$[0].gasto").value(1000.0))
//                    .andExpect(jsonPath("$[1].id").value(2))
//                    .andExpect(jsonPath("$[1].tipo").value("Mercado"))
//                    .andExpect(jsonPath("$[1].gasto").value(500.0));
//
//            Mockito.verify(despesaService, Mockito.times(1)).listar();
//        }
//
//        @Test
//        @DisplayName("Se não houver despesas: " +
//                "deve retornar 204 e uma lista vazia")
//        void deveRetornar204EListaVazia() throws Exception {
//
//            Mockito.when(despesaService.listar()).thenReturn(Collections.emptyList());
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DespesaEnum.BASE_URL.PATH)
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent())
//                    .andExpect(jsonPath("$").isEmpty());
//
//            Mockito.verify(despesaService, Mockito.times(1)).listar();
//        }
//    }
//
//    @Nested
//    @DisplayName("POST /despesas")
//    class Salvar {
//
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "deve retornar 201, a URI e salvar a despesa")
//        void deveRetornar201ESalvarADespesa() throws Exception {
//            Familia familia = new Familia();
//
//            familia.setId(1);
//            familia.setNome("Familia 1");
//            familia.setCep("00000000");
//            familia.setRenda(1.0);
//            familia.setNumeroCasa(1);
//
//            Despesa despesa = Despesa.builder()
//                    .id(1)
//                    .familia(familia)
//                    .tipo("teste")
//                    .gasto(1.0)
//                    .build();
//
//            Mockito.when(despesaService.salvar(Mockito.any(Despesa.class)))
//                    .thenReturn(despesa);
//
//            var content = """
//                    {
//                        "tipo": "teste",
//                        "gasto": 1.0,
//                        "familia_id": 1,
//                    }
//                    """;
//
//
//            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post
//                                    (DespesaEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isCreated())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.tipo").value("teste"))
//                    .andExpect(jsonPath("$.gasto").value("1.0"))
//                    .andExpect(jsonPath("$.familia.id").value(familia.getId()))
//                    .andExpect(jsonPath("$.familia.cep").value(familia.getCep()))
//                    .andExpect(jsonPath("$.familia.nome").value(familia.getNome()))
//                    .andExpect(jsonPath("$.familia.renda").value(familia.getRenda()))
//                    .andExpect(jsonPath("$.familia.numeroCasa").value(familia.getNumeroCasa()))
//                    .andReturn();
//
//            assertTrue(result.getResponse().getHeader("Location")
//                    .contains(DespesaEnum.BASE_URL.PATH + "/1"));
//        }
//
//        @Test
//        @DisplayName("Se o id da familia for nulo: " +
//                "deve retornar 400")
//        void deveRetornarBadRequestIdFamilia() throws Exception {
//            var content = """
//                    {
//                        "tipo": "teste",
//                        "gasto": 1.0,
//                        "familia_id": null,
//                    }
//                    """;
//
//
//            mockMvc.perform(MockMvcRequestBuilders.post(DespesaEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o tipo for nulo: " +
//                "deve retornar 400")
//        void deveRetornarBadRequestTipo() throws Exception {
//            var content = """
//                    {
//                        "tipo": "",
//                        "gasto": 1.0,
//                        "familia_id": 1,
//                    }
//                    """;
//
//
//            mockMvc.perform(MockMvcRequestBuilders.post(DespesaEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o gasto for nulo: " +
//                "deve retornar 400")
//        void deveRetornarBadRequestGasto() throws Exception {
//            var content = """
//                    {
//                        "tipo": "teste",
//                        "gasto": null,
//                        "familia_id": 1,
//                    }
//                    """;
//
//
//            mockMvc.perform(MockMvcRequestBuilders.post(DespesaEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o gasto for menor que zero: " +
//                "deve retornar 400")
//        void deveRetornarBadRequestGastoMenorQueZero() throws Exception {
//            var content = """
//                    {
//                        "tipo": "teste",
//                        "gasto": -1.0,
//                        "familia_id": 1,
//                    }
//                    """;
//
//
//            mockMvc.perform(MockMvcRequestBuilders.post(DespesaEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest());
//        }
//    }
//
//    @Nested
//    @DisplayName("GET /despesas/{id}")
//    class BuscarPorId {
//
//        @Test
//        @DisplayName("Se buscar a despesa: " +
//                "Deve retornar 200 e mostrar a despesa")
//        void deveMostrarADespesa() throws Exception{
//            Familia familia = new Familia();
//
//            familia.setId(1);
//            familia.setNome("Familia 1");
//            familia.setCep("00000000");
//            familia.setRenda(1.0);
//            familia.setNumeroCasa(1);
//
//            Despesa despesa = Despesa.builder()
//                    .id(1)
//                    .familia(familia)
//                    .tipo("teste")
//                    .gasto(1.0)
//                    .build();
//
//
//            Mockito.when(despesaService.buscarPorId(1)).thenReturn(despesa);
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DespesaEnum.BASE_URL.PATH)
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.tipo").value("teste"))
//                    .andExpect(jsonPath("$.gasto").value("1.0"))
//                    .andExpect(jsonPath("$.familia.id").value(familia.getId()))
//                    .andExpect(jsonPath("$.familia.cep").value(familia.getCep()))
//                    .andExpect(jsonPath("$.familia.nome").value(familia.getNome()))
//                    .andExpect(jsonPath("$.familia.renda").value(familia.getRenda()))
//                    .andExpect(jsonPath("$.familia.numeroCasa").value(familia.getNumeroCasa()));
//        }
//
//        @Test
//        @DisplayName("Se buscar despesa e não encontra nada" +
//                "Deve retornar 404 e uma mensagem de erro")
//        void retornar404SeNaoTiverNada() throws Exception{
//            Mockito.when(despesaService.buscarPorId(1)).thenReturn(null);
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DespesaEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isNotFound())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("Despesa não encontrada"));
//
//        }
//    }
//
//    @Nested
//    @DisplayName("PATCH /despesas/{id}")
//    class AlterarValor {
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "Deve retornar 200 e atualizar a despesa")
//        void deveAtualizarADespesa() throws Exception {
//
//            Despesa despesa = Despesa.builder()
//                    .id(1)
//                    .familia(null)
//                    .tipo("teste")
//                    .gasto(1.0)
//                    .build();
//
//            Mockito.when(despesaService.atualizarDespesa(1, new Despesa()))
//                    .thenReturn(despesa);
//
//            var content = """
//                        {
//                        "tipo": "teste_TESTE",
//                        "gasto": 1.0,
//                        "familia_id": 1,
//                        }
//                        """;
//
//            mockMvc.perform(MockMvcRequestBuilders.put(DespesaEnum.POR_ID.PATH, 1)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.tipo").value("teste"))
//                    .andExpect(jsonPath("$.gasto").value("1.0"));
//        }
//
//        @Test
//        @DisplayName("Se o id nao existir: " +
//                "Deve retornar 404 e uma mensagem de erro")
//        void deveRetornarErroIdNaoEncontrado() throws Exception {
//
//            Mockito.when(despesaService.atualizarDespesa(1, new Despesa()))
//                    .thenReturn(null);
//
//            var content = """
//                        {
//                        "tipo": "teste_TESTE",
//                        "gasto": 1.0,
//                        "familia_id": 1,
//                        }
//                        """;
//
//            mockMvc.perform(MockMvcRequestBuilders.put(DespesaEnum.POR_ID.PATH, 1)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isNotFound())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("Despesa não encontrada"));
//        }
//    }
//
//    @Nested
//    @DisplayName("DELETE /despesas/{id}")
//    class Deletar {
//
//        @Test
//        @DisplayName("Se a despensa existir: " +
//                "Deve retornar 204 e deletar a despensa")
//        void deveDeletarDespesa() throws Exception {
//
//            mockMvc.perform(MockMvcRequestBuilders.delete(DespesaEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent());
//        }
//
//        @Test
//        @DisplayName("Se a despesa não existir: " +
//                "Deve retornar 404 e uma mensagem de erro")
//        void deveRetornarErroDespesaNaoEncontrado() throws Exception {
//
//            mockMvc.perform(MockMvcRequestBuilders.delete(DespesaEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isNotFound())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("Despesa não encontrada"));
//        }
//    }
//
//}
