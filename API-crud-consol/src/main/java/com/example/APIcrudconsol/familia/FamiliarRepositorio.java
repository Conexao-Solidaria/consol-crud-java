package com.example.APIcrudconsol.familia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface FamiliarRepositorio extends JpaRepository<Familia, Integer> {
    @Query("SELECT f FROM Familia f")
    Collection<Familia> encontrarTodos();
}
