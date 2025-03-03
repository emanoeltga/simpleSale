package com.correia.Venda.dto;

import java.math.BigDecimal;

import com.correia.Venda.model.ItemPK;
import com.correia.Venda.model.ItemVenda;
import com.correia.Venda.model.Produto;
import com.correia.Venda.model.Venda;

public class ItemVendaDTO {
	
	private Long venda;
	private ProdutoDTO produto;
	private Long nseq;

	private Integer quantidade; // Quantidade do produto vendido
	private BigDecimal valorUnitario; // Valor unitário do produto
	private BigDecimal desconto; // Desconto aplicado
	private BigDecimal acrescimo; // Acréscimo aplicado
	

	//Getter // Setter
	public Long getVenda() {		
		return venda;
	}
	public void setVenda(Long venda) {
		this.venda = venda;
	}
	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
	public Long getNseq() {
		return nseq;
	}
	public void setNseq(Long nseq) {
		this.nseq = nseq;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getAcrescimo() {
		return acrescimo;
	}
	public void setAcrescimo(BigDecimal acrescimo) {
		this.acrescimo = acrescimo;
	}
	

	
}
