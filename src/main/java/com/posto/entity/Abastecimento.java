package com.posto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Registro de um abastecimento realizado em uma bomba.
 */
@Entity
@Table(name = "abastecimentos")
public class Abastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bomba_id", nullable = false)
    private BombaCombustivel bomba;

    @NotNull
    @Column(name = "data_abastecimento", nullable = false)
    private LocalDate dataAbastecimento;

    /** Valor total pago pelo abastecimento (R$). */
    @NotNull
    @Positive
    @Column(name = "valor_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal litragem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BombaCombustivel getBomba() {
        return bomba;
    }

    public void setBomba(BombaCombustivel bomba) {
        this.bomba = bomba;
    }

    public LocalDate getDataAbastecimento() {
        return dataAbastecimento;
    }

    public void setDataAbastecimento(LocalDate dataAbastecimento) {
        this.dataAbastecimento = dataAbastecimento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getLitragem() {
        return litragem;
    }

    public void setLitragem(BigDecimal litragem) {
        this.litragem = litragem;
    }
}
