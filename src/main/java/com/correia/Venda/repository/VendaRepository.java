package com.correia.Venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.correia.Venda.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
