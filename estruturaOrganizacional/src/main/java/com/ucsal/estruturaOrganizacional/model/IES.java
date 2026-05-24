package com.ucsal.estruturaOrganizacional.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_ies")
public class IES {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIES;

    @Column(nullable = false, unique = true)
    private String nomeInstituicao;

    @Column(nullable = false, unique = true)
    private String sigla;

    @OneToMany(mappedBy = "ies", fetch = FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CentroDeCusto> centrosCusto;
}
