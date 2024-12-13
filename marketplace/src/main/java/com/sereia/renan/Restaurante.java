package com.sereia.renan;

import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

public class Restaurante {
    public Long id;

    public String nome;

    public Localizacao localizacao;


    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localizacao=" + localizacao +
                '}';
    }
    public void persist(PgPool pool) {

        pool.preparedQuery("insert into localizacao (id, latitude,longitude) values ($1,$2,$3)")
                .executeAndAwait(Tuple.of(localizacao.id,localizacao.latitude, localizacao.longitude));

        pool.preparedQuery("insert into restaurante (id, nome, localizacao_id) values ($1,$2,$3)")
                .executeAndAwait(Tuple.of(id,nome, localizacao.id));

    }


}
