package com.consol.api.repository;


import com.consol.api.entity.Beneficio;
import com.consol.api.entity.Donatario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficioRepository extends JpaRepository<Beneficio, Integer> {
    List<Beneficio> findByDonatario_id(Integer idDonatario);
    List<Beneficio> findByDonatario_Familia_id(Integer idFamilia);
}
