package com.consol.api.donatario;

import com.consol.api.util.DonatarioEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Donatario - Listagem Por Id")
public class DonatarioPorIdTest {

    public static final String URL = DonatarioEnum.POR_ID.PATH;

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
        @DisplayName("Deve retornar 200 e listar o donatario por id")
        public void teste1() throws Exception {
            mockMvc.perform(get(URL + "1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.nome").value("Joao Silva"))
                    .andExpect(jsonPath("$.cpf").value("123456789"));
        }
    }

    @Nested
    @DisplayName("Dados Invalidos")
    public class Invalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("Deve retornar 404 quando nao houver donatarios cadastrados")
        public void teste1() throws Exception {
            mockMvc.perform(get(URL + "Joao"))
                    .andExpect(status().isNotFound());
        }

        @Test
        @SqlGroup({
                @Sql(scripts = FILL_DATABASE_SCRIPT,
                        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        })
        @DisplayName("Deve retornar 404 quando nao encontrar o donatario")
        public void teste2() throws Exception {
            mockMvc.perform(get(URL + "300"))
                    .andExpect(status().isNotFound());
        }
    }
}
