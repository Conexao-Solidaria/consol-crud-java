package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despesa")
    private Integer id;

    private String tipo;

    private Double gasto;

    @ManyToOne
    @JoinColumn(name = "fk_familia")
    private Familia familia;
}
