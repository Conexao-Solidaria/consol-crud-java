package com.consol.api.controller;
import com.consol.api.entity.Beneficio;
import com.consol.api.entity.Donatario;
import com.consol.api.entity.Familia;
import com.consol.api.service.BeneficioService;
import com.consol.api.service.FamiliaService;
import com.consol.api.utils.BeneficioEnum;
import com.consol.api.utils.DonatarioEnum;
import com.consol.api.utils.FamiliaEnum;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeneficioControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
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
                                    .id(1)
                                    .nome("Bolsa família")
                                    .valor(150.0)
                                    .donatario(new Donatario()).build(),

                            Beneficio.builder()
                                    .id(2)
                                    .nome("Auxílio Emergencial")
                                    .valor(500.0)
                                    .donatario(new Donatario()).build()
                    ));

            mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.BASE_URL.PATH)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$.id").value(1))

            ;

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
                        .id(1)
                        .nome("Bolsa família")
                        .valor(150.0)
                        .donatario(new Donatario()).build();

                Mockito.when(beneficioService.cadastrar(Mockito.any(Beneficio.class)))
                        .thenReturn(beneficioCadastrado);

                mockMvc.perform(MockMvcRequestBuilders.post(BeneficioEnum.BASE_URL.PATH)
                                .contentType("application/json")
                                .content("{ \"nome\": \"Bolsa família\", \"valor\": 150.0, \"donatario\": {} }"))
                        .andExpect(status().isCreated())
                        .andExpect(header().string("Location", "/beneficios/1"))
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.nome").value("Bolsa família"))
                        .andExpect(jsonPath("$.valor").value(150.0));

                Mockito.verify(beneficioService, Mockito.times(1)).cadastrar(Mockito.any(Beneficio.class));
            }


            @Test
            @DisplayName("Se não houver benefícios: 204 e lista vazia")
            void deveRetornarListaVazia() throws Exception {
                Mockito.when(beneficioService.listar()).thenReturn(Collections.emptyList());

                mockMvc.perform(MockMvcRequestBuilders.get("/beneficios")
                                .contentType("application/json"))
                        .andExpect(status().isNoContent())
                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$.length()").value(0));

            }

        }

        @Nested
        @DisplayName("GET /beneficios/filtro")
        class listarPorDonatario {
            // Se existir donatário, 200 e o benefício
            // Se não existir donatário, 404 e mensagem de erro

            @Test
            @DisplayName("Se o donatário existir: " +
                    "Deve retornar 200 e o donatário")
            void deveRetornarBeneficioPorDonatario() throws Exception {

                Donatario donatario = Donatario.builder()
                        .idDonatario(1)
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

                Mockito.when(beneficioService.listarPorDonatario("Donatário X"))
                        .thenReturn((List<Beneficio>) donatario);

                mockMvc.perform(MockMvcRequestBuilders.get(DonatarioEnum.POR_ID.PATH, 1)
                        .contentType("application/json"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.idDonatario").value(1))
                        .andExpect(jsonPath("$.nome").value("Donatário 1"))
                        .andExpect(jsonPath("$.rg").value("123456"))
                        .andExpect(jsonPath("$.cpf").value("12345678901"))
                        .andExpect(jsonPath("$.dataNascimento").value("2000-01-01"))
                        .andExpect(jsonPath("$.telefone1").value("987654322"))
                        .andExpect(jsonPath("$.telefone2").value("987654321"))
                        .andExpect(jsonPath("$.estadoCivil").value("Solteiro"))
                        .andExpect(jsonPath("$.escolaridade").value("Ensino Médio"))
                        .andExpect(jsonPath("$.trabalhando").value(true))
                        .andExpect(jsonPath("$.ocupacao").value("Programador"));
            }

            @Test
            @DisplayName("Se não houver donatários cadastrados: " +
                    "Deve retornar 204 e uma lista vazia")
            void deveRetornarListaVazia() throws Exception {

                Mockito.when(beneficioService.listarPorDonatario("Donatário"))
                        .thenReturn(Collections.emptyList());

                mockMvc.perform(MockMvcRequestBuilders.get(DonatarioEnum.FILTRO.PATH)
                                .param("nome", "Donatário")
                                .contentType("application/json"))
                                .andExpect(status().isNoContent())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$.length()").value(0));
            }

        }

        @Nested
        @DisplayName("GET /beneficios/filtro")
        class listarPorFamilia {
            // Se houver familias, 200 e os benefícios
            // Se não houver familias, 204 e lista vazia

            @Test
            @DisplayName("Se a família existir: " +
                    "Deve retornar 200 e a família")
            void deveRetornarBeneficioPorFamilia() throws Exception {

                Familia familia = Familia.builder()
                        .id(1)
                        .nome("Famíla X")
                        .cep("09120640")
                        .numeroCasa(26)
                        .renda(2.500)
                        .build();

                Mockito.when(beneficioService.listarPorFamilia("Família X"))
                        .thenReturn((List<Beneficio>) familia);

                mockMvc.perform(MockMvcRequestBuilders.get(DonatarioEnum.POR_ID.PATH, 1)
                                .contentType("application/json"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.nome").value("Família X"))
                        .andExpect(jsonPath("$.cep").value("09120640"))
                        .andExpect(jsonPath("$.numeroCasa").value(26))
                        .andExpect(jsonPath("$.renda").value(2.500));
            }

            @Test
            @DisplayName("Se não houver famílias cadastradas: " +
                    "Deve retornar 204 e uma lista vazia")
            void deveRetornarListaVazia() throws Exception {

                Mockito.when(beneficioService.listarPorFamilia("Família X"))
                        .thenReturn(Collections.emptyList());

                mockMvc.perform(MockMvcRequestBuilders.get(DonatarioEnum.FILTRO.PATH)
                                .param("nome", "Família")
                                .contentType("application/json"))
                                .andExpect(status().isNoContent())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$.length()").value(0));
            }
        }

        @Nested
        @DisplayName("PUT /beneficios/{id}")
        class atualizar {
            // Se os dados estiverem corretos, 200 e atualizar Beneficio
            // Se o id não existir, 404 e mensagem de erro

            @Test
            @DisplayName("Se os dados estiverem corretos, 200 e atualizar Beneficio")
            void deveCadastrarBeneficio() throws Exception {

                Beneficio beneficio = Beneficio.builder()
                        .id(1)
                        .nome("Benefício X")
                        .valor(50.0)
                        .build();

                Mockito.when(beneficioService.cadastrar(Mockito.any(Beneficio.class)))
                        .thenReturn(beneficio);

                var content = """
                        {
                            "id": "1",
                            "nome": "Benefício X",
                            "valor": "50.0"
                        }
                        """;

                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post
                                        (BeneficioEnum.BASE_URL.PATH)
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.nome").value("Benefício X"))
                        .andExpect(jsonPath("$.valor").value(50.0))
                        .andReturn();

                assertTrue(result.getResponse().getHeader("Location")
                        .contains(BeneficioEnum.BASE_URL.PATH + "/1"));
            }

            @Test
            @DisplayName("Se o Id não existir: " +
                    "Deve retornar 400 e uma mensagem de erro")
            void deveRetornarErroIdNaoExiste() throws Exception {

                Mockito.when(beneficioService.listarPorId(1))
                        .thenReturn(null);

                mockMvc.perform(MockMvcRequestBuilders.get(BeneficioEnum.POR_ID.PATH, 1)
                                .contentType("application/json"))
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.mensagem").value("Benefício não encontrado"));
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

                mockMvc.perform(MockMvcRequestBuilders.post("/beneficios")
                                .contentType("application/json")
                                .content(content))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.mensagem").value("O nome é obrigatório"));
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

                mockMvc.perform(MockMvcRequestBuilders.post("/beneficios")
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.mensagem").value("O valor é obrigatório"));
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

                mockMvc.perform(MockMvcRequestBuilders.post("/beneficios")
                                .contentType("application/json")
                                .content(content))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.mensagem").value("O valor está inválido"));
            }

        }


        @Nested
        @DisplayName("DELETE /beneficios/{id}")
        class deletar {
            // Se o beneficio existir, retornar 204 e deletar o beneficio
            // Se o benefício não existir, retornar 404 e mensagem de erro

            @Test
            @DisplayName("Se o benefício existir: " +
                    "Deve retornar 204 e deletar o benefício")
            void deveDeletarBeneficio() throws Exception {

                Mockito.when(beneficioService.deletar(1))
                        .thenReturn(true);

                mockMvc.perform(MockMvcRequestBuilders.delete(BeneficioEnum.POR_ID.PATH, 1)
                                .contentType("application/json"))
                                .andExpect(status().isNoContent());
            }

            @Test
            @DisplayName("Se o benefício não existir: " +
                    "Deve retornar 404 e uma mensagem de erro")
            void deveRetornarErroBeneficioNaoEncontrado() throws Exception {

                Mockito.when(beneficioService.deletar(1))
                        .thenReturn(false);

                mockMvc.perform(MockMvcRequestBuilders.delete(BeneficioEnum.POR_ID.PATH, 1)
                                .contentType("application/json"))
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.mensagem")
                                .value("Benefício não encontrado"));
            }
        }

}
