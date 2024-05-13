package com.example.APIcrudconsol.registro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RegistroRepositorio extends JpaRepository<RegistroVisita, Integer> {
    @Query("SELECT r FROM RegistroVisita r")
    Collection<RegistroVisita> encontrarTodos();
}
