package com.consol.api.controller;

import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Necessidade;
import com.consol.api.service.NecessidadeService;
import com.consol.api.utils.NecessidadeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NecessidadeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NecessidadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NecessidadeService necessidadeService;

    @InjectMocks
    private NecessidadeController necessidadeController;

    @Nested
    @DisplayName("POST /necessidades")
    class cadastrar {

        @Test
        @DisplayName("Se os dados estiverem corretos: " +
                "Deve retornar 201, a URI e salvar a necessidade")
        void deveRetornar201() throws Exception {
            Instituicao instituicao = Instituicao.builder()
                    .id(1)
                    .nome("Casa da Criança")
                    .cep("12345678")
                    .numeroImovel("123")
                    .descricao("Casa de crianças carentes")
                    .fotoPerfil(new byte[0])
                    .build();

            Necessidade necessidade = Necessidade.builder()
                    .id(1)
                    .tipo("Alimento")
                    .descricao("Arroz")
                    .instituicao(instituicao)
                    .build();

            Mockito.when(necessidadeService.cadastrar(Mockito.any(Necessidade.class))).thenReturn(necessidade);

            var content = """
                    {
                        "tipo": "Alimento",
                        "descricao": "Arroz",
                        "instituicao_id": 1
                    }
                    """;

            MvcResult result = mockMvc.perform(post(NecessidadeEnum.BASE_URL.PATH)
                            .contentType("application/json")
                            .content(content))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.tipo").value("Alimento"))
                    .andExpect(jsonPath("$.descricao").value("Arroz"))
                    .andExpect(jsonPath("$.instituicao.id").value(1))
                    .andExpect(jsonPath("$.instituicao.nome").value("Casa da Criança"))
                    .andExpect(jsonPath("$.instituicao.cep").value("12345678"))
                    .andExpect(jsonPath("$.instituicao.numeroImovel").value("123"))
                    .andExpect(jsonPath("$.instituicao.descricao").value("Casa de crianças carentes"))
                    .andExpect(jsonPath("$.instituicao.fotoPerfil").value(""))
                    .andReturn();

            assertTrue(result.getResponse()
                    .getHeader("Location").contains("/necessidades/" + necessidade.getId()));
        }

        @Test
        @DisplayName("Se o id da necessidade for nulo: " +
                "Deve retornar 400")
        void deveRetornar400Id() throws Exception {
            var content = """
                    {
                        "tipo": "Alimento",
                        "descricao": "Arroz",
                        "instituicao_id": null
                    }
                    """;

            mockMvc.perform(post(NecessidadeEnum.BASE_URL.PATH)
                            .contentType("application/json")
                            .content(content))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Se o tipo da necessidade for nulo ou vazio: " +
                "Deve retornar 400")
        void deveRetornar400Tipo() throws Exception {
            var content = """
                    {
                        "tipo": "",
                        "descricao": "Arroz",
                        "instituicao_id": 1
                    }
                    """;

            mockMvc.perform(post(NecessidadeEnum.BASE_URL.PATH)
                            .contentType("application/json")
                            .content(content))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Se a descrição da necessidade for nula ou vazia: " +
                "Deve retornar 400")
        void deveRetornar400Descricao() throws Exception {
            var content = """
                    {
                        "tipo": "Alimento",
                        "descricao": "",
                        "instituicao_id": 1
                    }
                    """;

            mockMvc.perform(post(NecessidadeEnum.BASE_URL.PATH)
                            .contentType("application/json")
                            .content(content))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("PUT /necessidades/{id}")
    class atualizar {
        @Test
        @DisplayName("Se os dados estiverem corretos: " +
                "Deve retornar 200 e a necessidade atualizada")
        void deveRetornar200() throws Exception {
            Instituicao instituicao = Instituicao.builder()
                    .id(1)
                    .nome("Casa da Criança")
                    .cep("12345678")
                    .numeroImovel("123")
                    .descricao("Casa de crianças carentes")
                    .fotoPerfil(new byte[0])
                    .build();

            Necessidade necessidade = Necessidade.builder()
                    .id(1)
                    .tipo("Alimento")
                    .descricao("Arroz")
                    .instituicao(instituicao)
                    .build();

            Mockito.when(necessidadeService.atualizar(
                    Mockito.any(Integer.class),
                    Mockito.any(Necessidade.class)
            )).thenReturn(necessidade);

            var content = """
                    {
                        "tipo": "Alimento",
                        "descricao": "Arroz",
                    }
                    """;

            mockMvc.perform(post(NecessidadeEnum.POR_ID.PATH, 1)
                            .contentType("application/json")
                            .content(content))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.tipo").value("Alimento"))
                    .andExpect(jsonPath("$.descricao").value("Arroz"))
                    .andExpect(jsonPath("$.instituicao.id").value(1))
                    .andExpect(jsonPath("$.instituicao.nome")
                            .value("Casa da Criança"))
                    .andExpect(jsonPath("$.instituicao.cep")
                            .value("12345678"))
                    .andExpect(jsonPath("$.instituicao.numeroImovel")
                            .value("123"))
                    .andExpect(jsonPath("$.instituicao.descricao")
                            .value("Casa de crianças carentes"))
                    .andExpect(jsonPath("$.instituicao.fotoPerfil").value(""));
        }

        @Test
        @DisplayName("Se o tipo da necessidade for nulo ou invalido: " +
                "Deve retornar 400")
        void deveRetornar400Id() throws Exception {
            var content = """
                    {
                        "tipo": "",
                        "descricao": "Arroz",
                    }
                    """;

            mockMvc.perform(post(NecessidadeEnum.POR_ID.PATH, 1)
                            .contentType("application/json")
                            .content(content))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Se a descrição da necessidade for nula ou vazia: " +
                "Deve retornar 400")
        void deveRetornar400Descricao() throws Exception {
            var content = """
                    {
                        "tipo": "Alimento",
                        "descricao": "",
                    }
                    """;

            mockMvc.perform(post(NecessidadeEnum.POR_ID.PATH, 1)
                            .contentType("application/json")
                            .content(content))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("DELETE /necessidades/{id}")
    class deletar {
        @Test
        @DisplayName("Se o id for valido: " +
                "Deve retornar 204")
        void deveRetornar204() throws Exception {
            mockMvc.perform(post(NecessidadeEnum.POR_ID.PATH, 1))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Se o id for invalido: " +
                "Deve retornar 400")
        void deveRetornar400() throws Exception {
            mockMvc.perform(post(NecessidadeEnum.POR_ID.PATH, "id"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /necessidades")
    class listar {
        @Test
        @DisplayName("Deve retornar 200 e uma lista de necessidades")
        void deveRetornar200() throws Exception {
            List<Necessidade> necessidades = List.of(
                    Necessidade.builder()
                            .id(1)
                            .tipo("Alimento")
                            .descricao("Arroz")
                            .instituicao(Instituicao.builder()
                                    .id(1)
                                    .nome("Casa da Criança")
                                    .cep("12345678")
                                    .numeroImovel("123")
                                    .descricao("Casa de crianças carentes")
                                    .fotoPerfil(new byte[0])
                                    .build())
                            .build(),
                    Necessidade.builder()
                            .id(2)
                            .tipo("Alimento")
                            .descricao("Feijão")
                            .instituicao(Instituicao.builder()
                                    .id(2)
                                    .nome("Casa do João")
                                    .cep("87654321")
                                    .numeroImovel("321")
                                    .descricao("Casa de crianças carentes")
                                    .fotoPerfil(new byte[0])
                                    .build())
                            .build()
            );

            Mockito.when(necessidadeService.buscarTodos()).thenReturn(necessidades);

            mockMvc.perform(post(NecessidadeEnum.BASE_URL.PATH))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].tipo").value("Alimento"))
                    .andExpect(jsonPath("$[0].descricao").value("Arroz"))
                    .andExpect(jsonPath("$[0].instituicao.id").value(1))
                    .andExpect(jsonPath("$[0].instituicao.nome")
                            .value("Casa da Criança"))
                    .andExpect(jsonPath("$[0].instituicao.cep")
                            .value("12345678"))
                    .andExpect(jsonPath("$[0].instituicao.numeroImovel")
                            .value("123"))
                    .andExpect(jsonPath("$[0].instituicao.descricao")
                            .value("Casa de crianças carentes"))
                    .andExpect(jsonPath("$[0].instituicao.fotoPerfil")
                            .value(""))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].tipo").value("Alimento"))
                    .andExpect(jsonPath("$[1].descricao").value("Feijão"))
                    .andExpect(jsonPath("$[1].instituicao.id").value(2))
                    .andExpect(jsonPath("$[1].instituicao.nome")
                            .value("Casa do João"))
                    .andExpect(jsonPath("$[1].instituicao.cep")
                            .value("87654321"))
                    .andExpect(jsonPath("$[1].instituicao.numeroImovel")
                            .value("321"))
                    .andExpect(jsonPath("$[1].instituicao.descricao")
                            .value("Casa de crianças carentes"))
                    .andExpect(jsonPath("$[1].instituicao.fotoPerfil")
                            .value(""));
        }

        @Test
        @DisplayName("Se não houver necessidades: " +
                "Deve retornar 204")
        void deveRetornar204() throws Exception {
            Mockito.when(necessidadeService.buscarTodos()).thenReturn(Collections.emptyList());

            mockMvc.perform(post(NecessidadeEnum.BASE_URL.PATH))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("GET /necessidades/{id}")
    class buscarPorId {
        @Test
        @DisplayName("Se a necessidade existir: " +
                "Deve retornar 200 e a necessidade")
        void deveRetornar200() throws Exception {
            Instituicao instituicao = Instituicao.builder()
                    .id(1)
                    .nome("Casa da Criança")
                    .cep("12345678")
                    .numeroImovel("123")
                    .descricao("Casa de crianças carentes")
                    .fotoPerfil(new byte[0])
                    .build();

            Necessidade necessidade = Necessidade.builder()
                    .id(1)
                    .tipo("Alimento")
                    .descricao("Arroz")
                    .instituicao(instituicao)
                    .build();

            Mockito.when(necessidadeService.buscarPorId(Mockito.any(Integer.class)))
                    .thenReturn(necessidade);

            mockMvc.perform(post(NecessidadeEnum.POR_ID.PATH, 1))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.tipo").value("Alimento"))
                    .andExpect(jsonPath("$.descricao").value("Arroz"))
                    .andExpect(jsonPath("$.instituicao.id").value(1))
                    .andExpect(jsonPath("$.instituicao.nome").value("Casa da Criança"))
                    .andExpect(jsonPath("$.instituicao.cep").value("12345678"))
                    .andExpect(jsonPath("$.instituicao.numeroImovel").value("123"))
                    .andExpect(jsonPath("$.instituicao.descricao").value("Casa de crianças carentes"))
                    .andExpect(jsonPath("$.instituicao.fotoPerfil").value(""));
        }

        @Test
        @DisplayName("Se o id for invalido: " +
                "Deve retornar 400")
        void deveRetornar400() throws Exception {
            mockMvc.perform(post(NecessidadeEnum.POR_ID.PATH, "id"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /necessidades/filtro")
    class porTipo {

        @Test
        @DisplayName("Se os dados estiverem corretos: " +
                "Deve retornar 200 e uma lista de necessidades por filtro")
        void deveRetornar200() throws Exception {
            List<Necessidade> necessidades = List.of(
                    Necessidade.builder()
                            .id(1)
                            .tipo("Alimento")
                            .descricao("Arroz")
                            .instituicao(Instituicao.builder()
                                    .id(1)
                                    .nome("Casa da Criança")
                                    .cep("12345678")
                                    .numeroImovel("123")
                                    .descricao("Casa de crianças carentes")
                                    .fotoPerfil(new byte[0])
                                    .build())
                            .build(),
                    Necessidade.builder()
                            .id(2)
                            .tipo("Alimento")
                            .descricao("Feijão")
                            .instituicao(Instituicao.builder()
                                    .id(2)
                                    .nome("Casa do João")
                                    .cep("87654321")
                                    .numeroImovel("321")
                                    .descricao("Casa de crianças carentes")
                                    .fotoPerfil(new byte[0])
                                    .build())
                            .build()
            );

            Mockito.when(necessidadeService.buscarPorTipo(Mockito.any(String.class)))
                    .thenReturn(necessidades);

            mockMvc.perform(post(NecessidadeEnum.FILTRO.PATH)
                            .param("tipo", "Alimento"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].tipo").value("Alimento"))
                    .andExpect(jsonPath("$[0].descricao").value("Arroz"))
                    .andExpect(jsonPath("$[0].instituicao.id").value(1))
                    .andExpect(jsonPath("$[0].instituicao.nome")
                            .value("Casa da Criança"))
                    .andExpect(jsonPath("$[0].instituicao.cep")
                            .value("12345678"))
                    .andExpect(jsonPath("$[0].instituicao.numeroImovel")
                            .value("123"))
                    .andExpect(jsonPath("$[0].instituicao.descricao")
                            .value("Casa de crianças carentes"))
                    .andExpect(jsonPath("$[0].instituicao.fotoPerfil")
                            .value(""))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].tipo").value("Alimento"))
                    .andExpect(jsonPath("$[1].descricao").value("Feijão"))
                    .andExpect(jsonPath("$[1].instituicao.id").value(2))
                    .andExpect(jsonPath("$[1].instituicao.nome")
                            .value("Casa do João"))
                    .andExpect(jsonPath("$[1].instituicao.cep")
                            .value("87654321"))
                    .andExpect(jsonPath("$[1].instituicao.numeroImovel")
                            .value("321"))
                    .andExpect(jsonPath("$[1].instituicao.descricao")
                            .value("Casa de crianças carentes"))
                    .andExpect(jsonPath("$[1].instituicao.fotoPerfil")
                            .value(""));
        }

        @Test
        @DisplayName("Se não houver necessidades: " +
                "Deve retornar 204")
        void deveRetornar204() throws Exception {
            Mockito.when(necessidadeService.buscarPorTipo(Mockito.any(String.class))).thenReturn(Collections.emptyList());

            mockMvc.perform(post(NecessidadeEnum.FILTRO.PATH)
                            .param("tipo", "Alimento"))
                    .andExpect(status().isNoContent());
        }
    }
}
