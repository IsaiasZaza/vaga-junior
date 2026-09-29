package com.posto.service;

import com.posto.dto.ConsultaGeralResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultaService {

    private final TipoCombustivelService tipoCombustivelService;
    private final BombaCombustivelService bombaService;
    private final AbastecimentoService abastecimentoService;

    public ConsultaService(
            TipoCombustivelService tipoCombustivelService,
            BombaCombustivelService bombaService,
            AbastecimentoService abastecimentoService) {
        this.tipoCombustivelService = tipoCombustivelService;
        this.bombaService = bombaService;
        this.abastecimentoService = abastecimentoService;
    }

    @Transactional(readOnly = true)
    public ConsultaGeralResponse consultarTudo() {
        return new ConsultaGeralResponse(
                tipoCombustivelService.listar(),
                bombaService.listar(),
                abastecimentoService.listar());
    }
}
