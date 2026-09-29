package com.posto.service;

import com.posto.dto.BombaCombustivelRequest;
import com.posto.dto.BombaCombustivelResponse;
import com.posto.entity.BombaCombustivel;
import com.posto.entity.TipoCombustivel;
import com.posto.exception.RecursoNaoEncontradoException;
import com.posto.exception.RegraNegocioException;
import com.posto.repository.AbastecimentoRepository;
import com.posto.repository.BombaCombustivelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BombaCombustivelService {

    private final BombaCombustivelRepository repository;
    private final TipoCombustivelService tipoCombustivelService;
    private final AbastecimentoRepository abastecimentoRepository;

    public BombaCombustivelService(
            BombaCombustivelRepository repository,
            TipoCombustivelService tipoCombustivelService,
            AbastecimentoRepository abastecimentoRepository) {
        this.repository = repository;
        this.tipoCombustivelService = tipoCombustivelService;
        this.abastecimentoRepository = abastecimentoRepository;
    }

    @Transactional(readOnly = true)
    public List<BombaCombustivelResponse> listar() {
        return repository.findAllWithTipo().stream().map(BombaCombustivelResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public BombaCombustivelResponse buscarPorId(Long id) {
        return BombaCombustivelResponse.from(obterEntidade(id));
    }

    @Transactional
    public BombaCombustivelResponse criar(BombaCombustivelRequest request) {
        TipoCombustivel tipo = tipoCombustivelService.obterEntidade(request.tipoCombustivelId());
        BombaCombustivel entity = new BombaCombustivel();
        entity.setNome(request.nome().trim());
        entity.setTipoCombustivel(tipo);
        return BombaCombustivelResponse.from(repository.save(entity));
    }

    @Transactional
    public BombaCombustivelResponse atualizar(Long id, BombaCombustivelRequest request) {
        BombaCombustivel entity = obterEntidade(id);
        TipoCombustivel tipo = tipoCombustivelService.obterEntidade(request.tipoCombustivelId());
        entity.setNome(request.nome().trim());
        entity.setTipoCombustivel(tipo);
        return BombaCombustivelResponse.from(repository.save(entity));
    }

    @Transactional
    public void excluir(Long id) {
        obterEntidade(id);
        if (abastecimentoRepository.countByBombaId(id) > 0) {
            throw new RegraNegocioException(
                    "Não é possível excluir: existem abastecimentos vinculados a esta bomba.");
        }
        repository.deleteById(id);
    }

    BombaCombustivel obterEntidade(Long id) {
        return repository.findByIdWithTipo(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Bomba não encontrada: id=" + id));
    }
}
