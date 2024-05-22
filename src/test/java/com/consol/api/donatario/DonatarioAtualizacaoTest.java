package com.consol.api.donatario;

import com.consol.api.util.DonatarioEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Donatario - Atualizacao")
public class DonatarioAtualizacaoTest {

    public static final String URL = DonatarioEnum.BASE_URL.PATH;

    public static final String FILL_DATABASE_SCRIPT = "/data/fill_donatario.sql";

    @Nested
    @DisplayName("Dados Validos")
    public class Valido {

        @Autowired
        MockMvc mockMvc;
//        retorna 200 quando atualiza
        @Test
        @SqlGroup(
                @Sql(scripts = FILL_DATABASE_SCRIPT,
                        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        )
        @DisplayName("Deve retornar 200 e atualizar um donatario")
        public void teste1() throws Exception {
            var donatario = """
                    {
                        "nome": "Donatario 1",
                        "cpf": "1234567890",
                        "dataNascimento": "1990-01-01"
                    }""";

            mockMvc.perform(put(URL + "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(donatario))
                    .andExpect(status().isOk());
        }

    }

    @Nested
    @DisplayName("Dados Invalidos")
    public class Invalido {

        @Autowired
                private MockMvc mockMvc;

//        retorna 400 quando nome em branco

                @Test
                @DisplayName("Deve retornar erro 400 quando o nome estiver em branco")
                public void teste1() throws Exception {

                    var donatario = """
                            {
                                "nome": "",
                                "cpf": "45678901234",
                                "dataNascimento": "1988-12-05"
                            }""";

                    mockMvc.perform(put(URL + "4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(donatario))
                            .andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message")
                                    .value("Nome eh obrigatorio"));
                }

//        retorna 400 quando cpf em branco

                @Test
                @DisplayName("Deve retornar 400 quando o cpf estiver em branco")
                public void teste2() throws Exception {

                    var donatario = """
                    {
                        "nome": "Ana Santos",
                        "cpf": "",
                        "dataNascimento": "1988-12-05"
                    } """;

                    mockMvc.perform(put(URL + "4")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(donatario)).andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message")
                            .value("CPF eh obrigatorio"));
                }

        //        retorna 404 quando id nao encontrado

                @Test
                @SqlGroup(
                        @Sql(scripts = FILL_DATABASE_SCRIPT,
                                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
                )
                @DisplayName("Deve retornar 404 quando o Id não for encontrado")
                public void teste3() throws Exception {

                    var donatario = """
                    {
                        "nome": "Ana Santos",
                        "cpf": "45678901234",
                        "dataNascimento": "1988-12-05"
                    } """;

                    mockMvc.perform(put(URL + "400")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(donatario)).andExpect(status().isNotFound())
                            .andExpect(jsonPath("$.message")
                            .value("Donatario nao encontrado"));
                }


//        retorna 400 quando nao houver id

        @Test
        @DisplayName("Deve retornar 400 quando não houver id")
        public void teste4() throws Exception {

            var donatario = """
                    {
                        "nome": "Ana Santos",
                        "cpf": "45678901234",
                        "dataNascimento": "1988-12-05"
                    } """;

            mockMvc.perform(put(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(donatario)).andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message")
                            .value("Id invalido"));
        }


//        retorna 400 quando dataNascimento em branco

        @Test
        @DisplayName("Deve retornar 400 quando a data de nascimento estiver em branco")
        public void teste5() throws Exception {

            var donatario = """
                    {
                        "nome": "Ana Santos",
                        "cpf": "45678901234",
                        "dataNascimento": ""
                    } """;

            mockMvc.perform(put(URL + "4")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(donatario)).andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message")
                            .value("A data de nascimento é obrigatória"));
        }

//        Deve retornar 404 quando nao houver donatarios cadastrados

        @Test
        @DisplayName("Deve retornar 404 quando não houver donatarios cadastrados")
        public void teste6() throws Exception {

            var donatario = """
                    {
                        "nome": "Ana Santos",
                        "cpf": "45678901234",
                        "dataNascimento": "1988-12-05"
                    } """;

            mockMvc.perform(put(URL + "4")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(donatario)).andExpect(status().isNotFound())
                            .andExpect(jsonPath("$.message")
                            .value("Nao ha donatarios cadastrados"));
        }


//        retorna 400 quando cpf invalido

        @Test
        @DisplayName("Deve retornar 400 quando o cpf for inválido")
        public void teste7() throws Exception {

            var donatario = """
                    {
                        "nome": "Ana Santos",
                        "cpf": "4567890123",
                        "dataNascimento": "1988-12-05"
                    } """;

            mockMvc.perform(put(URL + "4")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(donatario)).andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message")
                            .value("CPF invalido"));
        }


//        retorna 400 quando dataNascimento invalida
@Test
@DisplayName("Deve retornar 400 quando a data de nascimento estiver invalida")
public void teste8() throws Exception {

    var donatario = """
                    {
                        "nome": "Ana Santos",
                        "cpf": "45678901234",
                        "dataNascimento": "1988-12-0"
                    } """;

    mockMvc.perform(put(URL + "4")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(donatario)).andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message")
                    .value("Data de nascimento invalida"));
}
    }
        }
