package com.posto.repository;

import com.posto.entity.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    @Query("SELECT a FROM Abastecimento a JOIN FETCH a.bomba b JOIN FETCH b.tipoCombustivel")
    List<Abastecimento> findAllWithBomba();

    @Query("SELECT a FROM Abastecimento a JOIN FETCH a.bomba b JOIN FETCH b.tipoCombustivel WHERE a.id = :id")
    Optional<Abastecimento> findByIdWithBomba(Long id);

    long countByBombaId(Long bombaId);
}
