package com.ucsal.estruturaOrganizacional.dto;

import com.ucsal.estruturaOrganizacional.model.IES;

public class IesDTO {

    private Long idIES;
    private String nomeInstituicao;
    private String sigla;

    public IesDTO() {}

    public IesDTO(Long idIES, String nomeInstituicao, String sigla) {
        this.idIES = idIES;
        this.nomeInstituicao = nomeInstituicao;
        this.sigla = sigla;
    }

    // Construtor: Entity para DTO (Igual ao seu modelo de Cliente)
    public IesDTO(IES ies) {
        this.idIES = ies.getIdIES();
        this.nomeInstituicao = ies.getNomeInstituicao();
        this.sigla = ies.getSigla();
    }

    public IES IesEntity() {
        IES ies = new IES();

        ies.setIdIES(this.idIES);
        ies.setNomeInstituicao(this.nomeInstituicao);
        ies.setSigla(this.sigla);

        return ies;
    }

    public Long getIdIES() { return idIES; }

    public void setIdIES(Long idIES) { this.idIES = idIES; }

    public String getNomeInstituicao() { return nomeInstituicao; }

    public void setNomeInstituicao(String nomeInstituicao) { this.nomeInstituicao = nomeInstituicao; }

    public String getSigla() { return sigla; }

    public void setSigla(String sigla) { this.sigla = sigla; }
}
