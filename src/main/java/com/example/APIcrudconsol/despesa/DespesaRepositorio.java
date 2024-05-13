package com.example.APIcrudconsol.despesa;

import com.example.APIcrudconsol.despesa.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface DespesaRepositorio extends JpaRepository<Despesa, Integer> {
    @Query("SELECT d FROM Despesa d")
    Collection<Despesa> encontrarTodos();
}
