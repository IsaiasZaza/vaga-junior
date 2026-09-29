package com.posto.service;

import com.posto.dto.AbastecimentoRequest;
import com.posto.dto.AbastecimentoResponse;
import com.posto.entity.Abastecimento;
import com.posto.entity.BombaCombustivel;
import com.posto.exception.RecursoNaoEncontradoException;
import com.posto.repository.AbastecimentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AbastecimentoService {

    private final AbastecimentoRepository repository;
    private final BombaCombustivelService bombaService;

    public AbastecimentoService(AbastecimentoRepository repository, BombaCombustivelService bombaService) {
        this.repository = repository;
        this.bombaService = bombaService;
    }

    @Transactional(readOnly = true)
    public List<AbastecimentoResponse> listar() {
        return repository.findAllWithBomba().stream().map(AbastecimentoResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public AbastecimentoResponse buscarPorId(Long id) {
        return AbastecimentoResponse.from(obterEntidade(id));
    }

    @Transactional
    public AbastecimentoResponse criar(AbastecimentoRequest request) {
        BombaCombustivel bomba = bombaService.obterEntidade(request.bombaId());
        Abastecimento entity = new Abastecimento();
        entity.setBomba(bomba);
        entity.setDataAbastecimento(request.dataAbastecimento());
        entity.setValorTotal(request.valorTotal());
        entity.setLitragem(request.litragem());
        return AbastecimentoResponse.from(repository.save(entity));
    }

    @Transactional
    public AbastecimentoResponse atualizar(Long id, AbastecimentoRequest request) {
        Abastecimento entity = obterEntidade(id);
        BombaCombustivel bomba = bombaService.obterEntidade(request.bombaId());
        entity.setBomba(bomba);
        entity.setDataAbastecimento(request.dataAbastecimento());
        entity.setValorTotal(request.valorTotal());
        entity.setLitragem(request.litragem());
        return AbastecimentoResponse.from(repository.save(entity));
    }

    @Transactional
    public void excluir(Long id) {
        obterEntidade(id);
        repository.deleteById(id);
    }

    Abastecimento obterEntidade(Long id) {
        return repository.findByIdWithBomba(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Abastecimento não encontrado: id=" + id));
    }
}
