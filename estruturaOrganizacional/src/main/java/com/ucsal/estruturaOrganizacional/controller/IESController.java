package com.ucsal.estruturaOrganizacional.controller;

import com.ucsal.estruturaOrganizacional.dto.IesDTO;
import com.ucsal.estruturaOrganizacional.repository.IESRepository;
import com.ucsal.estruturaOrganizacional.service.IESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ies")
public class IESController {

    @Autowired
    IESService service;

    @GetMapping
    public ResponseEntity<List<IesDTO>> listarTodos(){
        List<IesDTO> list = service.listarTodas();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<IesDTO> insert(@RequestBody IesDTO dto){
        IesDTO novoIes = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoIes);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<IesDTO> update(@PathVariable Long id, IesDTO dto){
        IesDTO iesUpdate = service.update(id, dto);
        return ResponseEntity.ok(iesUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
