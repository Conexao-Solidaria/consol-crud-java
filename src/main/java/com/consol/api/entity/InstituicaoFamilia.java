package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstituicaoFamilia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_familia_instituicao")
    private Integer idFamiliaInstituicao;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao fkInstituicao;

    @ManyToOne
    @JoinColumn(name = "fk_familia")
    private Familia fkFamilia;
}
