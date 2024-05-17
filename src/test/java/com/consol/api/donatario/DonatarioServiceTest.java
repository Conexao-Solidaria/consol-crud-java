package com.consol.api.donatario;

import com.consol.api.entity.Donatario;
import com.consol.api.repository.DonatarioRepository;
import com.consol.api.service.DonatarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DonatarioServiceTest {

    @InjectMocks
    private DonatarioService donatarioService;

    @Mock
    private DonatarioRepository donatarioRepository;

    @Test
    void listar() {
//        GIVEN
        List<Donatario> lista = Collections.emptyList();

//        WHEN
//        Mockito.when(donatarioRepository.encontrarTodos()).thenReturn(lista);
        Mockito.when(donatarioService.listar()).thenReturn(lista);

//        THEN
        List<Donatario> donatarios = donatarioService.listar();

//        ASSERT
        assertTrue(donatarios.isEmpty());
    }
}
