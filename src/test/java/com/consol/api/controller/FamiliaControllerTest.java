//package com.consol.api.controller;
//
//import com.consol.api.entity.Familia;
//import com.consol.api.service.FamiliaService;
//import com.consol.api.utils.FamiliaEnum;
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
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(FamiliaController.class)
//@AutoConfigureMockMvc(addFilters = false)
//class FamiliaControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private FamiliaService familiaService;
//
//    @Nested
//    @DisplayName("GET /familias")
//    class Listar {
//
//        @Test
//        @DisplayName("Se houver donatarios: " +
//                "Deve retornar 200 e listar todos os donatários")
//        void deveListarTodosOsFamilias() throws Exception {
//
//            Mockito.when(familiaService.listar()).thenReturn(
//                    List.of(
//                            new Familia(),
//                            new Familia()));
//
//            mockMvc.perform(MockMvcRequestBuilders.get(FamiliaEnum.BASE_URL.PATH)
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(2));
//
//            Mockito.verify(familiaService, Mockito.times(1)).listar();
//        }
//
//        @Test
//        @DisplayName("Se não houver donatários: " +
//                "Deve retornar 204 e uma lista vazia")
//        void deveRetornarListaVazia() throws Exception {
//
//            Mockito.when(familiaService.listar()).thenReturn(Collections.emptyList());
//
//            mockMvc.perform(MockMvcRequestBuilders.get(FamiliaEnum.BASE_URL.PATH)
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent());
//
//            Mockito.verify(familiaService, Mockito.times(1)).listar();
//        }
//    }
//
//    @Nested
//    @DisplayName("POST /familias")
//    class Salvar {
//
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "Deve retornar 201, a URI e salvar a família")
//        void deveSalvarFamilia() throws Exception {
//
//            Familia familia = Familia.builder()
//                    .id(1)
//                    .nome("Família 1")
//                    .cep("12345678")
//                    .numeroCasa(123)
//                    .renda(1000.0)
//                    .build();
//
//            Mockito.when(familiaService.salvar(Mockito.any(Familia.class)))
//                    .thenReturn(familia);
//
//            var content = """
//                    {
//                        "nome": "Família 1",
//                        "cep": "12345678",
//                        "numeroCasa": 123,
//                        "renda": 1000.0
//                    }
//                    """;
//
//            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post
//                                    (FamiliaEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isCreated())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.nome").value("Família 1"))
//                    .andExpect(jsonPath("$.cep").value("12345678"))
//                    .andExpect(jsonPath("$.numeroCasa").value(123))
//                    .andReturn();
//
//            assertTrue(result.getResponse().getHeader("Location")
//                    .contains(FamiliaEnum.BASE_URL.PATH + "/1"));
//        }
//
//        @Test
//        @DisplayName("Se o nome estiver em branco: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestNome() throws Exception {
//                var content = """
//                        {
//                            "nome": "",
//                            "cep": "12345678",
//                            "numeroCasa": 123,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.post
//                                (FamiliaEnum.BASE_URL.PATH)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o cep estiver em branco: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestCep() throws Exception {
//                var content = """
//                        {
//                            "nome": "Família 1",
//                            "cep": "",
//                            "numeroCasa": 123,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.post
//                                (FamiliaEnum.BASE_URL.PATH)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o número da casa for nulo: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestNumeroCasa() throws Exception {
//                var content = """
//                        {
//                            "nome": "Família 1",
//                            "cep": "12345678",
//                            "numeroCasa": null,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.post
//                                (FamiliaEnum.BASE_URL.PATH)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o cep for inválido: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestCepInvalido() throws Exception {
//                var content = """
//                        {
//                            "nome": "Família 1",
//                            "cep": "12345678",
//                            "numeroCasa": 123,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.post
//                                (FamiliaEnum.BASE_URL.PATH)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o número da casa for negativo: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestNumeroCasaNegativo() throws Exception {
//                var content = """
//                        {
//                            "nome": "Família 1",
//                            "cep": "12345678",
//                            "numeroCasa": -123,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.post
//                                (FamiliaEnum.BASE_URL.PATH)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//    }
//
//    @Nested
//    @DisplayName("PUT /familias/{id}")
//    class Atualizar {
//
//        @Test
//        @DisplayName("Se o id existir: " +
//                "Deve retornar 200 e atualizar o endereco da família")
//        void deveAtualizarFamilia() throws Exception {
//
//            Familia familia = Familia.builder()
//                    .id(1)
//                    .nome("Família 1")
//                    .cep("12345678")
//                    .numeroCasa(123)
//                    .renda(1000.0)
//                    .build();
//
//            Mockito.when(familiaService.atualizar(Mockito.anyInt(), Mockito.any(Familia.class)))
//                    .thenReturn(familia);
//
//            var content = """
//                    {
//                        "cep": "12345678",
//                        "numeroCasa": 123
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.put
//                            (FamiliaEnum.POR_ID.PATH, 1)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.nome").value("Família 1"))
//                    .andExpect(jsonPath("$.cep").value("12345678"))
//                    .andExpect(jsonPath("$.numeroCasa").value(123));
//        }
//
//        @Test
//        @DisplayName("Se o cep estiver em branco: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestCep() throws Exception {
//                var content = """
//                        {
//                            "cep": "",
//                            "numeroCasa": 123,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.put
//                                (FamiliaEnum.POR_ID.PATH, 1)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o número da casa for nulo: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestNumeroCasa() throws Exception {
//                var content = """
//                        {
//                            "cep": "12345678",
//                            "numeroCasa": null,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.put
//                                (FamiliaEnum.POR_ID.PATH, 1)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o cep for inválido: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestCepInvalido() throws Exception {
//                var content = """
//                        {
//                            "cep": "12345678",
//                            "numeroCasa": 123,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.put
//                                (FamiliaEnum.POR_ID.PATH, 1)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o número da casa for negativo: " +
//                "Deve retornar 400")
//        void deveRetornarBadRequestNumeroCasaNegativo() throws Exception {
//                var content = """
//                        {
//                            "cep": "12345678",
//                            "numeroCasa": -123,
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.put
//                                (FamiliaEnum.POR_ID.PATH, 1)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isBadRequest());
//        }
//    }
//
//    @Nested
//    @DisplayName("DELETE /familias/{id}")
//    class Deletar {
//
//        @Test
//        @DisplayName("Se o id existir: " +
//                "Deve retornar 204 e deletar a família")
//        void deveDeletarFamilia() throws Exception {
//
//            mockMvc.perform(MockMvcRequestBuilders.delete
//                            (FamiliaEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent());
//        }
//    }
//}