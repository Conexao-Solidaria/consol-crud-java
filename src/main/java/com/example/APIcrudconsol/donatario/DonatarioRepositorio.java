package com.example.APIcrudconsol.donatario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface DonatarioRepositorio extends JpaRepository<Donatario, Integer> {
    @Query("SELECT u FROM Donatario u")
    Collection<Donatario> encontrarTodos();
}
