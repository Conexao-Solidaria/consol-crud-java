package com.example.APIcrudconsol.necessidade;

import com.consol.api.entity.Instituicao;
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
public class Necessidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNecessidade;

    private String tipo;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;

}
