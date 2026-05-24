package com.ucsal.estruturaOrganizacional.dto;

import com.ucsal.estruturaOrganizacional.model.CentroDeCusto;
import com.ucsal.estruturaOrganizacional.model.IES;
import com.ucsal.estruturaOrganizacional.model.enuns.Especificacao;
import com.ucsal.estruturaOrganizacional.model.enuns.TipoRepresentante;

public class CentroDeCustoDTO {

    private Long idCentroDeCusto;
    private String nome;
    private TipoRepresentante tipoRepresentante;
    private String nomeRepresentante;
    private Especificacao especificacao;
    private Long idPessoa;
    private Long idIes;
    private Boolean status;

    public CentroDeCustoDTO() {}

    public CentroDeCustoDTO(Long idCentroDeCusto, String nome, TipoRepresentante tipoRepresentante, String nomeRepresentante, Especificacao especificacao, Long idPessoa, Long idIes) {
        this.idCentroDeCusto = idCentroDeCusto;
        this.nome = nome;
        this.tipoRepresentante = tipoRepresentante;
        this.nomeRepresentante = nomeRepresentante;
        this.especificacao = especificacao;
        this.idPessoa = idPessoa;
        this.idIes = idIes;
        this.status = true;

    }

    public CentroDeCustoDTO(CentroDeCusto centroDeCusto) {
        this.idCentroDeCusto = centroDeCusto.getIdCentroDeCusto();
        this.nome = centroDeCusto.getNome();
        this.tipoRepresentante = centroDeCusto.getTipoRepresentante();
        this.nomeRepresentante = centroDeCusto.getNomeRepresentante();
        this.especificacao = centroDeCusto.getEspecificacao();
        this.idPessoa = centroDeCusto.getIdPessoa();

        // Evita NullPointerException se a IES vier nula da entidade
        if (centroDeCusto.getIes() != null) {
            this.idIes = centroDeCusto.getIes().getIdIES();
        }
        this.status = centroDeCusto.getStatus();
    }

    public CentroDeCusto CentroDeCustoEntity() {
        CentroDeCusto centroDeCusto = new CentroDeCusto();

        centroDeCusto.setIdCentroDeCusto(this.idCentroDeCusto);
        centroDeCusto.setNome(this.nome);
        centroDeCusto.setTipoRepresentante(this.tipoRepresentante);
        centroDeCusto.setNomeRepresentante(this.nomeRepresentante);
        centroDeCusto.setEspecificacao(this.especificacao);
        centroDeCusto.setIdPessoa(this.idPessoa);

        // Para a IES, criamos um objeto temporário apenas com o ID que veio no DTO
        if (this.idIes != null) {
            IES iesAux = new IES();
            iesAux.setIdIES(this.idIes);
            centroDeCusto.setIes(iesAux);
        }
        centroDeCusto.setStatus(this.status);

        return centroDeCusto;
    }

    public Long getIdCentroDeCusto() { return idCentroDeCusto; }

    public void setIdCentroDeCusto(Long idCentroDeCusto) { this.idCentroDeCusto = idCentroDeCusto; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public TipoRepresentante getTipoRepresentante() { return tipoRepresentante; }

    public void setTipoRepresentante(TipoRepresentante tipoRepresentante) { this.tipoRepresentante = tipoRepresentante; }

    public String getNomeRepresentante() { return nomeRepresentante; }

    public void setNomeRepresentante(String nomeRepresentante) { this.nomeRepresentante = nomeRepresentante; }

    public Especificacao getEspecificacao() { return especificacao; }

    public void setEspecificacao(Especificacao especificacao) { this.especificacao = especificacao; }

    public Long getIdPessoa() { return idPessoa; }

    public void setIdPessoa(Long idPessoaRepresentante) { this.idPessoa = idPessoaRepresentante; }

    public Long getIdIes() { return idIes; }

    public void setIdIes(Long idIes) { this.idIes = idIes; }

    public Boolean getStatus() { return status; }

    public void setStatus(Boolean status) { this.status = status; }

}
