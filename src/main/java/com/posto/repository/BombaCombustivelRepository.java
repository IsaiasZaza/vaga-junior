package com.posto.repository;

import com.posto.entity.BombaCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BombaCombustivelRepository extends JpaRepository<BombaCombustivel, Long> {

    @Query("SELECT b FROM BombaCombustivel b JOIN FETCH b.tipoCombustivel")
    List<BombaCombustivel> findAllWithTipo();

    @Query("SELECT b FROM BombaCombustivel b JOIN FETCH b.tipoCombustivel WHERE b.id = :id")
    Optional<BombaCombustivel> findByIdWithTipo(Long id);

    long countByTipoCombustivelId(Long tipoCombustivelId);
}
