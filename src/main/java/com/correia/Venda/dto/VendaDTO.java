package com.correia.Venda.dto;

import java.util.List;

import com.correia.Venda.model.Cliente;
import com.correia.Venda.model.ItemPK;
import com.correia.Venda.model.ItemVenda;
import com.correia.Venda.model.Produto;
import com.correia.Venda.model.Venda;

public class VendaDTO {

	private Long vendaId;
	private Long cliente;
	private List<ItemVendaDTO> itens;
	
	public Venda converte(VendaDTO dto) {
		Venda venda = new Venda();
		venda.setId(dto.getVendaId());
		Cliente cliente = new Cliente();
		cliente.setCliente_id(dto.getCliente());
		venda.setCliente(cliente);
		
		for (ItemVendaDTO itemVendaDTO : dto.itens) {
			ItemVenda item = new ItemVenda();
			Produto produto = itemVendaDTO.getProduto().convert(itemVendaDTO.getProduto());
			item.setId(new ItemPK(venda, produto,itemVendaDTO.getNseq()));
			item.setAcrescimo(itemVendaDTO.getAcrescimo());
			item.setDesconto(itemVendaDTO.getDesconto());
			item.setQuantidade(itemVendaDTO.getQuantidade());
			item.setValorUnitario(itemVendaDTO.getValorUnitario());
			
			venda.adicionarItem(item);
			
		}
		
		
		
		
		return venda;
	}
	//Getter // Setter
	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public List<ItemVendaDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemVendaDTO> itens) {
		this.itens = itens;
	}

	public Long getVendaId() {
		return vendaId;
	}

	public void setVendaId(Long vendaId) {
		this.vendaId = vendaId;
	}
	

}
