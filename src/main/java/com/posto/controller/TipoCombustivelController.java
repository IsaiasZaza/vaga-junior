package com.posto.controller;

import com.posto.dto.TipoCombustivelRequest;
import com.posto.dto.TipoCombustivelResponse;
import com.posto.service.TipoCombustivelService;
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
@RequestMapping("/api/tipos-combustivel")
public class TipoCombustivelController {

    private final TipoCombustivelService service;

    public TipoCombustivelController(TipoCombustivelService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoCombustivelResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TipoCombustivelResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<TipoCombustivelResponse> criar(@Valid @RequestBody TipoCombustivelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("/{id}")
    public TipoCombustivelResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoCombustivelRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
