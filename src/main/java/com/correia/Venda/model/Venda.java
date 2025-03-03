package com.correia.Venda.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.correia.Venda.dto.ItemVendaDTO;
import com.correia.Venda.dto.VendaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date dataVenda; // Data da venda
	private BigDecimal valorBruto; // Total da venda
	private BigDecimal valorLiquido;
	private BigDecimal valorAcrecimos;
	private BigDecimal valorDescontos;

	@ManyToOne
	@JoinColumn(name = "cliente_id") // Nome da coluna que será a chave estrangeira
	@JsonBackReference
	private Cliente cliente;

	@OneToMany(mappedBy = "id.venda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemVenda> itens = new ArrayList<>(); // Lista de itens da venda

	// Construtor vazio (obrigatório para JPA)
	public Venda() {
	}
	public VendaDTO converte(Venda venda) {
		VendaDTO dto = new VendaDTO();
		
		dto.setVendaId(venda.getId());
		
		dto.setCliente(venda.getCliente().getCliente_id());
		
		for (ItemVenda itemVenda : venda.getItens()) {
			ItemVendaDTO itemDto = new ItemVendaDTO();
			
			itemDto.setVenda(venda.getId());     //new ItemPK(venda, produto,itemVenda.getNseq()));
			itemDto.setAcrescimo(itemVenda.getAcrescimo());
			itemDto.setDesconto(itemVenda.getDesconto());
			itemDto.setQuantidade(itemVenda.getQuantidade());
			itemDto.setValorUnitario(itemVenda.getValorUnitario());
			
			dto.getItens().add(itemDto);
			
		}
		
		
		return dto;
	}

	// Construtor com parâmetros
	public Venda(Date dataVenda) {
		this.dataVenda = dataVenda;
		this.valorBruto = BigDecimal.ZERO; // Inicializa o total como zero
	}

	// Método para adicionar um item à venda
	public void adicionarItem(ItemVenda item) {
		this.itens.add(item);
		calcularTotal(); // Atualiza o total da venda
	}

	// Método para calcular o total da venda
	private void calcularTotal() {
		valorBruto = BigDecimal.ZERO;
		valorDescontos = BigDecimal.ZERO;
		valorAcrecimos = BigDecimal.ZERO;

		for (ItemVenda item : itens) {
			valorBruto = valorBruto.add(item.getValorBruto()); // Soma o valor bruto de cada item
			valorDescontos = valorDescontos.add(item.getDesconto()); // Soma os descontos de cada item
			valorAcrecimos = valorAcrecimos.add(item.getAcrescimo()); // Soma os acréscimos de cada item
		}

		// Calcula o valor líquido
		valorLiquido = valorBruto.add(valorAcrecimos).subtract(valorDescontos);
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public BigDecimal getTotal() {
		return valorBruto;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
		calcularTotal(); // Atualiza o total ao definir novos itens
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}
}