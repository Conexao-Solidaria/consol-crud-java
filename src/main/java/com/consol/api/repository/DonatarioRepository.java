package com.consol.api.repository;

import com.consol.api.entity.Despesa;
import com.consol.api.entity.Donatario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface DonatarioRepository extends JpaRepository<Donatario, Integer> {
    @Query("SELECT u FROM Donatario u")
    Collection<Donatario> encontrarTodos();

    List<Donatario> findByNomeContainsIgnoreCase(String nome);
}
