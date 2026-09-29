package com.posto.dto;

import com.posto.entity.Abastecimento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AbastecimentoResponse(
        Long id,
        Long bombaId,
        String bombaNome,
        String tipoCombustivelNome,
        LocalDate dataAbastecimento,
        BigDecimal valorTotal,
        BigDecimal litragem
) {

    public static AbastecimentoResponse from(Abastecimento entity) {
        return new AbastecimentoResponse(
                entity.getId(),
                entity.getBomba().getId(),
                entity.getBomba().getNome(),
                entity.getBomba().getTipoCombustivel().getNome(),
                entity.getDataAbastecimento(),
                entity.getValorTotal(),
                entity.getLitragem());
    }
}
