package com.correia.Venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.correia.Venda.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
