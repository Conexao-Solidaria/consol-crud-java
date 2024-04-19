package com.example.APIcrudconsol.beneficio;

import com.example.APIcrudconsol.donatario.Donatario;
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
    private Integer idBeneficio;

    private String nome;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "fk_donatario")
    private Donatario donatario;
}
