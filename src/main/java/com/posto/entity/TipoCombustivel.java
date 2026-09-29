package com.posto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Tipo de combustível vendido no posto (ex.: Gasolina Comum, Etanol).
 */
@Entity
@Table(name = "tipos_combustivel")
public class TipoCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    /** Preço cobrado por litro. */
    @NotNull
    @Positive
    @Column(name = "preco_por_litro", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoPorLitro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoPorLitro() {
        return precoPorLitro;
    }

    public void setPrecoPorLitro(BigDecimal precoPorLitro) {
        this.precoPorLitro = precoPorLitro;
    }
}
