package com.sereia.renan;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;

public class PratoCarrinho {
    public String cliente;

    public Long prato;

    public static Uni<List<PratoCarrinho>> findCarrinho(PgPool pgPool, String client){
        return pgPool.preparedQuery("select * from prato_cliente where cliente = $1").execute(Tuple.of(client))
                .map(rowSet -> {
                    List<PratoCarrinho> list = new ArrayList<>(rowSet.size());
                    for (Row row: rowSet){
                        list.add(toPratoCarrinho(row));
                    }
                    return list;
                });
    }

    public static Uni<Boolean> delete(PgPool pool, String cliente){
        return pool.preparedQuery("delete from prato_cliente where cliente = $1").execute(Tuple.of(cliente))
                .map(rows -> rows.rowCount() == 1);
    }

    private static PratoCarrinho toPratoCarrinho(Row row) {
        PratoCarrinho pratoCarrinho = new PratoCarrinho();
        pratoCarrinho.prato = row.getLong("prato");
        pratoCarrinho.cliente=row.getString("cliente");
        return pratoCarrinho ;
    }

    public static Uni<Long> save(PgPool pgPool,String cliente, Long idPrato) {
        return pgPool.preparedQuery("insert into prato_cliente (cliente,prato) values ($1,$2) RETURNING (prato)")
                .execute(Tuple.of(cliente,idPrato))
                .map(rows -> rows.iterator().next().getLong("prato"));
    }
}
