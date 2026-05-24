package com.ucsal.estruturaOrganizacional.controller;

import com.ucsal.estruturaOrganizacional.dto.CentroDeCustoDTO;
import com.ucsal.estruturaOrganizacional.model.enuns.Especificacao;
import com.ucsal.estruturaOrganizacional.service.CentroDeCustoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/centro_de_custo")
public class CentroDeCustoController {

    @Autowired
    private CentroDeCustoService service;

    @GetMapping
    public List<CentroDeCustoDTO> listarTodos() {
        // Retorna a lista de DTOs que o método listaTodos() da Service já gera
        return service.listaTodos();
    }

    @GetMapping("/escolas")
    public List<CentroDeCustoDTO> listarEscolas() {
        // Se você optar por criar esse método na service, ele deve receber o Enum corrigido
        // Exemplo na service: return centroDeCustoRepository.findByEspecificacao(especificacao).stream().map(CentroDeCustoDTO::new).toList();
        return service.listarPorEspecificacao(Especificacao.ESCOLA);
    }

    @GetMapping("/unidades")
    public List<CentroDeCustoDTO> listarUnidades() {
        return service.listarPorEspecificacao(Especificacao.UNIDADE);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroDeCustoDTO> buscar(@PathVariable Long id) {
        // Usa o método listId da sua Service, que já devolve um DTO
        return ResponseEntity.ok(service.listId(id));
    }

    @PostMapping
    public ResponseEntity<CentroDeCustoDTO> criar(@RequestBody CentroDeCustoDTO dto) {
        // Recebe um DTO do front-end e devolve o DTO do objeto salvo
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroDeCustoDTO> atualizar(@PathVariable Long id, @RequestBody CentroDeCustoDTO dto) {
        // Garante a segurança forçando o ID da URL para dentro do DTO antes de mandar para a service
        dto.setIdCentroDeCusto(id);
        return ResponseEntity.ok(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        // Alinhado com o seu método delete(id) da Service
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        // Alinhado com o método inativar(id) que criamos por último
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
