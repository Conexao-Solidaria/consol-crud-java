package com.consol.api.controller;

import com.consol.api.entity.Titular;
import com.consol.api.entity.RegistroVisita;
import com.consol.api.entity.RegistroVisitaController;
import com.consol.api.service.RegistroVisitaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.consol.api.utils.RegistroVisitaEnum.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistroVisitaController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Controller - RegistroVisita")
public class RegistroVisitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistroVisitaService registroVisitaService;

    @Nested
    @DisplayName("POST /registros-visitas")
    class PostRegistrosVisitas {

        @Test
        @DisplayName("Se os dados estiverem corretos: " +
                "Deve retornar 201, a URI e o RegistroVisita criado.")
        void deveRetornar201Created() throws Exception {
            Titular titular = Titular.builder()
                    .id(1)
                    .dataCadastro(LocalDate.now())
                    .nome("Donatário 1")
                    .rg("123456")
                    .cpf("12345678901")
                    .dataNascimento(LocalDate.parse("2000-01-01"))
                    .telefone1("123456789")
                    .telefone2("987654321")
                    .estadoCivil("Solteiro")
                    .escolaridade("Ensino Médio")
                    .trabalhando(true)
                    .ocupacao("Programador")
                    .build();

            RegistroVisita registroVisita = RegistroVisita.builder()
                    .id(1)
                    .titular(titular)
                    .dataVisita(LocalDate.now())
                    .descricao("Descrição 1")
                    .build();

            Mockito.when(registroVisitaService.salvar(Mockito.any(RegistroVisita.class)))
                    .thenReturn(registroVisita);

            var content = """
                    {
                        "donatarioId": 1,
                        "descricao": "Descrição 1"
                    }""";

            MvcResult result = mockMvc.perform(post(BASE_URL.PATH)
                            .contentType("application/json")
                            .content(content))
                    .andExpect(jsonPath("$.id")
                            .value(registroVisita.getId()))
                    .andExpect(jsonPath("$.donatario.id")
                            .value(titular.getId()))
                    .andExpect(jsonPath("$.donatario.nome")
                            .value(titular.getNome()))
                    .andExpect(jsonPath("$.donatario.rg")
                            .value(titular.getRg()))
                    .andExpect(jsonPath("$.donatario.cpf")
                            .value(titular.getCpf()))
                    .andExpect(jsonPath("$.donatario.dataNascimento")
                            .value(titular.getDataNascimento().toString()))
                    .andExpect(jsonPath("$.donatario.telefone1")
                            .value(titular.getTelefone1()))
                    .andExpect(jsonPath("$.donatario.telefone2")
                            .value(titular.getTelefone2()))
                    .andExpect(jsonPath("$.donatario.estadoCivil")
                            .value(titular.getEstadoCivil()))
                    .andExpect(jsonPath("$.donatario.escolaridade")
                            .value(titular.getEscolaridade()))
                    .andExpect(jsonPath("$.donatario.trabalhando")
                            .value(titular.getTrabalhando()))
                    .andExpect(jsonPath("$.donatario.ocupacao")
                            .value(titular.getOcupacao()))
                    .andExpect(jsonPath("$.descricao")
                            .value(registroVisita.getDescricao()))
                    .andExpect(jsonPath("$.dataVisita")
                            .value(registroVisita.getDataVisita().toString()))
                    .andReturn();

            assertTrue(result.getResponse().getHeader("Location")
                    .contains(BASE_URL.PATH + registroVisita.getId()));

            Mockito.verify(registroVisitaService, Mockito.times(1))
                    .salvar(Mockito.any(RegistroVisita.class));
        }

        @Test
        @DisplayName("Se o donatarioId for negativo: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequest() throws Exception {
            var content = """
                    {
                        "donatarioId": -10,
                        "descricao": "Descrição 1"
                    }""";

            mockMvc.perform(post(BASE_URL.PATH)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .salvar(Mockito.any(RegistroVisita.class));
        }

        @Test
        @DisplayName("Se o donatarioId for nulo: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDonatarioIdNulo() throws Exception {
            var content = """
                    {
                        "donaatarioId": ,
                        "descricao": "Descrição 1"
                    }""";

            mockMvc.perform(post(BASE_URL.PATH)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .salvar(Mockito.any(RegistroVisita.class));
        }

        @Test
        @DisplayName("Se a descrição for nula: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDescricaoNula() throws Exception {
            var content = """
                    {
                        "donatarioId": 1
                        "descricao":
                    }""";

            mockMvc.perform(post(BASE_URL.PATH)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .salvar(Mockito.any(RegistroVisita.class));
        }

        @Test
        @DisplayName("Se a descrição for vazia: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDescricaoVazia() throws Exception {
            var content = """
                    {
                        "donatarioId": 1,
                        "descricao": ""
                    }""";

            mockMvc.perform(post(BASE_URL.PATH)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .salvar(Mockito.any(RegistroVisita.class));
        }
    }

    @Nested
    @DisplayName("GET /registros-visitas")
    class GetRegistrosVisitas {

        @Test
        @DisplayName("Se houver registros de visitas: " +
                "Deve retornar 200 e a lista de registros de visitas.")
        void deveRetornar200EListaDeRegistrosDeVisitas() throws Exception {
            Titular titular1 = Titular.builder()
                    .id(1)
                    .dataCadastro(LocalDate.now())
                    .nome("Donatário 1")
                    .rg("123456")
                    .cpf("12345678901")
                    .dataNascimento(LocalDate.parse("2000-01-01"))
                    .telefone1("123456789")
                    .telefone2("987654321")
                    .estadoCivil("Solteiro")
                    .escolaridade("Ensino Médio")
                    .trabalhando(true)
                    .ocupacao("Programador")
                    .build();

            Titular titular2 = Titular.builder()
                    .id(2)
                    .dataCadastro(LocalDate.now())
                    .nome("Donatário 2")
                    .rg("654321")
                    .cpf("09876543210")
                    .dataNascimento(LocalDate.parse("2000-01-01"))
                    .telefone1("987654321")
                    .telefone2("123456789")
                    .estadoCivil("Casado")
                    .escolaridade("Ensino Superior")
                    .trabalhando(false)
                    .ocupacao("Analista de Sistemas")
                    .build();

            List<RegistroVisita> registros = List.of(
                    RegistroVisita.builder()
                            .id(1)
                            .titular(titular1)
                            .dataVisita(LocalDate.now())
                            .descricao("Descrição 1")
                            .build(),
                    RegistroVisita.builder()
                            .id(2)
                            .titular(titular2)
                            .dataVisita(LocalDate.now())
                            .descricao("Descrição 2")
                            .build()
            );

            Mockito.when(registroVisitaService.listar())
                    .thenReturn(registros);

            mockMvc.perform(get(BASE_URL.PATH)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id")
                            .value(registros.get(0).getId()))
                    .andExpect(jsonPath("$[0].donatario.id")
                            .value(titular1.getId()))
                    .andExpect(jsonPath("$[0].donatario.nome")
                            .value(titular1.getNome()))
                    .andExpect(jsonPath("$[0].donatario.rg")
                            .value(titular1.getRg()))
                    .andExpect(jsonPath("$[0].donatario.cpf")
                            .value(titular1.getCpf()))
                    .andExpect(jsonPath("$[0].donatario.dataNascimento")
                            .value(titular1.getDataNascimento().toString()))
                    .andExpect(jsonPath("$[0].donatario.telefone1")
                            .value(titular1.getTelefone1()))
                    .andExpect(jsonPath("$[0].donatario.telefone2")
                            .value(titular1.getTelefone2()))
                    .andExpect(jsonPath("$[0].donatario.estadoCivil")
                            .value(titular1.getEstadoCivil()))
                    .andExpect(jsonPath("$[0].donatario.escolaridade")
                            .value(titular1.getEscolaridade()))
                    .andExpect(jsonPath("$[0].donatario.trabalhando")
                            .value(titular1.getTrabalhando()))
                    .andExpect(jsonPath("$[0].donatario.ocupacao")
                            .value(titular1.getOcupacao()))
                    .andExpect(jsonPath("$[0].descricao")
                            .value(registros.get(0).getDescricao()))
                    .andExpect(jsonPath("$[0].dataVisita")
                            .value(registros.get(0).getDataVisita().toString()))
                    .andExpect(jsonPath("$[1].id")
                            .value(registros.get(1).getId()))
                    .andExpect(jsonPath("$[1].donatario.id")
                            .value(titular2.getId()))
                    .andExpect(jsonPath("$[1].donatario.nome")
                            .value(titular2.getNome()))
                    .andExpect(jsonPath("$[1].donatario.rg")
                            .value(titular2.getRg()))
                    .andExpect(jsonPath("$[1].donatario.cpf")
                            .value(titular2.getCpf()))
                    .andExpect(jsonPath("$[1].donatario.dataNascimento")
                            .value(titular2.getDataNascimento().toString()))
                    .andExpect(jsonPath("$[1].donatario.telefone1")
                            .value(titular2.getTelefone1()))
                    .andExpect(jsonPath("$[1].donatario.telefone2")
                            .value(titular2.getTelefone2()))
                    .andExpect(jsonPath("$[1].donatario.estadoCivil")
                            .value(titular2.getEstadoCivil()))
                    .andExpect(jsonPath("$[1].donatario.escolaridade")
                            .value(titular2.getEscolaridade()))
                    .andExpect(jsonPath("$[1].donatario.trabalhando")
                            .value(titular2.getTrabalhando()))
                    .andExpect(jsonPath("$[1].donatario.ocupacao")
                            .value(titular2.getOcupacao()))
                    .andExpect(jsonPath("$[1].descricao")
                            .value(registros.get(1).getDescricao()))
                    .andExpect(jsonPath("$[1].dataVisita")
                            .value(registros.get(1).getDataVisita().toString()));

            Mockito.verify(registroVisitaService, Mockito.times(1))
                    .listar();
        }

        @Test
        @DisplayName("Se não houver registros de visitas: " +
                "Deve retornar 204 e uma lista vazia.")
        void deveRetornar200EListaVazia() throws Exception {
            Mockito.when(registroVisitaService.listar())
                    .thenReturn(Collections.emptyList());

            mockMvc.perform(get(BASE_URL.PATH)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

            Mockito.verify(registroVisitaService, Mockito.times(1)).listar();
        }
    }

    @Nested
    @DisplayName("GET /registros-visitas/{id}")
    class GetRegistrosVisitasPorId {

        @Test
        @DisplayName("Se o registroVisita existir: " +
                "Deve retornar 200 e o registroVisita.")
        void deveRetornar200ERegistroDeVisita() throws Exception {
            Titular titular = Titular.builder()
                    .id(1)
                    .dataCadastro(LocalDate.now())
                    .nome("Donatário 1")
                    .rg("123456")
                    .cpf("12345678901")
                    .dataNascimento(LocalDate.parse("2000-01-01"))
                    .telefone1("123456789")
                    .telefone2("987654321")
                    .estadoCivil("Solteiro")
                    .escolaridade("Ensino Médio")
                    .trabalhando(true)
                    .ocupacao("Programador")
                    .build();

            RegistroVisita registroVisita = RegistroVisita.builder()
                    .id(1)
                    .titular(titular)
                    .dataVisita(LocalDate.now())
                    .descricao("Descrição 1")
                    .build();

            Mockito.when(registroVisitaService.porId(Mockito.any(Integer.class)))
                    .thenReturn(registroVisita);

            mockMvc.perform(get(POR_ID.PATH, 1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id")
                            .value(registroVisita.getId()))
                    .andExpect(jsonPath("$.donatario.id")
                            .value(titular.getId()))
                    .andExpect(jsonPath("$.donatario.nome")
                            .value(titular.getNome()))
                    .andExpect(jsonPath("$.donatario.rg")
                            .value(titular.getRg()))
                    .andExpect(jsonPath("$.donatario.cpf")
                            .value(titular.getCpf()))
                    .andExpect(jsonPath("$.donatario.dataNascimento")
                            .value(titular.getDataNascimento().toString()))
                    .andExpect(jsonPath("$.donatario.telefone1")
                            .value(titular.getTelefone1()))
                    .andExpect(jsonPath("$.donatario.telefone2")
                            .value(titular.getTelefone2()))
                    .andExpect(jsonPath("$.donatario.estadoCivil")
                            .value(titular.getEstadoCivil()))
                    .andExpect(jsonPath("$.donatario.escolaridade")
                            .value(titular.getEscolaridade()))
                    .andExpect(jsonPath("$.donatario.trabalhando")
                            .value(titular.getTrabalhando()))
                    .andExpect(jsonPath("$.donatario.ocupacao")
                            .value(titular.getOcupacao()))
                    .andExpect(jsonPath("$.descricao")
                            .value(registroVisita.getDescricao()))
                    .andExpect(jsonPath("$.dataVisita")
                            .value(registroVisita.getDataVisita().toString()));

            Mockito.verify(registroVisitaService, Mockito.times(1));
        }

        @Test
        @DisplayName("Se o id for negativo: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequest() throws Exception {
            mockMvc.perform(get(POR_ID.PATH, -1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porId(Mockito.any(Integer.class));
        }

        @Test
        @DisplayName("Se o id for nulo: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestIdNulo() throws Exception {
            mockMvc.perform(get(POR_ID.PATH, "")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porId(Mockito.any(Integer.class));
        }
    }

    @Nested
    @DisplayName("GET /registros-visitas/donatario/{id}")
    class GetRegistrosVisitasPorTitular {

        @Test
        @DisplayName("Se houver registros de visitas para o donatário: " +
                "Deve retornar 200 e a lista de registros de visitas.")
        void deveRetornar200EListaDeRegistrosDeVisitas() throws Exception {
            Titular titular = Titular.builder()
                    .id(1)
                    .dataCadastro(LocalDate.now())
                    .nome("Donatário 1")
                    .rg("123456")
                    .cpf("12345678901")
                    .dataNascimento(LocalDate.parse("2000-01-01"))
                    .telefone1("123456789")
                    .telefone2("987654321")
                    .estadoCivil("Solteiro")
                    .escolaridade("Ensino Médio")
                    .trabalhando(true)
                    .ocupacao("Programador")
                    .build();

            List<RegistroVisita> registros = List.of(
                    RegistroVisita.builder()
                            .id(1)
                            .titular(titular)
                            .dataVisita(LocalDate.now())
                            .descricao("Descrição 1")
                            .build(),
                    RegistroVisita.builder()
                            .id(2)
                            .titular(titular)
                            .dataVisita(LocalDate.now())
                            .descricao("Descrição 2")
                            .build()
            );

            Mockito.when(registroVisitaService.porDonatario(Mockito.any(Integer.class)))
                    .thenReturn(registros);

            mockMvc.perform(get(POR_DONATARIO.PATH, 1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id")
                            .value(registros.get(0).getId()))
                    .andExpect(jsonPath("$[0].donatario.id")
                            .value(titular.getId()))
                    .andExpect(jsonPath("$[0].donatario.nome")
                            .value(titular.getNome()))
                    .andExpect(jsonPath("$[0].donatario.rg")
                            .value(titular.getRg()))
                    .andExpect(jsonPath("$[0].donatario.cpf")
                            .value(titular.getCpf()))
                    .andExpect(jsonPath("$[0].donatario.dataNascimento")
                            .value(titular.getDataNascimento().toString()))
                    .andExpect(jsonPath("$[0].donatario.telefone1")
                            .value(titular.getTelefone1()))
                    .andExpect(jsonPath("$[0].donatario.telefone2")
                            .value(titular.getTelefone2()))
                    .andExpect(jsonPath("$[0].donatario.estadoCivil")
                            .value(titular.getEstadoCivil()))
                    .andExpect(jsonPath("$[0].donatario.escolaridade")
                            .value(titular.getEscolaridade()))
                    .andExpect(jsonPath("$[0].donatario.trabalhando")
                            .value(titular.getTrabalhando()))
                    .andExpect(jsonPath("$[0].donatario.ocupacao")
                            .value(titular.getOcupacao()))
                    .andExpect(jsonPath("$[0].descricao")
                            .value(registros.get(0).getDescricao()))
                    .andExpect(jsonPath("$[0].dataVisita")
                            .value(registros.get(0).getDataVisita().toString()))
                    .andExpect(jsonPath("$[1].id")
                            .value(registros.get(1).getId()))
                    .andExpect(jsonPath("$[1].donatario.id")
                            .value(titular.getId()))
                    .andExpect(jsonPath("$[1].donatario.nome")
                            .value(titular.getNome()))
                    .andExpect(jsonPath("$[1].donatario.rg")
                            .value(titular.getRg()))
                    .andExpect(jsonPath("$[1].donatario.cpf")
                            .value(titular.getCpf()))
                    .andExpect(jsonPath("$[1].donatario.dataNascimento")
                            .value(titular.getDataNascimento().toString()))
                    .andExpect(jsonPath("$[1].donatario.telefone1")
                            .value(titular.getTelefone1()))
                    .andExpect(jsonPath("$[1].donatario.telefone2")
                            .value(titular.getTelefone2()))
                    .andExpect(jsonPath("$[1].donatario.estadoCivil")
                            .value(titular.getEstadoCivil()))
                    .andExpect(jsonPath("$[1].donatario.escolaridade")
                            .value(titular.getEscolaridade()))
                    .andExpect(jsonPath("$[1].donatario.trabalhando")
                            .value(titular.getTrabalhando()))
                    .andExpect(jsonPath("$[1].donatario.ocupacao")
                            .value(titular.getOcupacao()))
                    .andExpect(jsonPath("$[1].descricao")
                            .value(registros.get(1).getDescricao()))
                    .andExpect(jsonPath("$[1].dataVisita")
                            .value(registros.get(1).getDataVisita().toString()));

            Mockito.verify(registroVisitaService, Mockito.times(1));
        }

        @Test
        @DisplayName("Se nao houver registros de visitas para o donatario: " +
                "Deve retornar 204 e uma lista vazia.")
        void deveRetornar204EListaVazia() throws Exception {
            Mockito.when(registroVisitaService.porDonatario(Mockito.any(Integer.class)))
                    .thenReturn(Collections.emptyList());

            mockMvc.perform(get(POR_DONATARIO.PATH, 1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

            Mockito.verify(registroVisitaService, Mockito.times(1));
        }

        @Test
        @DisplayName("Se o id for negativo: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequest() throws Exception {
            mockMvc.perform(get(POR_DONATARIO.PATH, -1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porDonatario(Mockito.any(Integer.class));
        }

        @Test
        @DisplayName("Se o id for nulo: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestIdNulo() throws Exception {
            mockMvc.perform(get(POR_DONATARIO.PATH, "")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porDonatario(Mockito.any(Integer.class));
        }
    }

    @Nested
    @DisplayName("GET /registros-visitas/periodo")
    class GetRegistrosVisitasPorPeriodo {

        @Test
        @DisplayName("Se houver registros de visitas no periodo: " +
                "Deve retornar 200 e a lista de registros de visitas.")
        void deveRetornar200EListaDeRegistrosDeVisitas() throws Exception {
            Titular titular1 = Titular.builder()
                    .id(1)
                    .dataCadastro(LocalDate.now())
                    .nome("Donatário 1")
                    .rg("123456")
                    .cpf("12345678901")
                    .dataNascimento(LocalDate.parse("2000-01-01"))
                    .telefone1("123456789")
                    .telefone2("987654321")
                    .estadoCivil("Solteiro")
                    .escolaridade("Ensino Médio")
                    .trabalhando(true)
                    .ocupacao("Programador")
                    .build();

            Titular titular2 = Titular.builder()
                    .id(2)
                    .dataCadastro(LocalDate.now())
                    .nome("Donatário 2")
                    .rg("654321")
                    .cpf("09876543210")
                    .dataNascimento(LocalDate.parse("2000-01-01"))
                    .telefone1("987654321")
                    .telefone2("123456789")
                    .estadoCivil("Casado")
                    .escolaridade("Ensino Superior")
                    .trabalhando(false)
                    .ocupacao("Analista de Sistemas")
                    .build();

            List<RegistroVisita> registros = List.of(
                    RegistroVisita.builder()
                            .id(1)
                            .titular(titular1)
                            .dataVisita(LocalDate.now())
                            .descricao("Descrição 1")
                            .build(),
                    RegistroVisita.builder()
                            .id(2)
                            .titular(titular2)
                            .dataVisita(LocalDate.now())
                            .descricao("Descrição 2")
                            .build()
            );

            Mockito.when(registroVisitaService.porPeriodo(
                            Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))
                    )
                    .thenReturn(registros);

            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "2021-01-01")
                            .param("dataFinal", "2021-12-31")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id")
                            .value(registros.get(0).getId()))
                    .andExpect(jsonPath("$[0].donatario.id")
                            .value(titular1.getId()))
                    .andExpect(jsonPath("$[0].donatario.nome")
                            .value(titular1.getNome()))
                    .andExpect(jsonPath("$[0].donatario.rg")
                            .value(titular1.getRg()))
                    .andExpect(jsonPath("$[0].donatario.cpf")
                            .value(titular1.getCpf()))
                    .andExpect(jsonPath("$[0].donatario.dataNascimento")
                            .value(titular1.getDataNascimento().toString()))
                    .andExpect(jsonPath("$[0].donatario.telefone1")
                            .value(titular1.getTelefone1()))
                    .andExpect(jsonPath("$[0].donatario.telefone2")
                            .value(titular1.getTelefone2()))
                    .andExpect(jsonPath("$[0].donatario.estadoCivil")
                            .value(titular1.getEstadoCivil()))
                    .andExpect(jsonPath("$[0].donatario.escolaridade")
                            .value(titular1.getEscolaridade()))
                    .andExpect(jsonPath("$[0].donatario.trabalhando")
                            .value(titular1.getTrabalhando()))
                    .andExpect(jsonPath("$[0].donatario.ocupacao")
                            .value(titular1.getOcupacao()))
                    .andExpect(jsonPath("$[0].descricao")
                            .value(registros.get(0).getDescricao()))
                    .andExpect(jsonPath("$[0].dataVisita")
                            .value(registros.get(0).getDataVisita().toString()))
                    .andExpect(jsonPath("$[1].id")
                            .value(registros.get(1).getId()))
                    .andExpect(jsonPath("$[1].donatario.id")
                            .value(titular2.getId()))
                    .andExpect(jsonPath("$[1].donatario.nome")
                            .value(titular2.getNome()))
                    .andExpect(jsonPath("$[1].donatario.rg")
                            .value(titular2.getRg()))
                    .andExpect(jsonPath("$[1].donatario.cpf")
                            .value(titular2.getCpf()))
                    .andExpect(jsonPath("$[1].donatario.dataNascimento")
                            .value(titular2.getDataNascimento().toString()))
                    .andExpect(jsonPath("$[1].donatario.telefone1")
                            .value(titular2.getTelefone1()))
                    .andExpect(jsonPath("$[1].donatario.telefone2")
                            .value(titular2.getTelefone2()))
                    .andExpect(jsonPath("$[1].donatario.estadoCivil")
                            .value(titular2.getEstadoCivil()))
                    .andExpect(jsonPath("$[1].donatario.escolaridade")
                            .value(titular2.getEscolaridade()))
                    .andExpect(jsonPath("$[1].donatario.trabalhando")
                            .value(titular2.getTrabalhando()))
                    .andExpect(jsonPath("$[1].donatario.ocupacao")
                            .value(titular2.getOcupacao()))
                    .andExpect(jsonPath("$[1].descricao")
                            .value(registros.get(1).getDescricao()))
                    .andExpect(jsonPath("$[1].dataVisita")
                            .value(registros.get(1).getDataVisita().toString()));

            Mockito.verify(registroVisitaService, Mockito.times(1));
        }

        @Test
        @DisplayName("Se nao houver registros de visitas no periodo: " +
                "Deve retornar 204 e uma lista vazia.")
        void deveRetornar204EListaVazia() throws Exception {
            Mockito.when(registroVisitaService.porPeriodo(
                            Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))
                    )
                    .thenReturn(Collections.emptyList());

            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "2021-01-01")
                            .param("dataFinal", "2021-12-31")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

            Mockito.verify(registroVisitaService, Mockito.times(1));
        }

        @Test
        @DisplayName("Se a dataInicial for nula: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataInicialNula() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataFinal", "2021-12-31")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }

        @Test
        @DisplayName("Se a dataFinal for nula: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataFinalNula() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "2021-01-01")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }

        @Test
        @DisplayName("Se a dataInicial for posterior a dataFinal: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataInicialPosteriorDataFinal() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "2021-12-31")
                            .param("dataFinal", "2021-01-01")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }

        @Test
        @DisplayName("Se a dataInicial for invalida: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataInicialInvalida() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "2021-13-01")
                            .param("dataFinal", "2021-12-31")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }

        @Test
        @DisplayName("Se a dataFinal for invalida: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataFinalInvalida() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "2021-01-01")
                            .param("dataFinal", "2021-13-31")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }

        @Test
        @DisplayName("Se a dataInicial for vazia: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataInicialVazia() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "")
                            .param("dataFinal", "2021-12-31")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }

        @Test
        @DisplayName("Se a dataFinal for vazia: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataFinalVazia() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "2021-01-01")
                            .param("dataFinal", "")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }

        @Test
        @DisplayName("Se a data inicial for futura: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataInicialFutura() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "3000-01-01")
                            .param("dataFinal", "3001-12-31")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }

        @Test
        @DisplayName("Se a data final for futura: " +
                "Deve retornar 400 e uma mensagem de erro.")
        void deveRetornar400BadRequestDataFinalFutura() throws Exception {
            mockMvc.perform(get(POR_PERIODO.PATH)
                            .param("dataInicial", "2021-01-01")
                            .param("dataFinal", "3000-12-31")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            Mockito.verify(registroVisitaService, Mockito.never())
                    .porPeriodo(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        }
    }
}
