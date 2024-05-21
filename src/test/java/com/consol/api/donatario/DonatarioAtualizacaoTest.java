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
                        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
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
                                "cpf": "12345678901",
                                "dataNascimento": "1990-01-01"
                            }""";

                    mockMvc.perform(put(URL + "3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(donatario))
                            .andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message")
                                    .value("Nome é obrigatório"));
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

                    mockMvc.perform(put(URL + "3")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(donatario)).andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message")
                            .value("CPF é obrigatório"));
                }

//        retorna 404 quando id nao encontrado

//        retorna 400 quando nao houver id

//        retorna 400 quando dataNascimento em branco

//        Deve retornar 404 quando nao houver donatarios cadastrados

//        retorna 400 quando cpf invalido
//        retorna 400 quando dataNascimento invalida
    }
}
