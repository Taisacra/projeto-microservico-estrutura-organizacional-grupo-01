package com.ucsal.estruturaOrganizacional.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ucsal.estruturaOrganizacional.model.enuns.Especificacao;
import com.ucsal.estruturaOrganizacional.model.enuns.TipoRepresentante;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@Table(name = "tb_centrocusto")
public class CentroDeCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCentroDeCusto;

    @Column(nullable = false)
    private String nome;

    @Enumerated (EnumType.STRING)
    @Column(name = "tipoRepresentante",nullable = false)
    private TipoRepresentante tipoRepresentante;

    @Column(nullable = false)
    private String nomeRepresentante;

    @Enumerated (EnumType.STRING)
    @Column(name = "especificacao", nullable = false)
    private Especificacao especificacao;

    @NotNull
    @Column(name = "id_pessoa_FK", nullable = false)
    private Long idPessoa;

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_ies_FK", nullable = false)
    private IES ies;

    @NotNull
    @Column(nullable = false)
    private Boolean status = true;

}
