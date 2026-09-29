package com.posto.service;

import com.posto.dto.TipoCombustivelRequest;
import com.posto.dto.TipoCombustivelResponse;
import com.posto.entity.TipoCombustivel;
import com.posto.exception.RecursoNaoEncontradoException;
import com.posto.exception.RegraNegocioException;
import com.posto.repository.BombaCombustivelRepository;
import com.posto.repository.TipoCombustivelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoCombustivelService {

    private final TipoCombustivelRepository repository;
    private final BombaCombustivelRepository bombaRepository;

    public TipoCombustivelService(
            TipoCombustivelRepository repository,
            BombaCombustivelRepository bombaRepository) {
        this.repository = repository;
        this.bombaRepository = bombaRepository;
    }

    @Transactional(readOnly = true)
    public List<TipoCombustivelResponse> listar() {
        return repository.findAll().stream().map(TipoCombustivelResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public TipoCombustivelResponse buscarPorId(Long id) {
        return TipoCombustivelResponse.from(obterEntidade(id));
    }

    @Transactional
    public TipoCombustivelResponse criar(TipoCombustivelRequest request) {
        validarNomeUnico(request.nome(), null);
        TipoCombustivel entity = new TipoCombustivel();
        entity.setNome(request.nome().trim());
        entity.setPrecoPorLitro(request.precoPorLitro());
        return TipoCombustivelResponse.from(repository.save(entity));
    }

    @Transactional
    public TipoCombustivelResponse atualizar(Long id, TipoCombustivelRequest request) {
        TipoCombustivel entity = obterEntidade(id);
        validarNomeUnico(request.nome(), id);
        entity.setNome(request.nome().trim());
        entity.setPrecoPorLitro(request.precoPorLitro());
        return TipoCombustivelResponse.from(repository.save(entity));
    }

    @Transactional
    public void excluir(Long id) {
        obterEntidade(id);
        if (bombaRepository.countByTipoCombustivelId(id) > 0) {
            throw new RegraNegocioException(
                    "Não é possível excluir: existem bombas vinculadas a este tipo de combustível.");
        }
        repository.deleteById(id);
    }

    TipoCombustivel obterEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tipo de combustível não encontrado: id=" + id));
    }

    private void validarNomeUnico(String nome, Long idIgnorado) {
        repository.findByNomeIgnoreCase(nome.trim()).ifPresent(existente -> {
            if (idIgnorado == null || !existente.getId().equals(idIgnorado)) {
                throw new RegraNegocioException("Já existe um tipo de combustível com este nome.");
            }
        });
    }
}
