package com.correia.Venda.model;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class ItemVenda {

    @EmbeddedId
    private ItemPK id; // Chave primária composta

    private Integer quantidade; // Quantidade do produto vendido
    private BigDecimal valorUnitario; // Valor unitário do produto
    private BigDecimal desconto; // Desconto aplicado
    private BigDecimal acrescimo; // Acréscimo aplicado

    // Construtor vazio (obrigatório para JPA)
    public ItemVenda() {
    }

    // Construtor com parâmetros
    public ItemVenda(Venda venda, Produto produto, Long sequencial,Integer quantidade, BigDecimal valorUnitario, BigDecimal desconto,
                     BigDecimal acrescimo) {
        this.id = new ItemPK(venda, produto, sequencial); // Inicializa a chave primária
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.desconto = desconto != null ? desconto : BigDecimal.ZERO; // Inicializa com zero se nulo
        this.acrescimo = acrescimo != null ? acrescimo : BigDecimal.ZERO; // Inicializa com zero se nulo
    }

    // Métodos de cálculo
    public BigDecimal getValorBruto() {
        return BigDecimal.valueOf(quantidade).multiply(valorUnitario); // Cálculo do valor bruto
    }

    public BigDecimal getValorLiquido() {
        return getValorBruto().add(acrescimo).subtract(desconto); // Cálculo do valor líquido
    }

    // Getters e Setters
    public ItemPK getId() {
        return id;
    }

    public void setId(ItemPK id) {
        this.id = id;
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
        this.desconto = desconto != null ? desconto : BigDecimal.ZERO; // Garante que não seja nulo
    }

    public BigDecimal getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(BigDecimal acrescimo) {
        this.acrescimo = acrescimo != null ? acrescimo : BigDecimal.ZERO; // Garante que não seja nulo
    }
}