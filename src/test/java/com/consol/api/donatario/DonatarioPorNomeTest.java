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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Donatario - Listagem Por Nome")
public class DonatarioPorNomeTest {

    public static final String URL = DonatarioEnum.POR_NOME.PATH;
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
        @DisplayName("Deve retornar 200 e listar todos os donatarios que contem o nome")
        public void teste1() throws Exception {

            mockMvc.perform((get(URL).param("nome", "Silva")))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id").isNumber())
                    .andExpect(jsonPath("$[0].nome").value("Joao Silva"))
                    .andExpect(jsonPath("$[0].cpf").value("123456789"))
                    .andExpect(jsonPath("$[1].id").isNumber())
                    .andExpect(jsonPath("$[1].nome").value("Maria Silva"))
                    .andExpect(jsonPath("$[1].cpf").value("987654321"));
        }
    }

    @Nested
    @DisplayName("Dados Invalidos")
    public class Invalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("Deve retornar 204 quando nao houver donatarios cadastrados")
//        TODO: Implementar o teste para verificar se a lista de donatarios esta vazia
        public void teste1() throws Exception {
        }

        @Test
        @SqlGroup({
                @Sql(scripts = FILL_DATABASE_SCRIPT,
                        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        })
        @DisplayName("Deve retornar 204 quando nao encontrar o donatario")
//        TODO: Implementar o teste para verificar se a lista de donatarios esta vazia
        public void teste2() throws Exception {
        }
    }
}
