package com.posto.dto;

import com.posto.entity.TipoCombustivel;

import java.math.BigDecimal;

public record TipoCombustivelResponse(Long id, String nome, BigDecimal precoPorLitro) {

    public static TipoCombustivelResponse from(TipoCombustivel entity) {
        return new TipoCombustivelResponse(entity.getId(), entity.getNome(), entity.getPrecoPorLitro());
    }
}
