package com.ucsal.estruturaOrganizacional.repository;

import com.ucsal.estruturaOrganizacional.model.CentroDeCusto;
import com.ucsal.estruturaOrganizacional.model.enuns.Especificacao;
import com.ucsal.estruturaOrganizacional.model.enuns.TipoRepresentante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {
    //listar os centro de custos a depender da especificacao (se é escola ou unidade)
    List<CentroDeCusto> findByEspecificacao(Especificacao especificacao);

    //listar centros ativos
    List<CentroDeCusto> findByStatusTrue();

    //para a verificacao se tem coordenardor RN001
    Optional<CentroDeCusto> findByNomeAndTipoRepresentante(String Nome, TipoRepresentante tipo);
}
