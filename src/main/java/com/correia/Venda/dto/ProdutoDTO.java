package com.correia.Venda.dto;

import org.springframework.beans.BeanUtils;

import com.correia.Venda.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
	
	private Long produto_id;
    private String nome;
    private Double preco;
    
    public Produto convert(ProdutoDTO dto) {
    	Produto produto = new Produto();
    	produto.setNome(dto.getNome());
    	produto.setPreco(dto.getPreco());
    	produto.setProduto_id(dto.getProduto_id());    	
    	
    	return produto;
    }

	
}
