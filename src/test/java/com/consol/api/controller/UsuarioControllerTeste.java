//package com.consol.api.controller;
//
//import com.consol.api.entity.Instituicao;
//import com.consol.api.entity.Usuario;
//import com.consol.api.service.UsuarioService;
//import com.consol.api.utils.UsuarioEnum;
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
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UsuarioController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@DisplayName("Controller - Usuario")
//public class UsuarioControllerTeste {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UsuarioService usuarioService;
//
//    @Nested
//    @DisplayName("GET /usuarios/{id}")
//    class PorIdInst {
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "Deve retornar 200 e listar todos os usuarios")
//        void deveListarTodosOsFamilias() throws Exception {
//            Instituicao instituicao = new Instituicao(1, "teste", "11111111", "12", "teste", null);
//
//            Usuario usuario = Usuario.builder()
//                    .id(1)
//                    .cpf("11111111111")
//                    .coordenador(true)
//                    .email("teste@gmail.com")
//                    .senha("123123")
//                    .nomeUsuario("jonas")
//                    .instituicao(instituicao)
//                    .build();
//
//            mockMvc.perform(MockMvcRequestBuilders.get(UsuarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.cpf").value("11111111111"))
//                    .andExpect(jsonPath("$.coordenador").value(1))
//                    .andExpect(jsonPath("$.email").value("teste@gmail.com"))
//                    .andExpect(jsonPath("$.nomeUsuario").value("jonas"))
//                    .andExpect(jsonPath("$.instituicao.nome").value("teste"))
//                    .andExpect(jsonPath("$.instituicao.cep").value("11111111"))
//                    .andExpect(jsonPath("$.instituicao.numeroImovel").value("12"))
//                    .andExpect(jsonPath("$.instituicao.descricao").value("teste"))
//                    .andExpect(jsonPath("$.instituicao.id").value(1))
//                    .andReturn();
//
//        }
//    }
//
//    @Nested
//    @DisplayName("POST /usuarios")
//    class Salvar {
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "deve retornar 201, a URI e salvar o usuario")
//        void deveRetornar201ESalvarOUsuario() throws Exception {
//            Instituicao instituicao = new Instituicao(1, "teste", "11111111", "12", "teste", null);
//
//            Usuario usuario = Usuario.builder()
//                    .id(1)
//                    .cpf("11111111111")
//                    .coordenador(true)
//                    .email("teste@gmail.com")
//                    .senha("123123")
//                    .nomeUsuario("jonas")
//                    .instituicao(instituicao)
//                    .build();
//
//            var content = """
//                    {
//                        "coordenador": 1,
//                        "nomeUsuario": "jonas",
//                        "cpf": "11111111111",
//                        "email": "teste@gmail.com",
//                        "senha": "123123",
//                        "instituicao_id": 1,
//                    }
//                    """;
//
//            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post
//                                    (UsuarioEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.cpf").value("11111111111"))
//                    .andExpect(jsonPath("$.coordenador").value(1))
//                    .andExpect(jsonPath("$.email").value("teste@gmail.com"))
//                    .andExpect(jsonPath("$.nomeUsuario").value("jonas"))
//                    .andExpect(jsonPath("$.instituicao.nome").value("teste"))
//                    .andExpect(jsonPath("$.instituicao.cep").value("11111111"))
//                    .andExpect(jsonPath("$.instituicao.numeroImovel").value("12"))
//                    .andExpect(jsonPath("$.instituicao.descricao").value("teste"))
//                    .andExpect(jsonPath("$.instituicao.id").value(1))
//                    .andReturn();
//
//            assertTrue(result.getResponse().getHeader("Location")
//                    .contains(UsuarioEnum.BASE_URL.PATH + "/1"));
//        }
//
//        @Test
//        @DisplayName("Se o cpf for nulo ou vazio: " +
//                "deve retornar 400")
//        void deveRetornarBadRequestCpf() throws Exception {
//            var content = """
//                    {
//                        "coordenador": 1,
//                        "nomeUsuario": "jonas",
//                        "cpf": "",
//                        "email": "teste@gmail.com",
//                        "senha": "123123",
//                        "instituicao_id": 1,
//                    }
//                    """;
//
//
//            mockMvc.perform(MockMvcRequestBuilders.post(UsuarioEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o email for nulo ou vazio: " +
//                "deve retornar 400")
//        void deveRetornarBadRequestEmail() throws Exception {
//            var content = """
//                    {
//                        "coordenador": 1,
//                        "nomeUsuario": "jonas",
//                        "cpf": "11111111111",
//                        "email": "",
//                        "senha": "123123",
//                        "instituicao_id": 1,
//                    }
//                    """;
//
//
//            mockMvc.perform(MockMvcRequestBuilders.post(UsuarioEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se o nomeUsuario for nulo ou vazio: " +
//                "deve retornar 400")
//        void deveRetornarBadRequest() throws Exception {
//            var content = """
//                    {
//                        "coordenador": 1,
//                        "nomeUsuario": "",
//                        "cpf": "11111111111",
//                        "email": "teste@gmail.com",
//                        "senha": "123123",
//                        "instituicao_id": 1,
//                    }
//                    """;
//
//
//            mockMvc.perform(MockMvcRequestBuilders.post(UsuarioEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest());
//        }
//
//        @Test
//        @DisplayName("Se a senha for nulo ou vazio: " +
//                "deve retornar 400")
//        void deveRetornarBadRequestSenha() throws Exception {
//            var content = """
//                    {
//                        "coordenador": 1,
//                        "nomeUsuario": "jonas",
//                        "cpf": "11111111111",
//                        "email": "teste@gmail.com",
//                        "senha": "",
//                        "instituicao_id": 1,
//                    }
//                    """;
//
//
//            mockMvc.perform(MockMvcRequestBuilders.post(UsuarioEnum.BASE_URL.PATH)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isBadRequest());
//        }
//
//
//    }
//
//    @Nested
//    @DisplayName("PATCH /usuarios/{id}")
//    class AlterarValor {
//        @Test
//        @DisplayName("Se os dados estiverem corretos: " +
//                "Deve retornar 200 e atualizar o usuario")
//        void deveAtualizarOUsuario() throws Exception {
//
//            Usuario usuario = Usuario.builder()
//                    .id(1)
//                    .cpf("11111111111")
//                    .coordenador(true)
//                    .email("teste@gmail.com")
//                    .senha("123123")
//                    .nomeUsuario("jonas")
//                    .instituicao(null)
//                    .build();
//
//            var content = """
//                    {
//                        "coordenador": 1,
//                        "nomeUsuario": "jonas",
//                        "cpf": "11111111111",
//                        "email": "teste@gmail.com",
//                        "senha": "123123",
//                        "instituicao_id": 1,
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.put(UsuarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.cpf").value("11111111111"))
//                    .andExpect(jsonPath("$.coordenador").value(1))
//                    .andExpect(jsonPath("$.email").value("teste@gmail.com"))
//                    .andExpect(jsonPath("$.nomeUsuario").value("jonas"));
//        }
//
//        @Test
//        @DisplayName("Se o id nao existir: " +
//                "Deve retornar 404 e uma mensagem de erro")
//        void deveRetornarErroIdNaoEncontrado() throws Exception {
//
//            var content = """
//                    {
//                        "coordenador": 1,
//                        "nomeUsuario": "jonas",
//                        "cpf": "11111111111",
//                        "email": "teste@gmail.com",
//                        "senha": "123123",
//                        "instituicao_id": 1,
//                    }
//                    """;
//
//            mockMvc.perform(MockMvcRequestBuilders.put(UsuarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json")
//                            .content(content))
//                    .andExpect(status().isNotFound())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("Usuario não encontrado"));
//        }
//    }
//
//    @Nested
//    @DisplayName("DELETE /usurios/{id}")
//    class Deletar {
//
//        @Test
//        @DisplayName("Se o usuario existir: " +
//                "Deve retornar 204 e deletar o usuario")
//        void deveDeletarUsuario() throws Exception {
//
//            mockMvc.perform(MockMvcRequestBuilders.delete(UsuarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isNoContent());
//        }
//
//        @Test
//        @DisplayName("Se a usuario não existir: " +
//                "Deve retornar 404 e uma mensagem de erro")
//        void deveRetornarErroUsuarioNaoEncontrado() throws Exception {
//            mockMvc.perform(MockMvcRequestBuilders.delete(UsuarioEnum.POR_ID.PATH, 1)
//                            .contentType("application/json"))
//                    .andExpect(status().isNotFound())
//                    .andExpect(jsonPath("$.mensagem")
//                            .value("Usuario não encontrado"));
//        }
//    }
//}
