package com.example.APIcrudconsol.beneficio;


import com.example.APIcrudconsol.beneficio.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface BeneficioRepositorio extends JpaRepository<Beneficio, Integer> {
}
