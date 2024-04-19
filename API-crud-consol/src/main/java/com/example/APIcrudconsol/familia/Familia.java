package com.example.APIcrudconsol.familia;

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
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFamilia;

    private String nome;

    private String cep;

    private Integer numeroCasa;

    private Double renda;
}
