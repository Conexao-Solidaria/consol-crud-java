package com.consol.api.controller;
import com.consol.api.entity.Beneficio;
import com.consol.api.entity.Donatario;
import com.consol.api.entity.Familia;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.service.BeneficioService;
import com.consol.api.utils.BeneficioEnum;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeneficioController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Controller - Benefício")
public class BeneficioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeneficioService beneficioService;

        @Nested
        @DisplayName("GET /beneficios")
        class listar {
        // Se os dados estiverem corretos, 200 e listar todos os beneficios - OK
        // Se não houver beneficios, 204 e lista vazia - OK

        @Test
        @DisplayName("Se os dados estiverem corretos: " +
                "Deve retornar 200 e listar todos os benefícios")
        void deveListarTodosOsFamilias() throws Exception {

            Mockito.when(beneficioService.listar()).thenReturn(
                    List.of(
                            Beneficio.builder()
                                    .idBeneficio(1)
                                    .nome("Bolsa família")
                                    .valor(150.0)
                                    .donatario(new Donatario()).build(),

                            Beneficio.builder()
                                    .idBeneficio(2)
                                    .nome("Auxílio Emergencial")
                                    .valor(500.0)
                                    .donatario(new Donatario()).build()
                    ));

            mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.BASE_URL.PATH)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[0].id").value(1));

            Mockito.verify(beneficioService, Mockito.times(1)).listar();
        }

        @Test
        @DisplayName("Se não houver benefícios: 204 e lista vazia")
        void deveRetornarListaVazia() throws Exception {
            Mockito.when(beneficioService.listar()).thenReturn(List.of());

            mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.BASE_URL.PATH)
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());

            Mockito.verify(beneficioService, Mockito.times(1)).listar();
        }
    }

        @Nested
        @DisplayName("POST / beneficios")
        class cadastrar {
            // Se os dados estiverem corretos, 201, URI e salvar benefício
            // Se o nome stiver vazio, 400 e mensagem de erro
            // Se o valor estiver vazio, 400 e mensagem de erro
            // Se o nome estiver inválido, 400 e mensagem de erro
            // Se o valor estiver inválido, 40 e mensagem de erro
            // Se o nome estiver nulo, 400 e mensagem de erro
            // Se o valor estivernulo, 400 e mensagem de erro

            @Test
            @DisplayName("Se os dados estiverem corretos, 201, URI e benficio cadastrado")
            void deveCadastrarBeneficio() throws Exception {

                Beneficio novoBeneficio = Beneficio.builder()
                        .nome("Bolsa família")
                        .valor(150.0)
                        .donatario(new Donatario()).build();

                Beneficio beneficioCadastrado = Beneficio.builder()
                        .idBeneficio(1)
                        .nome("Bolsa família")
                        .valor(150.0)
                        .donatario(new Donatario()).build();

                Mockito.when(beneficioService.salvar(Mockito.any(Beneficio.class), Mockito.any(Integer.class)))
                        .thenReturn(beneficioCadastrado);

                var content = """
                    {
                        "nome": "Bolsa familia",
                        "valor": 150.0,
                        "donatario": {}
                    }""";

                mockMvc.perform(MockMvcRequestBuilders.post(BeneficioEnum.CRIAR.PATH,1)
                                .contentType("application/json")
                                .content(content))
                                .andExpect(status().isCreated())
                                .andExpect(header().string("Location", "/beneficios/donatario/1"))
                                .andExpect(jsonPath("$.id")
                                        .value(1))
                                .andExpect(jsonPath("$.nome")
                                        .value("Bolsa família"))
                                .andExpect(jsonPath("$.valor")
                                        .value(150.0));

                Mockito.verify(beneficioService, Mockito.times(1)).salvar(Mockito.any(Beneficio.class), Mockito.any(Integer.class));
            }
            @Test
            @DisplayName("Se o nome estiver vazio: " +
                    "Deve retornar 400 e uma mensagem de erro")
            void deveRetornarErroNomeVazio() throws Exception {

                var content = """
                    {
                        "nome": "",
                        "valor": "50.0"
                    }
                    """;

                mockMvc.perform(MockMvcRequestBuilders.post(BeneficioEnum.CRIAR.PATH,1)
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isBadRequest());
            }

            @Test
            @DisplayName("Se o valor estiver vazio: " +
                    "Deve retornar 400 e uma mensagem de erro")
            void deveRetornarErroValorVazio() throws Exception {

                var content = """
                    {
                        "nome": "Benefício X",
                        "valor": ""
                    }
                    """;

                mockMvc.perform(MockMvcRequestBuilders.post(BeneficioEnum.CRIAR.PATH,1)
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isBadRequest());
            }

            @Test
            @DisplayName("Se o valor estiver inválido: " +
                    "Deve retornar 400 e uma mensagem de erro")
            void deveRetornarErroValorInvalido() throws Exception {

                var content = """
                    {
                        "nome": "Benefício X",
                        "valor": "ABC"
                    }
                    """;

                mockMvc.perform(MockMvcRequestBuilders.post(BeneficioEnum.CRIAR.PATH,1)
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isBadRequest());
            }

        }

        @Nested
        @DisplayName("GET /beneficios/filtro")
        class listarPorDonatario {
            // Se existir donatário, 200 e o benefício

            @Test
            @DisplayName("Se o benefício existir: " +
                    "Deve retornar 200 e os benefícios por donatario")
            void deveRetornarBeneficioPorDonatario() throws Exception {

                Donatario donatario = new Donatario();
                donatario.setId(1);
                donatario.setDataCadastro(LocalDate.now());
                donatario.setNome("João Silva");
                donatario.setRg("123456789");
                donatario.setCpf("12345678901");
                donatario.setDataNascimento(LocalDate.of(1980, 5, 15));
                donatario.setTelefone1("11987654321");
                donatario.setTelefone2("11987654322");
                donatario.setEstadoCivil("Solteiro");
                donatario.setEscolaridade("Ensino Médio");
                donatario.setTrabalhando(true);
                donatario.setOcupacao("Operador de Máquina");
                donatario.setFamilia(null);


                List<Beneficio> beneficio = List.of(
                        Beneficio.builder()
                            .idBeneficio(1)
                            .nome("Benefício X")
                            .valor(50.0)
                            .donatario(donatario)
                            .build(),
                        Beneficio.builder()
                            .idBeneficio(2)
                            .nome("Benefício Y")
                            .valor(100.0)
                            .donatario(donatario)
                            .build()
                );

                Mockito.when(beneficioService.listarPorDonatario(1))
                        .thenReturn((List<Beneficio>) beneficio);

                mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.POR_FILTRO.PATH, 1)
                        .contentType("application/json"))

                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$.length()").value(2))
                        .andExpect(jsonPath("$[0].id").value(1))
                        .andExpect(jsonPath("$[0].nome").value("Benefício X"))
                        .andExpect(jsonPath("$[0].valor").value(50.0))
                        .andExpect(jsonPath("$[0].idDonatario").value(1)
                        );
            }

            @Test
            @DisplayName("Se não houver donatários e nem benefícios cadastrados: " +
                    "Deve retornar 204 e uma lista vazia")
            void deveRetornarListaVazia() throws Exception {

                Mockito.when(beneficioService.listarPorDonatario(1))
                        .thenReturn(Collections.emptyList());

                mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.POR_FILTRO.PATH,1)
                                .param("nome", "Donatário")
                                .contentType("application/json"))
                                .andExpect(status().isNoContent());
            }

        }

        @Nested
        @DisplayName("GET /beneficios/filtro")
        class listarPorFamilia {
            // Se houver familias, 200 e os benefícios
            // Se não houver familias, 204 e lista vazia

            @Test
            @DisplayName("Se o benefício e família existirem: " +
                    "Deve retornar 200 e uma lista de benefícios por família")
            void deveRetornarBeneficioPorFamilia() throws Exception {

                Familia familia = new Familia();
                familia.setId(1);
                familia.setNome("Familia Silva");
                familia.setCep("12345678");
                familia.setNumeroCasa(100);
                familia.setRenda(2500.00);

                Donatario donatario = new Donatario();
                donatario.setId(1);
                donatario.setDataCadastro(LocalDate.now());
                donatario.setNome("João Silva");
                donatario.setRg("123456789");
                donatario.setCpf("12345678901");
                donatario.setDataNascimento(LocalDate.of(1980, 5, 15));
                donatario.setTelefone1("11987654321");
                donatario.setTelefone2("11987654322");
                donatario.setEstadoCivil("Solteiro");
                donatario.setEscolaridade("Ensino Médio");
                donatario.setTrabalhando(true);
                donatario.setOcupacao("Operador de Máquina");
                donatario.setFamilia(familia);



                List<Beneficio> beneficios = List.of(
                        Beneficio.builder()
                                .idBeneficio(1)
                                .nome("Benefício X")
                                .valor(50.0)
                                .donatario(donatario)
                                .build(),
                        Beneficio.builder()
                                .idBeneficio(2)
                                .nome("Benefício Y")
                                .valor(100.0)
                                .donatario(donatario)
                                .build()
                );

                Mockito.when(beneficioService.listarPorFamilia(1))
                        .thenReturn(beneficios);

                mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.POR_FAMILIA.PATH, 1)
                                .contentType("application/json"))

                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$.length()").value(2))
                        .andExpect(jsonPath("$[0].id").value(1))
                        .andExpect(jsonPath("$[0].nome").value("Benefício X"))
                        .andExpect(jsonPath("$[0].valor").value(50.0))
                        .andExpect(jsonPath("$[0].idDonatario").value(1)
                        );
            }

            @Test
            @DisplayName("Se não houver famílias cadastradas: " +
                    "Deve retornar 204 e uma lista vazia")
            void deveRetornarListaVazia() throws Exception {

                Mockito.when(beneficioService.listarPorFamilia(1))
                        .thenReturn(Collections.emptyList());

                mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.POR_FAMILIA.PATH,1)
                                .param("nome", "Beneficio X")
                                .contentType("application/json"))
                                .andExpect(status().isNoContent());
            }
        }

        @Nested
        @DisplayName("PUT /beneficios/{id}")
        class atualizar {
            // Se os dados estiverem corretos, 200 e atualizar Beneficio

            @Test
            @DisplayName("Se os dados estiverem corretos, 200 e atualizar Beneficio")
            void deveCadastrarBeneficio() throws Exception {

                Donatario donatario = new Donatario();
                donatario.setId(1);
                donatario.setDataCadastro(LocalDate.now());
                donatario.setNome("João Silva");
                donatario.setRg("123456789");
                donatario.setCpf("12345678901");
                donatario.setDataNascimento(LocalDate.of(1980, 5, 15));
                donatario.setTelefone1("11987654321");
                donatario.setTelefone2("11987654322");
                donatario.setEstadoCivil("Solteiro");
                donatario.setEscolaridade("Ensino Médio");
                donatario.setTrabalhando(true);
                donatario.setOcupacao("Operador de Máquina");
                donatario.setFamilia(null);

                Beneficio beneficio = Beneficio.builder()
                        .idBeneficio(1)
                        .nome("Benefício X")
                        .valor(50.0)
                        .donatario(donatario)
                        .build();

                Mockito.when(beneficioService.atualizar(Mockito.any(Integer.class),Mockito.any(Beneficio.class)))
                        .thenReturn(beneficio);

                var content = """
                        {
                            "nome": "Benefício X",
                            "valor": "50.0"
                           
                        }
                        """;

                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(BeneficioEnum.POR_ID.PATH,1)
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.nome").value("Benefício X"))
                        .andExpect(jsonPath("$.valor").value(50.0))
                        .andExpect(jsonPath("$.idDonatario").value(1))
                        .andReturn();

            }

            @Test
            @DisplayName("Se o Id não existir: " +
                    "Deve retornar 400 e uma mensagem de erro")
            void deveRetornarErroIdNaoExiste() throws Exception {

                Mockito.when(beneficioService.listarPorId(1))
                                .thenThrow(EntidadeNaoEncontradaException.class);

                mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.POR_ID.PATH, 1)
                                .contentType("application/json"))
                        .andExpect(status().isNotFound());
            }

            @Test
            @DisplayName("Se o nome estiver vazio: " +
                    "Deve retornar 400 e uma mensagem de erro")
            void deveRetornarErroNomeVazio() throws Exception {

                var content = """
                    {
                        "nome": "",
                        "valor": "50.0"
                    }
                    """;

                mockMvc.perform(MockMvcRequestBuilders.patch(BeneficioEnum.POR_ID.PATH,1)
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isBadRequest());
            }

            @Test
            @DisplayName("Se o valor estiver vazio: " +
                    "Deve retornar 400 e uma mensagem de erro")
            void deveRetornarErroValorVazio() throws Exception {

                var content = """
                    {
                        "nome": "Benefício X",
                        "valor": ""
                    }
                    """;

                mockMvc.perform(MockMvcRequestBuilders.patch(BeneficioEnum.POR_ID.PATH,1)
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isBadRequest());
            }

            @Test
            @DisplayName("Se o valor estiver inválido: " +
                    "Deve retornar 400 e uma mensagem de erro")
            void deveRetornarErroValorInvalido() throws Exception {

                var content = """
                    {
                        "nome": "Benefício X",
                        "valor": "ABC"
                    }
                    """;

                mockMvc.perform(MockMvcRequestBuilders.patch(BeneficioEnum.POR_ID.PATH,1)
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isBadRequest());
            }


        }


        @Nested
        @DisplayName("DELETE /beneficios/{id}")
        class deletar {
            // Se o beneficio existir, retornar 204 e deletar o beneficio

            @Test
            @DisplayName("Se o benefício existir: " +
                    "Deve retornar 204 e deletar o benefício")
            void deveDeletarBeneficio() throws Exception {

                mockMvc.perform(MockMvcRequestBuilders.delete(BeneficioEnum.POR_ID.PATH, 1)
                                .contentType("application/json"))
                                .andExpect(status().isNoContent());
            }

        }
}
