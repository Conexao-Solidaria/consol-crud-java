package com.example.APIcrudconsol.instituicaoFamilia;

import com.example.APIcrudconsol.familia.Familia;
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
public class InstituicaoFamilia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFamiliaInstituicao;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao fkInstituicao;

    @ManyToOne
    @JoinColumn(name = "fk_familia")
    private Familia fkFamilia;
}
