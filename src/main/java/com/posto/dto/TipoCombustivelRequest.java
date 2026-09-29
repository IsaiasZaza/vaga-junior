package com.posto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TipoCombustivelRequest(
        @NotBlank String nome,
        @NotNull @Positive BigDecimal precoPorLitro
) {
}
