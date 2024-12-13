package com.sereia.renan;

import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class RestauranteCadastrado {

    @Inject
    PgPool pool;


    @Incoming("restaurantes")
    public void cadastrar(String json) {
        Restaurante restaurante = JsonbBuilder.create().fromJson(json, Restaurante.class);

        System.out.println(json);
        System.out.println(restaurante);

        restaurante.persist(pool);
    }
}
