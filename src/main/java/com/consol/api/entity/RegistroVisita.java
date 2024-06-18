package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
//@Data
@Builder
public class RegistroVisita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_visita")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_donatario")
    private Donatario donatario;

    @CreationTimestamp
    private LocalDate dataVisita;

    private String descricao;
}
