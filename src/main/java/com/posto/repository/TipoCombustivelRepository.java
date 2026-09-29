package com.posto.repository;

import com.posto.entity.TipoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoCombustivelRepository extends JpaRepository<TipoCombustivel, Long> {

    Optional<TipoCombustivel> findByNomeIgnoreCase(String nome);
}
