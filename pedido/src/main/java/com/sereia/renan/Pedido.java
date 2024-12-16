package com.sereia.renan;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity(collection = "pedidos",database = "pedido")
public class Pedido {
    public String cliente;

    public List<Prato> pratos;

    public Restaurante restaurante;

    public String entregador;

    public Localizacao localizacaoEntregador;
}
