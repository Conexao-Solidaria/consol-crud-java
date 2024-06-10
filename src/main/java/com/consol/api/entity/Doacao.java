package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double peso;

    private String descricao;

    private LocalDate dataDoacao;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;

    @JoinColumn(name = "fk_donatario")
    @ManyToOne
    private Donatario donatario;

}
