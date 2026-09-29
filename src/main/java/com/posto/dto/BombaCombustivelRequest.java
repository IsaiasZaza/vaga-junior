package com.posto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BombaCombustivelRequest(
        @NotBlank String nome,
        @NotNull Long tipoCombustivelId
) {
}
