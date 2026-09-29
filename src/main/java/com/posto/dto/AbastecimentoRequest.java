package com.posto.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AbastecimentoRequest(
        @NotNull Long bombaId,
        @NotNull LocalDate dataAbastecimento,
        @NotNull @Positive BigDecimal valorTotal,
        @NotNull @Positive BigDecimal litragem
) {
}
