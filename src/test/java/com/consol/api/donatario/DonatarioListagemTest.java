package com.consol.api.donatario;

import com.consol.api.util.DonatarioEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Donatario - Listagem Geral")
public class DonatarioListagemTest {

    public static final String URL = DonatarioEnum.BASE_URL.PATH;

    public static final String FILL_DATABASE_SCRIPT = "/data/fill_donatario.sql";

    @Nested
    @DisplayName("Dados Validos")
    public class Valido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("Deve listar todos os donatarios com sucesso e retornar 200")
        public void teste1() throws Exception {
            mockMvc.perform(get(URL))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id").isNumber())
                    .andExpect(jsonPath("$[0].nome").value("Donatario 1"))
                    .andExpect(jsonPath("$[0].cpf").value("12345678901"))
                    .andExpect(jsonPath("$[1].id").isNumber())
                    .andExpect(jsonPath("$[1].nome").value("Donatario 2"))
                    .andExpect(jsonPath("$[1].cpf").value("12345678902"));
        }
    }

    @Nested
    @DisplayName("Dados Invalidos")
    public class Invalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("Deve retornar 204 quando nao houver donatarios na base de dados")
        public void teste1() throws Exception {
            mockMvc.perform(get(URL))
                    .andExpect(status().isNoContent())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$", hasSize(0)));
        }
    }
}
