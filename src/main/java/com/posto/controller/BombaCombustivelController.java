package com.posto.controller;

import com.posto.dto.BombaCombustivelRequest;
import com.posto.dto.BombaCombustivelResponse;
import com.posto.service.BombaCombustivelService;
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
@RequestMapping("/api/bombas")
public class BombaCombustivelController {

    private final BombaCombustivelService service;

    public BombaCombustivelController(BombaCombustivelService service) {
        this.service = service;
    }

    @GetMapping
    public List<BombaCombustivelResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public BombaCombustivelResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<BombaCombustivelResponse> criar(@Valid @RequestBody BombaCombustivelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("/{id}")
    public BombaCombustivelResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody BombaCombustivelRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
