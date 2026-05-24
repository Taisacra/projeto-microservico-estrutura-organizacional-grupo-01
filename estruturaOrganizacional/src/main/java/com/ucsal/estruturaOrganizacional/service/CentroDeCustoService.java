package com.ucsal.estruturaOrganizacional.service;

import com.ucsal.estruturaOrganizacional.dto.CentroDeCustoDTO;
import com.ucsal.estruturaOrganizacional.model.CentroDeCusto;
import com.ucsal.estruturaOrganizacional.model.enuns.Especificacao;
import com.ucsal.estruturaOrganizacional.repository.CentroDeCustoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CentroDeCustoService {

    @Autowired
    CentroDeCustoRepository centroDeCustoRepository;

    public CentroDeCustoDTO listId(Long id){
        CentroDeCusto entity = centroDeCustoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de custo não encontrado"));
        return new CentroDeCustoDTO(entity);
    }
    public List<CentroDeCustoDTO> listarPorEspecificacao(Especificacao especificacao) {
        // 1. O repositório busca a lista de Entidades (CentroDeCusto) filtrada pelo Enum
        List<CentroDeCusto> list = centroDeCustoRepository.findByEspecificacao(especificacao);

        // 2. Transforma a lista de Entidades em uma lista de DTOs
        return list.stream()
                .map(centro -> new CentroDeCustoDTO(centro))
                .collect(Collectors.toList());
    }

    public List<CentroDeCustoDTO> listaTodos(){
        List<CentroDeCusto> list = centroDeCustoRepository.findAll();
        return list.stream()
                .map(centro -> new CentroDeCustoDTO(centro))
                .collect(Collectors.toList());
    }


    @Transactional
    public CentroDeCustoDTO salvar(CentroDeCustoDTO dto){
        // Valida as regras de negócio antes de converter para entidade
        validarResponsavel(dto);

        // Se for um cadastro novo, garante que inicia ativo (status = true)
        if (dto.getIdCentroDeCusto() == null) {
            dto.setStatus(true);
        }

        CentroDeCusto entity = dto.CentroDeCustoEntity();

        // CORRIGIDO: Validação dos campos obrigatórios da entidade ajustada
        if (entity.getNome() == null || entity.getNome().isBlank() ||
                entity.getIes() == null || entity.getTipoRepresentante() == null ||
                entity.getNomeRepresentante() == null || entity.getNomeRepresentante().isBlank()) {
            throw new RuntimeException("O nome do centro, a IES vinculada, o nome do responsável e o tipo de representante são obrigatórios.");
        }

        // Salva no banco e converte o resultado de volta para DTO
        CentroDeCusto entidadeSalva = centroDeCustoRepository.save(entity);
        return new CentroDeCustoDTO(entidadeSalva);
    }

    private void validarResponsavel(CentroDeCustoDTO dto) {
        Optional<CentroDeCusto> existente = centroDeCustoRepository.findByNomeAndTipoRepresentante(dto.getNome(), dto.getTipoRepresentante());

        if (existente.isPresent()){
            Long idExistente = existente.get().getIdCentroDeCusto();
            Long idNovo = dto.getIdCentroDeCusto();

            // Se for cadastro novo (idNovo == null) ou se estiver tentando atualizar um registro
            // mudando o nome para o nome de outro centro que já tem esse tipo de representante
            if (idNovo == null || !idNovo.equals(idExistente)){
                String cargo = dto.getTipoRepresentante().getRepresentante();
                throw new RuntimeException("Regra de Negócio: O centro '" + dto.getNome() + "' já possui um " + cargo + " vinculado.");
            }
        }
    }

    @Transactional
    public void delete(Long id){
        // Verifica se o Centro de Custo realmente existe antes de deletar
        CentroDeCusto entity = centroDeCustoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de custo não encontrado"));

        // CORRIGIDO: Como estamos usando microsserviços, a exclusão física é direta no repositório.
        centroDeCustoRepository.delete(entity);
    }

    @Transactional
    public void inativar(Long id) {
        CentroDeCusto entity = centroDeCustoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de custo não encontrado"));
        entity.setStatus(false);
        centroDeCustoRepository.save(entity);
    }

}
