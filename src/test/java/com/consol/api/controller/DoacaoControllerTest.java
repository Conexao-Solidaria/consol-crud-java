//package com.consol.api.controller;
//
//import com.consol.api.entity.Doacao;
//import com.consol.api.entity.Titular;
//import com.consol.api.entity.Instituicao;
//import com.consol.api.service.DoacaoService;
//import com.consol.api.utils.BeneficioEnum;
//import com.consol.api.utils.DoacaoEnum;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(DoacaoControllerTest.class)
//@AutoConfigureMockMvc(addFilters = false)
//@DisplayName("Controller - Doacao")
//public class DoacaoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DoacaoService doacaoService;
//
//    @Nested
//    @DisplayName("GET /doacoes")
//    class listar {
//        // Se os dados estiverem corretos, 200 e listar todas as doacoes - OK
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "Deve retornar 200 e listar todas as doacoes")
//        void deveListarTodasAsDoacoes () throws Exception {
//
//            Mockito.when(doacaoService.listar()).thenReturn(List.of(
//                    Doacao.builder()
//                            .id(1)
//                            .peso(5.0)
//                            .descricao("Doação 1")
//                            .dataDoacao(LocalDate.parse("2024-05-04"))
//                            .instituicao(new Instituicao())
//                            .titular(new Titular())
//                            .build(),
//                    Doacao.builder()
//                            .id(2)
//                            .peso(10.0)
//                            .descricao("Doação 2")
//                            .dataDoacao(LocalDate.parse("2024-06-03"))
//                            .instituicao(new Instituicao())
//                            .titular(new Titular())
//                            .build()
//                    ));
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DoacaoEnum.BASE_URL.PATH)
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$[0].id").value(1))
//                    .andExpect(jsonPath("$[0].peso").value(5.0))
//                    .andExpect(jsonPath("$[0].descricao").value("Doação 1"))
//                    .andExpect(jsonPath("$[0].dataDoacao").value("2024-05-04"))
//
//                    .andExpect(jsonPath("$[1].id").value(2))
//                    .andExpect(jsonPath("$[1].peso").value(10.0))
//                    .andExpect(jsonPath("$[1].descricao").value("Doação 2"))
//                    .andExpect(jsonPath("$[1].dataDoacao").value(LocalDate.parse("2024-06-03")));
//
//            Mockito.verify(doacaoService, Mockito.times(1)).listar();
//        }
//
//        // Se não houver doacoes, 204 e lista vazia - OK
//        @Test
//        @DisplayName("Se não houver doacoes: 204 e lista vazia")
//        void deveRetornarListaVazia() throws Exception {
//            Mockito.when(doacaoService.listar()).thenReturn(List.of());
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DoacaoEnum.BASE_URL.PATH)
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent());
//
//            Mockito.verify(doacaoService, Mockito.times(1)).listar();
//        }
//    }
//
//    @Nested
//    @DisplayName("POST /doacoes")
//    class cadastrar{
//
//        // Se os dados estiverem corretos, 201, URI e salvar doação
//        @Test
//        @DisplayName("Se os dados estiverem corretos, 201, URI e doação cadastrada")
//        void deveCadastrarDoacao() throws Exception {
//
//            Doacao novaDoacao = Doacao.builder()
//                    .peso(5.0)
//                    .descricao("Doação 1")
//                    .dataDoacao(LocalDate.parse("2024-05-04"))
//                    .instituicao(new Instituicao())
//                    .titular(new Titular())
//                    .build();
//
//            Doacao doacaoCadastrada = Doacao.builder()
//                    .id(1)
//                    .peso(5.0)
//                    .descricao("Doação 1")
//                    .dataDoacao(LocalDate.parse("2024-05-04"))
//                    .instituicao(new Instituicao())
//                    .titular(new Titular())
//                    .build();
//
//            Mockito.when(doacaoService.salvar(Mockito.any(Doacao.class)))
//                    .thenReturn(doacaoCadastrada);
//
//            var content = """
//                    {
//                        "peso": 5.0,
//                        "descricao": "Doação 1",
//                        "dataDoacao": "2024-05-04",
//                        "titular": {},
//                        "instituicao": {}
//                    }""";
//
//            mockMvc.perform(MockMvcRequestBuilders.post(BeneficioEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isCreated())
//                    .andExpect(header().string("Location", "/doacoes/1"))
//                    .andExpect(jsonPath("$.id")
//                            .value(1))
//                    .andExpect(jsonPath("$.peso")
//                            .value(5.0))
//                    .andExpect(jsonPath("$.descricao")
//                            .value("Doação 1"))
//                    .andExpect(jsonPath("$.dataDoacao")
//                                    .value("2024-05-04"))
//                    .andExpect(jsonPath("$.titular")
//                            .value(new Titular()))
//                    .andExpect(jsonPath("$.instituicao")
//                            .value(new Instituicao())
//
//                    );
//            Mockito.verify(doacaoService, Mockito.times(1)).salvar(Mockito.any(Doacao.class));
//        }
//
//        // Se os dados não estiverem corretos, 400 e mensagem de erro
//        @Test
//        @DisplayName("Se não houver doações: 204 e lista vazia")
//        void deveRetornarListaVazia() throws Exception {
//            Mockito.when(doacaoService.listar()).thenReturn(Collections.emptyList());
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DoacaoEnum.BASE_URL.PATH)
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(0));
//
//        }
//
//        @Test
//        @DisplayName("Se o peso estiver nulo: 400 e mensagem de erro")
//        // Se o peso for nulo, 400 e mensagem de erro
//        void deveRetornarErroPesoNulo() throws Exception {
//
//            var content = """
//                    {
//                        "id": 1,
//                        "peso": null,
//                        "descricao": "Doação de roupas",
//                        "dataDoacao": "2024-05-04",
//                        "instituicao": ,
//                        "titular":
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/doacoes")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O peso é obrigatório"));
//        }
//
//        @Test
//        @DisplayName("Se a descrição estiver vazia: 400 e mensagem de erro")
//        // Se a descricao estiver vazia, 400 e mensagem de erro
//        void deveRetornarDescricaoVazia() throws Exception {
//
//            var content = """
//                    {
//                        "id": 1,
//                        "peso": 5.0,
//                        "descricao": "",
//                        "dataDoacao": "2024-05-04",
//                        "instituicao": ,
//                        "titular":
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/doacoes")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A descricao é obrigatória"));
//        }
//
//        @Test
//        @DisplayName("Se a data da doação estiver vazia: 400 e mensagem de erro")
//        // Se a dataDoacao estiver vazia, 400 e mensagem de erro
//        void deveRetornarErroDataDoacaoVazia() throws Exception {
//
//            var content = """
//                    {
//                        "id": 1,
//                        "peso": 5.0,
//                        "descricao": "Doação de roupas",
//                        "dataDoacao": "",
//                        "instituicao": ,
//                        "titular":
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/doacoes")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A data da doação é obrigatória"));
//        }
//
//        @Test
//        @DisplayName("Se o peso estiver inválido: 400 e mensagem de erro")
//        // Se o peso estiver inválido, 400 e mensagem de erro
//        void deveRetornarErroPesoInvalido() throws Exception {
//
//            var content = """
//                    {
//                        "id": 1,
//                        "peso": 5,
//                        "descricao": "Doação de roupas",
//                        "dataDoacao": "2024-05-04",
//                        "instituicao": ,
//                        "titular":
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/doacoes")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O peso está inválido"));
//        }
//
//        @Test
//        @DisplayName("Se a descrição estiver inválida: 400 e mensagem de erro")
//        // Se a descricao estiver inválida, 400 e mensagem de erro
//        void deveRetornarErroDescricaoInvalida() throws Exception {
//
//            var content = """
//                    {
//                        "id": 1,
//                        "peso": 5.0,
//                        "descricao": 5,
//                        "dataDoacao": "2024-05-04",
//                        "instituicao": ,
//                        "titular":
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/doacoes")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A descrição está inválida"));
//        }
//
//
//        @Test
//        @DisplayName("Se a data da doação estiver inválida: 400 e mensagem de erro")
//        // Se o dataDoacao estiver inválida, 400 e mensagem de erro
//        void deveRetornarErroDataDoacaoInvalida() throws Exception {
//
//            var content = """
//                    {
//                        "id": 1,
//                        "peso": 5.0,
//                        "descricao": "Doação de roupas",
//                        "dataDoacao": 5,
//                        "instituicao": ,
//                        "titular":
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/doacoes")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A data da doação está inválida"));
//        }
//
//        @Test
//        @DisplayName("Se a descrição estiver nula: 400 e mensagem de erro")
//        // Se a descricao estiver nula, 400 e mensagem de erro
//        void deveRetornarErroDescricaoNula() throws Exception {
//
//            var content = """
//                    {
//                        "id": 1,
//                        "peso": 5.0,
//                        "descricao": null,
//                        "dataDoacao": "2024-05-04",
//                        "instituicao": ,
//                        "titular":
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/doacoes")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A descrição está nula"));
//        }
//
//        @Test
//        @DisplayName("Se a data da doação estiver nula: 400 e mensagem de erro")
//        // Se a dataDoacao estiver nula, 400 e mensagem de erro
//        void deveRetornarErroDataDoacaoNula() throws Exception {
//
//            var content = """
//                    {
//                        "id": 1,
//                        "peso": 5.0,
//                        "descricao": "Doação de roupas",
//                        "dataDoacao": null,
//                        "instituicao": ,
//                        "titular":
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/doacoes")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A data da doação está nula"));
//        }
//    }
//
//    @Nested
//    @DisplayName("GET /doacoes/{id}")
//    class listarPorId {
//
//        @Test
//        @DisplayName("Se a doação existir: 200 e a doação")
//        void deveRetornarDoacao() throws Exception {
//
//            Doacao doacao = Doacao.builder()
//                    .id(1)
//                    .peso(5.0)
//                    .descricao("Doação 1")
//                    .dataDoacao(LocalDate.parse("2024-05-04"))
//                    .instituicao(new Instituicao())
//                    .titular(new Titular())
//                    .build();
//
//            Mockito.when(doacaoService.listarPorId(1))
//                    .thenReturn(doacao);
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DoacaoEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.peso").value(5.0))
//                    .andExpect(jsonPath("$.descricao").value("Doação 1"))
//                    .andExpect(jsonPath("$.dataDoacao").value("2024-05-04"))
//                    .andExpect(jsonPath("$.instituicao").value(new Instituicao()))
//                    .andExpect(jsonPath("$.titular").value(new Titular()));
//        }
//    }
//
//    @Nested
//    @DisplayName("GET /doacoes/filtro")
//    class listarPorData {
//
//        @Test
//        @DisplayName("Se houver doações nesta data: 200 e as doações")
//        void deveRetornarDoacoes() throws Exception {
//
//            LocalDate data = LocalDate.of(2024, 5, 4);
//            Mockito.when(doacaoService.listarPorData(data))
//                    .thenReturn(List.of(
//                            Doacao.builder()
//                                    .id(1)
//                                    .dataDoacao(data)
//                                    .build(),
//                            Doacao.builder()
//                                    .id(2)
//                                    .dataDoacao(data)
//                                    .build()));
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DoacaoEnum.POR_FILTRO.PATH)
//                            .param("dataDoacao", "Doação")
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(2))
//                    .andExpect(jsonPath("$[0].id").value(1))
//                    .andExpect(jsonPath("$[0].dataDoacao").value(data))
//                    .andExpect(jsonPath("$[1].id").value(2))
//                    .andExpect(jsonPath("$[1].dataDoacao").value(data));
//
//        }
//
//        @Test
//        @DisplayName("Se não houver doações nesta data: 204 e uma lista vazia")
//        void deveRetornarListaVazia() throws Exception {
//
//            LocalDate data = LocalDate.of(2024, 5, 4);
//            Mockito.when(doacaoService.listarPorData(data))
//                    .thenReturn(Collections.emptyList());
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DoacaoEnum.POR_FILTRO.PATH)
//                            .param("dataDoacao", "Doação")
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(0));
//        }
//    }
//
//    @Nested
//    @DisplayName("GET /doacoes/filtro")
//    class listarPorPeriodo {
//
//        @Test
//        @DisplayName("Se houver doações cadastradas nesse período: 200 e as doações")
//        void deveRetornarDoacoes() throws Exception {
//
//            LocalDate dataInicio = LocalDate.of(2023, 1, 1);
//            LocalDate dataFim = LocalDate.of(2024, 5, 4);
//
//            Mockito.when(doacaoService.listarPorPeriodo(dataInicio, dataFim))
//                    .thenReturn(List.of(
//                            Doacao.builder()
//                                    .id(1)
//                                    .dataDoacao(LocalDate.of(2023, 1, 1))
//                                    .build(),
//                            Doacao.builder()
//                                    .id(2)
//                                    .dataDoacao(LocalDate.of(2024, 5, 4))
//                                    .build()));
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/doacoes/filtro")
//                            .param("dataInicio", "2023-01-01")
//                            .param("dataFim", "2024-05-04")
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(2))
//                    .andExpect(jsonPath("$[0].id").value(1))
//                    .andExpect(jsonPath("$[0].data").value("2023-01-00"))
//                    .andExpect(jsonPath("$[1].id").value(2))
//                    .andExpect(jsonPath("$[1].data").value("2024-05-04"));
//        }
//
//        @Test
//        @DisplayName("Se não houver doações cadastradas nesse período: 204 e uma lista vazia")
//        void deveRetornarListaVazia() throws Exception {
//
//            LocalDate dataInicio = LocalDate.of(2023, 1, 1);
//            LocalDate dataFim = LocalDate.of(2024, 5, 4);
//
//            Mockito.when(doacaoService.listarPorPeriodo(dataInicio, dataFim))
//                    .thenReturn(Collections.emptyList());
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/doacoes/filtro")
//                            .param("dataInicio", "2023-01-01")
//                            .param("dataFim", "2024-05-04")
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(0));
//
//        }
//    }
//}
