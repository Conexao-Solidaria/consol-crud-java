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
    @Column(name = "id_doacao")
    private Integer id;

    private Integer statusDoacao;

    private LocalDate dataDoacao;

    private String descricao;

    private Integer flagDoacaoEntregue;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;

    @ManyToOne
    @JoinColumn(name = "fk_titular")
    private Titular titular;

}
