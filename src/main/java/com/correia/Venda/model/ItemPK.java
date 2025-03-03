package com.correia.Venda.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ItemPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Long sequencial; // Sequencial, se necessário

    // Construtor vazio
    public ItemPK() {}

    // Construtor com parâmetros
    public ItemPK(Venda venda, Produto produto, Long sequencial) {
        this.venda = venda;
        this.produto = produto;
        this.sequencial=sequencial;
    }

    // Getters e Setters
    @JsonIgnore
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getSequencial() {
        return sequencial;
    }

    public void setSequencial(Long sequencial) {
        this.sequencial = sequencial;
    }

    @Override
    public int hashCode() {
        return Objects.hash(venda, produto, sequencial);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemPK other = (ItemPK) obj;
        return Objects.equals(venda, other.venda) && 
               Objects.equals(produto, other.produto) && 
               Objects.equals(sequencial, other.sequencial);
    }
}