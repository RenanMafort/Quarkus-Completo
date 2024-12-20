package com.sereia.renan;

import org.eclipse.microprofile.graphql.Ignore;

import java.math.BigDecimal;
import java.util.List;

public class Prato {
    public String nome;
    public String descricao;
    public BigDecimal valor;

    @Ignore
    public List<Integer> ingredientesIds;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
