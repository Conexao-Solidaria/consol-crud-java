package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
//@Data
@Builder
public class Beneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficio")
    private Integer id;

    private String nome;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "fk_donatario")
    private Donatario donatario;
}
