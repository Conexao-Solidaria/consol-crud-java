package com.example.APIcrudconsol.doacoes;

import com.example.APIcrudconsol.instituicao.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacoesRepository extends JpaRepository<Doacoes, Integer> {

}
