package com.correia.Venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correia.Venda.model.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
