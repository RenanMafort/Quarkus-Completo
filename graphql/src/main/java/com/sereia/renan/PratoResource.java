package com.sereia.renan;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.eclipse.microprofile.graphql.*;

@GraphQLApi
public class PratoResource {
    @Query("buscarPratos")
    @Description("Esta query retorna todos os pratos")
    public List<Prato> bustarTodosPratos() {
        Prato prato = new Prato();
        prato.nome = "feijao";
        prato.descricao = "prato tipico de algum lugar";
        prato.setValor(BigDecimal.TEN);
        List<Prato> lista = Collections.singletonList(prato);
        return lista;
    }

    @Query("buscarPratoPorId")
    @Description("Esta query retorna um prato")
    public Prato bustarPrato(@Name("id") Integer id) {
        System.out.println("ID: " + id);
        Prato prato = new Prato();
        prato.nome = "feijao";
        prato.descricao = "prato tipico de algum lugar";
        prato.setValor(BigDecimal.TEN);
        return prato;
    }

    @Name("restaurante")
    public Restaurante buscarRestaurante(@Source Prato prato) {
        Restaurante restaurante = new Restaurante();
        restaurante.setDono("João");
        restaurante.setNome("Manguai");
        return restaurante;
    }

    @Mutation
    @Description("Altera o restaurante")
    public Restaurante alterar(Restaurante restaurante) {
        System.out.println(restaurante.getNome());
        return restaurante;
    }
}
