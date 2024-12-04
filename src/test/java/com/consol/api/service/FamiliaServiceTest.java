//package com.consol.api.service;
//
//import com.consol.api.entity.Despesa;
//import com.consol.api.entity.Titular;
//import com.consol.api.entity.Familia;
//import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
//import com.consol.api.repository.FamiliaRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("Service - Familia")
//public class FamiliaServiceTest {
//
//    @InjectMocks
//    private FamiliaService service;
//
//    @Mock
//    private FamiliaRepository repository;
//
//    @Nested
//    @DisplayName("Listar")
//    class Listar {
//
//        @Test
//        @DisplayName("Se o banco estiver vazio:" +
//                "Deve retornar uma lista vazia")
//        void deveRetornarListaVazia() {
//            List<Familia> familias = Collections.emptyList();
//
//            Mockito.when(repository.findAll()).thenReturn(familias);
//
//            List<Familia> familiasRetorno = service.listar();
//
//            assertEquals(familias.size(), familiasRetorno.size());
//
//            Mockito.verify(repository, Mockito.times(1)).findAll();
//        }
//
//        @Test
//        @DisplayName("Se o banco tiver 3 familias:" +
//                "Deve retornar uma lista com 3 familias")
//        void deveRetornarListaComTresFamilias() {
//            List<Familia> familias = List.of(
////                    new Familia(1, "Familia 1", "123456", 1, 1000.0, new ArrayList<Donatario>(), new ArrayList<Despesa>())
//                    Familia.builder()
//                            .id(1)
//                            .nome("Familia 1")
//                            .cep("123456")
//                            .numeroCasa(1)
//                            .renda(1000.0)
//                            .titulars(new ArrayList<Titular>())
//                            .despesas(new ArrayList<Despesa>())
//                            .build(),
//                    Familia.builder()
//                            .id(2)
//                            .nome("Familia 2")
//                            .cep("123456")
//                            .numeroCasa(2)
//                            .renda(2000.0)
//                            .titulars(new ArrayList<Titular>())
//                            .despesas(new ArrayList<Despesa>())
//                            .build(),
//                    Familia.builder()
//                            .id(3)
//                            .nome("Familia 3")
//                            .cep("123456")
//                            .numeroCasa(3)
//                            .renda(3000.0)
//                            .titulars(new ArrayList<Titular>())
//                            .despesas(new ArrayList<Despesa>())
//                            .build()
//            );
//
//            Mockito.when(repository.findAll()).thenReturn(familias);
//
//            List<Familia> familiasRetorno = service.listar();
//
//            assertEquals(familias.size(), familiasRetorno.size());
//
//            Mockito.verify(repository, Mockito.times(1)).findAll();
//        }
//    }
//
//    @Nested
//    @DisplayName("Por Id")
//    class PorId {
//
//        @Test
//        @DisplayName("Se a familia existir:" +
//                "Deve retornar a familia")
//        void deveRetornarFamilia() {
//            Familia familia = Familia.builder()
//                    .id(1)
//                    .nome("Familia 1")
//                    .cep("123456")
//                    .numeroCasa(1)
//                    .renda(1000.0)
//                    .titulars(new ArrayList<Titular>())
//                    .despesas(new ArrayList<Despesa>())
//                    .build();
//
//            Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.of(familia));
//
//            Familia familiaRetorno = service.porId(1);
//
//            assertEquals(familia.getId(), familiaRetorno.getId());
//            assertEquals(familia.getNome(), familiaRetorno.getNome());
//            assertEquals(familia.getCep(), familiaRetorno.getCep());
//            assertEquals(familia.getNumeroCasa(), familiaRetorno.getNumeroCasa());
//            assertEquals(familia.getRenda(), familiaRetorno.getRenda());
//            assertEquals(familia.getTitulars(), familiaRetorno.getTitulars());
//            assertEquals(familia.getDespesas(), familiaRetorno.getDespesas());
//
//            Mockito.verify(repository, Mockito.times(1)).findById(1);
//        }
//
//        @Test
//        @DisplayName("Se a familia não existir:" +
//                "Deve retornar uma exception de entidade não encontrada")
//        void deveRetornarExcecao() {
//            int idBusca = 1;
//
//            Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.empty());
//
//            EntidadeNaoEncontradaException exception =
//                    assertThrows(EntidadeNaoEncontradaException.class,
//                    () -> service.porId(idBusca));
//
//            assertEquals("Familia não encontrada!", exception.getLocalizedMessage());
//
//            Mockito.verify(repository, Mockito.times(1)).findById(1);
//        }
//    }
//
//    @Nested
//    @DisplayName("Atualizar")
//    class Atualizar {
//
//        @Test
//        @DisplayName("Se a familia existir:" +
//                "Deve retornar a familia atualizada")
//        void deveRetornarFamiliaAtualizada() {
//            Familia familia = Familia.builder()
//                    .id(1)
//                    .nome("Familia 1")
//                    .cep("123456")
//                    .numeroCasa(1)
//                    .renda(1000.0)
//                    .titulars(new ArrayList<Titular>())
//                    .despesas(new ArrayList<Despesa>())
//                    .build();
//
//            Familia familiaAtualizada = Familia.builder()
//                    .id(1)
//                    .nome("Familia 1")
//                    .cep("654321")
//                    .numeroCasa(2)
//                    .renda(1000.0)
//                    .titulars(new ArrayList<Titular>())
//                    .despesas(new ArrayList<Despesa>())
//                    .build();
//
//            Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.of(familia));
//
//            Familia familiaRetorno = service.atualizar(1, familiaAtualizada);
//
//            assertEquals(familiaAtualizada.getId(), familiaRetorno.getId());
//            assertEquals(familiaAtualizada.getNome(), familiaRetorno.getNome());
//            assertEquals(familiaAtualizada.getCep(), familiaRetorno.getCep());
//            assertEquals(familiaAtualizada.getNumeroCasa(), familiaRetorno.getNumeroCasa());
//            assertEquals(familiaAtualizada.getRenda(), familiaRetorno.getRenda());
//            assertEquals(familiaAtualizada.getTitulars(), familiaRetorno.getTitulars());
//            assertEquals(familiaAtualizada.getDespesas(), familiaRetorno.getDespesas());
//
//            Mockito.verify(repository, Mockito.times(1)).findById(1);
//        }
//
//        @Test
//        @DisplayName("Se a familia não existir:" +
//                "Deve retornar uma exception de entidade não encontrada")
//        void deveRetornarExcecao() {
//            Familia familiaAtualizada = Familia.builder()
//                    .id(1)
//                    .nome("Familia 1")
//                    .cep("654321")
//                    .numeroCasa(2)
//                    .renda(1000.0)
//                    .titulars(new ArrayList<Titular>())
//                    .despesas(new ArrayList<Despesa>())
//                    .build();
//
//            Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.empty());
//
//            EntidadeNaoEncontradaException exception =
//                    assertThrows(EntidadeNaoEncontradaException.class,
//                    () -> service.atualizar(1, familiaAtualizada));
//
//            assertEquals("Familia não encontrada!", exception.getLocalizedMessage());
//
//            Mockito.verify(repository, Mockito.times(1)).findById(1);
//        }
//
//        @Test
//        @DisplayName("Se outros dados alem do cep e endereco forem alterados:" +
//                "Deve retornar uma exception de argumento invalido")
//        void deveRetornarExcecaoArgumentoInvalido() {
//            Familia familia = Familia.builder()
//                    .id(1)
//                    .nome("Familia 1")
//                    .cep("123456")
//                    .numeroCasa(1)
//                    .renda(1000.0)
//                    .titulars(new ArrayList<Titular>())
//                    .despesas(new ArrayList<Despesa>())
//                    .build();
//
//            Familia familiaAtualizada = Familia.builder()
//                    .id(1)
//                    .nome("Familia 2")
//                    .cep("654321")
//                    .numeroCasa(2)
//                    .renda(2000.0)
//                    .titulars(new ArrayList<Titular>())
//                    .despesas(new ArrayList<Despesa>())
//                    .build();
//
//            Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.of(familia));
//
//            IllegalArgumentException exception =
//                    assertThrows(IllegalArgumentException.class,
//                    () -> service.atualizar(1, familiaAtualizada));
//
//            assertEquals("Argumento(s) inválido(s)", exception.getLocalizedMessage());
//
//            Mockito.verify(repository, Mockito.times(1)).findById(1);
//        }
//    }
//
//    @Nested
//    @DisplayName("Salvar")
//    class Salvar {
//
//        @Test
//        @DisplayName("Deve retornar a familia salva")
//        void deveRetornarFamiliaSalva() {
//            Familia familia = Familia.builder()
//                    .id(1)
//                    .nome("Familia 1")
//                    .cep("123456")
//                    .numeroCasa(1)
//                    .renda(1000.0)
//                    .titulars(new ArrayList<Titular>())
//                    .despesas(new ArrayList<Despesa>())
//                    .build();
//
//            Mockito.when(repository.save(familia)).thenReturn(familia);
//
//            Familia familiaRetorno = service.salvar(familia);
//
//            assertEquals(familia.getId(), familiaRetorno.getId());
//            assertEquals(familia.getNome(), familiaRetorno.getNome());
//            assertEquals(familia.getCep(), familiaRetorno.getCep());
//            assertEquals(familia.getNumeroCasa(), familiaRetorno.getNumeroCasa());
//            assertEquals(familia.getRenda(), familiaRetorno.getRenda());
//            assertEquals(familia.getTitulars(), familiaRetorno.getTitulars());
//            assertEquals(familia.getDespesas(), familiaRetorno.getDespesas());
//
//            Mockito.verify(repository, Mockito.times(1)).save(familia);
//        }
//    }
//
//    @Nested
//    @DisplayName("Deletar")
//    class Deletar {
//
//        @Test
//        @DisplayName("Se a familia existir:" +
//                "Deve deletar a familia")
//        void deveDeletarFamilia() {
//            Familia familia = Familia.builder()
//                    .id(1)
//                    .nome("Familia 1")
//                    .cep("123456")
//                    .numeroCasa(1)
//                    .renda(1000.0)
//                    .titulars(new ArrayList<Titular>())
//                    .despesas(new ArrayList<Despesa>())
//                    .build();
//
//            Mockito.when(repository.existsById(1)).thenReturn(true);
//
//            service.deletar(1);
//
//            Mockito.verify(repository, Mockito.times(1)).deleteById(1);
//        }
//
//        @Test
//        @DisplayName("Se a familia não existir:" +
//                "Deve retornar uma exception de entidade não encontrada")
//        void deveRetornarExcecao() {
//            Mockito.when(repository.existsById(1)).thenReturn(false);
//
//            EntidadeNaoEncontradaException exception =
//                    assertThrows(EntidadeNaoEncontradaException.class,
//                    () -> service.deletar(1));
//
//            assertEquals("Familia não encontrada!", exception.getLocalizedMessage());
//
//            Mockito.verify(repository, Mockito.times(1)).existsById(1);
//        }
//    }
//}
