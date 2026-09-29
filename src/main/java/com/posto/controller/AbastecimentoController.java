package com.posto.controller;

import com.posto.dto.AbastecimentoRequest;
import com.posto.dto.AbastecimentoResponse;
import com.posto.service.AbastecimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/abastecimentos")
public class AbastecimentoController {

    private final AbastecimentoService service;

    public AbastecimentoController(AbastecimentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<AbastecimentoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AbastecimentoResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<AbastecimentoResponse> criar(@Valid @RequestBody AbastecimentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("/{id}")
    public AbastecimentoResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AbastecimentoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
