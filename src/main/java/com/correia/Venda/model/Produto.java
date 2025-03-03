package com.correia.Venda.model;



import org.springframework.beans.BeanUtils;

import com.correia.Venda.dto.ProdutoDTO;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produto_id;
    private String nome;
    private Double preco;
    private Integer saldo;
    
    public ProdutoDTO convert(Produto produto ) {
    	ProdutoDTO dto = new ProdutoDTO();
    	
    	dto.setNome(produto.getNome());
    	dto.setPreco(produto.getPreco());
    	dto.setProduto_id(produto.getProduto_id());  
    	
    	return dto;
    }
}
