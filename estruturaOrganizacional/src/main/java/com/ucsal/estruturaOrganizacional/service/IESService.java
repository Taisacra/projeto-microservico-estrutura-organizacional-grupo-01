package com.ucsal.estruturaOrganizacional.service;

import com.ucsal.estruturaOrganizacional.dto.IesDTO;
import com.ucsal.estruturaOrganizacional.model.CentroDeCusto;
import com.ucsal.estruturaOrganizacional.model.IES;
import com.ucsal.estruturaOrganizacional.repository.IESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IESService {

    @Autowired
    private IESRepository iesRepository;

    public List<IesDTO> listarTodas(){
        List<IES> list = iesRepository.findAll();
        return list.stream()
                .map(ies -> new IesDTO(ies))
                .collect(Collectors.toList());
    }

    /*public IesDTO listarId(Long idIes){
        IES entity = iesRepository.findById(idIes).orElseThrow(() -> new RuntimeException("IES não encontrada."));
        return new IesDTO(entity);

    }*/

    @Transactional
    public IesDTO insert(IesDTO dto){
        IES entity = dto.IesEntity();
        entity = iesRepository.save(entity);
        return new IesDTO(entity);
    }

    @Transactional
    public IesDTO update(Long id, IesDTO dto){
        IES entity = iesRepository.findById(id).orElseThrow(() -> new RuntimeException("IES não encontrada."));
        entity.setNomeInstituicao(dto.getNomeInstituicao());
        entity.setSigla(dto.getSigla());
        entity = iesRepository.save(entity);
        return new IesDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        IES entity = iesRepository.findById(id).orElseThrow(() -> new RuntimeException("IES não encontrada."));
        if(entity.getCentrosCusto() != null && entity.getCentrosCusto().isEmpty()){
            throw  new RuntimeException("Não é possível excluir a IES " + entity.getSigla() + " pois existem Centros de Custo associados a ela.");
        }
        iesRepository.delete(entity);
    }

}
