package com.consol.api.service;

import com.consol.api.repository.DonatarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Service - Donatario")
public class DonatarioServiceTest {

    @InjectMocks
    private DonatarioService service;

    @Mock
    private DonatarioRepository repository;

    @Nested
    @DisplayName("Salvar")
    class Salvar {
//        TODO
    }

    @Nested
    @DisplayName("Listar")
    class Listar {
//        TODO
    }

    @Nested
    @DisplayName("Por Id")
    class PorId {
//        TODO
    }

    @Nested
    @DisplayName("Por Nome")
    class PorNome {
//        TODO
    }

    @Nested
    @DisplayName("Atualizar")
    class Atualizar {
//        TODO
    }

    @Nested
    @DisplayName("Deletar")
    class Deletar {
//        TODO
    }
}