//package com.consol.api.controller;
//
//import com.consol.api.entity.Titular;
//import com.consol.api.service.TitularService;
//import com.consol.api.utils.DonatarioEnum;
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
//
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(TitularController.class)
//@AutoConfigureMockMvc(addFilters = false)
//class TitularControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TitularService titularService;
//
//    @Nested
//    @DisplayName("GET /donatarios")
//    class listar {
//
//        @Test
//        @DisplayName("Se houver donatarios: " +
//                "Deve retornar 200 e listar todos os donatários")
//        void deveListarTodosOsDonatarios() throws Exception {
//
//            Mockito.when(titularService.listar()).thenReturn(
//                    List.of(
//                            new Titular(),
//                            new Titular()));
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/donatarios")
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(2));
//        }
//
//        @Test
//        @DisplayName("Se não houver donatários: " +
//                "Deve retornar 204 e uma lista vazia")
//        void deveRetornarListaVazia() throws Exception {
//
//            Mockito.when(titularService.listar()).thenReturn(Collections.emptyList());
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/donatarios")
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(0));
//        }
//    }
//
//    @Nested
//    @DisplayName("POST /donatarios")
//    class salvar {
//
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "Deve retornar 201, a URI e salvar o donatário")
//        void deveSalvarDonatario() throws Exception {
//
//            Titular titular = Titular.builder()
//                    .id(1)
//                    .dataCadastro(LocalDate.now())
//                    .nome("Donatário 1")
//                    .rg("123456")
//                    .cpf("12345678901")
//                    .dataNascimento(LocalDate.parse("2000-01-01"))
//                    .telefone1("123456789")
//                    .telefone2("987654321")
//                    .estadoCivil("Solteiro")
//                    .escolaridade("Ensino Médio")
//                    .trabalhando(true)
//                    .ocupacao("Programador")
//                    .build();
//
//            Mockito.when(titularService.salvar(
//                    Mockito.any(Titular.class),
//                    Mockito.any(Integer.class)
//                    ))
//                    .thenReturn(titular);
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post
//                                    (DonatarioEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isCreated())
//                    .andExpect(jsonPath("$.idDonatario").value(1))
//                    .andExpect(jsonPath("$.nome").value("Donatário 1"))
//                    .andExpect(jsonPath("$.rg").value("123456"))
//                    .andExpect(jsonPath("$.cpf").value("12345678901"))
//                    .andExpect(jsonPath("$.dataNascimento")
//                            .value("2000-01-01"))
//                    .andExpect(jsonPath("$.telefone1").value("123456789"))
//                    .andExpect(jsonPath("$.telefone2").value("987654321"))
//                    .andExpect(jsonPath("$.estadoCivil").value("Solteiro"))
//                    .andExpect(jsonPath("$.escolaridade")
//                            .value("Ensino Médio"))
//                    .andExpect(jsonPath("$.trabalhando").value(true))
//                    .andExpect(jsonPath("$.ocupacao").value("Programador"))
//                    .andReturn();
//
//            assertTrue(result.getResponse().getHeader("Location")
//                    .contains(DonatarioEnum.BASE_URL.PATH + "/1"));
//        }
//
//        @Test
//        @DisplayName("Se o nome estiver vazio: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroNomeVazio() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O nome é obrigatório"));
//        }
//
//        @Test
//        @DisplayName("Se o RG estiver vazio: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroRgVazio() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O RG é obrigatório"));
//        }
//
//        @Test
//        @DisplayName("Se o CPF estiver vazio: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroCpfVazio() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O CPF é obrigatório"));
//        }
//
//        @Test
//        @DisplayName("Se a data de nascimento estiver vazia: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroDataNascimentoVazia() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A data de nascimento é obrigatória"));
//        }
//
//        @Test
//        @DisplayName("Se o telefone1 e o telefone2 estiverem vazios: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroCelularETelefoneVazios() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "",
//                        "telefone2": "",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O telefone1 ou o telefone2 é obrigatório"));
//        }
//
//        @Test
//        @DisplayName("Se o estado civil estiver vazio: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroEstadoCivilVazio() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O estado civil é obrigatório"));
//        }
//
//        @Test
//        @DisplayName("Se rg for inválido: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroRgInvalido() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "1234567",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O RG é inválido"));
//        }
//
//        @Test
//        @DisplayName("Se o CPF for inválido: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroCpfInvalido() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "1234567890",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O CPF é inválido"));
//        }
//
//        @Test
//        @DisplayName("Se a data de nascimento for futura: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroDataNascimentoFutura() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2100-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A data de nascimento não pode ser futura"));
//        }
//
//        @Test
//        @DisplayName("Se o telefone1 for inválido: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroCelularInvalido() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "1234567890",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O telefone1 é inválido"));
//        }
//
//        @Test
//        @DisplayName("Se o telefone2 for inválido: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroTelefoneInvalido() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "98765432",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O telefone2 é inválido"));
//        }
//
//        @Test
//        @DisplayName("Se o estado civil for inválido: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroEstadoCivilInvalido() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteirox",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O estado civil é inválido"));
//        }
//
//        @Test
//        @DisplayName("Se a escolaridade for inválida: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroEscolaridadeInvalida() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médiox",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("A escolaridade é inválida"));
//        }
//
//        @Test
//        @DisplayName("Se trabalhando for nulo: " +
//                "Deve retornar 400 e uma mensagem de erro")
//        void deveRetornarErroTrabalhandoNulo() throws Exception {
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": null,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/donatarios")
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("O campo trabalhando é obrigatório"));
//        }
//    }
//
//    @Nested
//    @DisplayName("GET /donatarios/{id}")
//    class porId {
//
//        @Test
//        @DisplayName("Se o donatário existir: " +
//                "Deve retornar 200 e o donatário")
//        void deveRetornarDonatario() throws Exception {
//
//            Titular titular = Titular.builder()
//                    .id(1)
//                    .dataCadastro(LocalDate.now())
//                    .nome("Donatário 1")
//                    .rg("123456")
//                    .cpf("12345678901")
//                    .dataNascimento(LocalDate.parse("2000-01-01"))
//                    .telefone1("123456789")
//                    .telefone2("987654321")
//                    .estadoCivil("Solteiro")
//                    .escolaridade("Ensino Médio")
//                    .trabalhando(true)
//                    .ocupacao("Programador")
//                    .build();
//
//            Mockito.when(titularService.porId(1))
//                    .thenReturn(titular);
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DonatarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.idDonatario").value(1))
//                    .andExpect(jsonPath("$.nome").value("Donatário 1"))
//                    .andExpect(jsonPath("$.rg").value("123456"))
//                    .andExpect(jsonPath("$.cpf").value("12345678901"))
//                    .andExpect(jsonPath("$.dataNascimento")
//                            .value("2000-01-01"))
//                    .andExpect(jsonPath("$.telefone1").value("123456789"))
//                    .andExpect(jsonPath("$.telefone2").value("987654321"))
//                    .andExpect(jsonPath("$.estadoCivil").value("Solteiro"))
//                    .andExpect(jsonPath("$.escolaridade")
//                            .value("Ensino Médio"))
//                    .andExpect(jsonPath("$.trabalhando").value(true))
//                    .andExpect(jsonPath("$.ocupacao").value("Programador"));
//        }
//
//        @Test
//        @DisplayName("Se o donatário não existir: " +
//                "Deve retornar 404 e uma mensagem de erro")
//        void deveRetornarErroDonatarioNaoEncontrado() throws Exception {
//
//                Mockito.when(titularService.porId(1))
//                        .thenReturn(null);
//
//                mockMvc.perform(MockMvcRequestBuilders.get(DonatarioEnum.POR_ID.PATH, 1)
//                                .contentType("application/json"))
//                        .andExpect(status().isNotFound())
//                        .andExpect(jsonPath("$.mensagem")
//                                .value("Donatário não encontrado"));
//        }
//    }
//
//    @Nested
//    @DisplayName("GET /donatarios/filtro")
//    class listarPorNome {
//
//        @Test
//        @DisplayName("Se houver donatários com o nome: " +
//                "Deve retornar 200 e os donatários")
//        void deveRetornarDonatarios() throws Exception {
//
//                Mockito.when(titularService.listarPorNome("Donatário"))
//                        .thenReturn(List.of(
//                                Titular.builder()
//                                        .id(1)
//                                        .nome("Donatário 1")
//                                        .build(),
//                                Titular.builder()
//                                        .id(2)
//                                        .nome("Donatário 2")
//                                        .build()));
//
//                mockMvc.perform(MockMvcRequestBuilders.get(DonatarioEnum.FILTRO.PATH)
//                                .param("nome", "Donatário")
//                                .contentType("application/json"))
//                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$").isArray())
//                        .andExpect(jsonPath("$.length()").value(2))
//                        .andExpect(jsonPath("$[0].idDonatario").value(1))
//                        .andExpect(jsonPath("$[0].nome").value("Donatário 1"))
//                        .andExpect(jsonPath("$[1].idDonatario").value(2))
//                        .andExpect(jsonPath("$[1].nome").value("Donatário 2"));
//        }
//
//        @Test
//        @DisplayName("Se não houver donatários com o nome: " +
//                "Deve retornar 204 e uma lista vazia")
//        void deveRetornarListaVazia() throws Exception {
//
//            Mockito.when(titularService.listarPorNome("Donatário"))
//                    .thenReturn(Collections.emptyList());
//
//            mockMvc.perform(MockMvcRequestBuilders.get(DonatarioEnum.FILTRO.PATH)
//                            .param("nome", "Donatário")
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$.length()").value(0));
//        }
//    }
//
//    @Nested
//    @DisplayName("PUT /donatarios/{id}")
//    class atualizar {
//
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "Deve retornar 200 e atualizar o donatário")
//        void deveAtualizarDonatario() throws Exception {
//
//                Titular titular = Titular.builder()
//                        .id(1)
//                        .dataCadastro(LocalDate.now())
//                        .nome("Donatário 1")
//                        .rg("123456")
//                        .cpf("12345678901")
//                        .dataNascimento(LocalDate.parse("2000-01-01"))
//                        .telefone1("123456789")
//                        .telefone2("987654321")
//                        .estadoCivil("Solteiro")
//                        .escolaridade("Ensino Médio")
//                        .trabalhando(true)
//                        .ocupacao("Programador")
//                        .build();
//
//                Mockito.when(titularService.atualizar(1, new Titular()))
//                        .thenReturn(titular);
//
//                var content = """
//                        {
//                            "nome": "Donatário 1",
//                            "rg": "123456",
//                            "cpf": "12345678901",
//                            "dataNascimento": "2000-01-01",
//                            "telefone1": "123456789",
//                            "telefone2": "987654321",
//                            "estadoCivil": "Solteiro",
//                            "escolaridade": "Ensino Médio",
//                            "trabalhando": true,
//                            "ocupacao": "Programador"
//                        }
//                        """;
//
//                mockMvc.perform(MockMvcRequestBuilders.put(DonatarioEnum.POR_ID.PATH, 1)
//                                .contentType("application/json")
//                                .content(content))
//                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$.idDonatario").value(1))
//                        .andExpect(jsonPath("$.nome").value("Donatário 1"))
//                        .andExpect(jsonPath("$.rg").value("123456"))
//                        .andExpect(jsonPath("$.cpf").value("12345678901"))
//                        .andExpect(jsonPath("$.dataNascimento")
//                                .value("2000-01-01"))
//                        .andExpect(jsonPath("$.telefone1").value("123456789"))
//                        .andExpect(jsonPath("$.telefone2").value("987654321"))
//                        .andExpect(jsonPath("$.estadoCivil").value("Solteiro"))
//                        .andExpect(jsonPath("$.escolaridade")
//                                .value("Ensino Médio"))
//                        .andExpect(jsonPath("$.trabalhando").value(true))
//                        .andExpect(jsonPath("$.ocupacao").value("Programador"));
//        }
//
//        @Test
//        @DisplayName("Se o id nao existir: " +
//                "Deve retornar 404 e uma mensagem de erro")
//        void deveRetornarErroIdNaoEncontrado() throws Exception {
//
//            Mockito.when(titularService.atualizar(1, new Titular()))
//                    .thenReturn(null);
//
//            var content = """
//                    {
//                        "nome": "Donatário 1",
//                        "rg": "123456",
//                        "cpf": "12345678901",
//                        "dataNascimento": "2000-01-01",
//                        "telefone1": "123456789",
//                        "telefone2": "987654321",
//                        "estadoCivil": "Solteiro",
//                        "escolaridade": "Ensino Médio",
//                        "trabalhando": true,
//                        "ocupacao": "Programador"
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.put(DonatarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isNotFound())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("Donatário não encontrado"));
//        }
//    }
//
//    @Nested
//    @DisplayName("DELETE /donatarios/{id}")
//    class deletar {
//
//        @Test
//        @DisplayName("Se o donatário existir: " +
//                "Deve retornar 204 e deletar o donatário")
//        void deveDeletarDonatario() throws Exception {
//
//            mockMvc.perform(MockMvcRequestBuilders.delete(DonatarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent());
//        }
//
//        @Test
//        @DisplayName("Se o donatário não existir: " +
//                "Deve retornar 404 e uma mensagem de erro")
//        void deveRetornarErroDonatarioNaoEncontrado() throws Exception {
//
//            mockMvc.perform(MockMvcRequestBuilders.delete(DonatarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isNotFound())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("Donatário não encontrado"));
//        }
//    }
//}