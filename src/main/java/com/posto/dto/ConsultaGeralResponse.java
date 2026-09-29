package com.posto.dto;

import java.util.List;

/**
 * Agregação de todos os cadastros para consulta em uma única chamada.
 */
public record ConsultaGeralResponse(
        List<TipoCombustivelResponse> tiposCombustivel,
        List<BombaCombustivelResponse> bombas,
        List<AbastecimentoResponse> abastecimentos
) {
}
