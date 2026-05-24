package com.ucsal.estruturaOrganizacional.model.enuns;


public enum Especificacao {

    UNIDADE(1L, "Unidade"),
    ESCOLA(2L, "Escola");

    private Long id;
    private final Long idEspecificacao;
    private final String especificacao;

    private Especificacao(Long idEspecificacao, String especificacao) {
        this.idEspecificacao = idEspecificacao;
        this.especificacao = especificacao;
    }

    public Long getIdEspecificacao() {return idEspecificacao;}

    public String getEspecificacao() {return especificacao;}

}
