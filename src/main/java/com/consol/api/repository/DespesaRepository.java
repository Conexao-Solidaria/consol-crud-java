package com.consol.api.repository;

import com.consol.api.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {
    @Query("SELECT d FROM Despesa d")
    Collection<Despesa> encontrarTodos();
}
