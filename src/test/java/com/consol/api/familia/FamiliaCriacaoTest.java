package com.consol.api.familia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Familia - Criacao")
public class FamiliaCriacaoTest {

    public static final String URL = "/familias";

    public static final String FILL_DATABASE_SCRIPT = "/data/fill_familia.sql";

    @Nested
    @DisplayName("Dados Validos")
    public class Valido {

        @Autowired
        MockMvc mockMvc;

        @Test
        @DisplayName("Deve retornar 201 e criar uma familia")
        public void teste1() {
            // TODO
        }
    }

    @Nested
    @DisplayName("Dados Invalidos")
    public class Invalido {

        @Autowired
        MockMvc mockMvc;

        @Test
        @DisplayName("Deve retornar 400 caso cep vazio")
        public void teste1() {
            // TODO
        }

        @Test
        @DisplayName("Deve retornar 400 caso nome vazio")
        public void teste2() {
            // TODO
        }

        @Test
        @DisplayName("Deve retornar 400 caso numero da casa vazio")
        public void teste3() {
            // TODO
        }

        @Test
        @DisplayName("Deve retornar 400 caso renda vazia")
        public void teste4() {
            // TODO
        }

        @Test
        @DisplayName("Deve retornar 400 caso cep invalido")
        public void teste5() {
            // TODO
        }
    }
}
