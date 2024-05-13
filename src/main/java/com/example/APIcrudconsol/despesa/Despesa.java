package com.example.APIcrudconsol.despesa;

import com.example.APIcrudconsol.familia.Familia;
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
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDespesa;

    @ManyToOne
    @JoinColumn(name = "fk_familia")
    private Familia familia;

    private String tipo;

    private Double gasto;

}
