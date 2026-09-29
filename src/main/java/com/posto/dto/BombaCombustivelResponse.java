package com.posto.dto;

import com.posto.entity.BombaCombustivel;

public record BombaCombustivelResponse(
        Long id,
        String nome,
        Long tipoCombustivelId,
        String tipoCombustivelNome
) {

    public static BombaCombustivelResponse from(BombaCombustivel entity) {
        return new BombaCombustivelResponse(
                entity.getId(),
                entity.getNome(),
                entity.getTipoCombustivel().getId(),
                entity.getTipoCombustivel().getNome());
    }
}
