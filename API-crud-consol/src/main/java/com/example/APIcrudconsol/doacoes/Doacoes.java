package com.example.APIcrudconsol.doacoes;

import com.example.APIcrudconsol.donatario.Donatario;
import com.example.APIcrudconsol.instituicao.Instituicao;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Doacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoacoes;

    private Double peso;

    private String descricao;

    private char statusDoacao;

    private LocalDate dataDoacao;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;

    @JoinColumn(name = "fk_donatario")
    @ManyToOne
    private Donatario donatario;

}
