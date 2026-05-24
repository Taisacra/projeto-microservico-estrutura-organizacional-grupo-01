package com.ucsal.estruturaOrganizacional.model.enuns;

public enum TipoRepresentante {
    COORDENADOR (1L, "Coordenador"),
    REPRESENTANTE  (2L, "Representante");

    private final Long idRepresentante;
    private final String representante;

    private TipoRepresentante(Long idRepresentante, String representante) {
        this.idRepresentante = idRepresentante;
        this.representante = representante;
    }

    public Long getIdRepresentante() {return idRepresentante;}

    public String getRepresentante() {return representante;}
}
