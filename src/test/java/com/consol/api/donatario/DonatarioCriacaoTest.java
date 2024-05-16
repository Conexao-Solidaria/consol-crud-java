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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Donatario - Criacao")
public class DonatarioCriacaoTest {

    public static final String URL = DonatarioEnum.BASE_URL.PATH;

    public static final String FILL_DATABASE_SCRIPT = "/data/fill_donatario.sql";

    @Nested
    @DisplayName("Dados Validos")
    public class Valido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @SqlGroup({
                @Sql(scripts = FILL_DATABASE_SCRIPT,
                        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        })
        @DisplayName("Deve criar um donatario com sucesso e retornar 201")
        public void teste1() throws Exception {

            var donatario = """
                    {
                        "nome": "Donatario 1",
                        "cpf": "12345678901",
                        "dataNascimento": "1990-01-01"
                    }""";

            mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donatario))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.nome").value("Donatario 1"))
                    .andExpect(jsonPath("$.cpf").value("12345678901"))
                    .andExpect(jsonPath("$.dataNascimento")
                            .value("1990-01-01"));
        }
    }

    @Nested
    @DisplayName("Dados Invalidos")
    public class Invalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("Deve retornar erro 400 ao tentar criar um donatario com nome em branco")
        public void teste1() throws Exception {

            var donatario = """
                    {
                        "nome": "",
                        "cpf": "12345678901",
                        "dataNascimento": "1990-01-01"
                    }""";

            mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donatario))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message")
                            .value("Nome é obrigatório"));
        }

        @Test
        @DisplayName("Deve retornar erro 400 ao tentar criar um donatario com cpf em branco")
        public void teste2() throws Exception {

            var donatario = """
                    {
                        "nome": "Donatario 1",
                        "cpf": "",
                        "dataNascimento": "1990-01-01"
                    }""";

            mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donatario))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message")
                            .value("CPF é obrigatório"));
        }

        @Test
        @DisplayName("Deve retornar erro 400 ao tentar criar um donatario com data de " +
                "nascimento em branco")
        public void teste3() throws Exception {

            var donatario = """
                    {
                        "nome": "Donatario 1",
                        "cpf": "12345678901",
                        "dataNascimento": ""
                    }""";

            mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donatario))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message")
                            .value("Data de nascimento é obrigatória"));
        }

        @Test
        @DisplayName("Deve retornar erro 400 ao tentar criar um donatario com cpf invalido")
        public void teste4() throws Exception {

            var donatario = """
                    {
                        "nome": "Donatario 1",
                        "cpf": "1234567890",
                        "dataNascimento": "1990-01-01"
                    }""";

            mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donatario))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value("CPF inválido"));
        }

        @Test
        @DisplayName("Deve retornar erro 400 ao tentar criar um donatario com data " +
                "de nascimento invalida")
        public void teste5() throws Exception {

            var donatario = """
                    {
                        "nome": "Donatario 1",
                        "cpf": "12345678901",
                        "dataNascimento": "1990-01-32"
                    }""";

            mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donatario))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message")
                            .value("Data de nascimento inválida"));
        }
    }
}
