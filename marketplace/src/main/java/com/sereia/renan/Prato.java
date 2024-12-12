package com.sereia.renan;

import com.sereia.renan.dto.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class Prato {
    public Long id;

    public String nome;

    public String descricao;

    public Restaurante restaurante;

    public BigDecimal preco;

    public static Multi<PratoDTO> findAllMulti(PgPool pool) {
        return pool.preparedQuery("select * from prato limit 5000").execute()
                .onItem().transformToMulti(rows -> Multi.createFrom().iterable(rows))
                .onItem().transform(PratoDTO::from);

    }

    public static Multi<PratoDTO> findAllMulti(PgPool pool,Long idRestaurante) {
        return pool.preparedQuery("select * from prato where public.prato.restaurante_id = $1 ORDER BY nome desc")
                .execute(Tuple.of(idRestaurante))
                .onItem().transformToMulti(rows -> Multi.createFrom().iterable(rows))
                .onItem().transform(PratoDTO::from);

    }


    public static Uni<List<PratoDTO>> findAllUnit(PgPool pool) {
        return pool.preparedQuery("select * from prato limit 5000").execute()
                .onItem().transform(s -> {
                    List<PratoDTO> list = new ArrayList<>();
                    s.forEach(row -> {
                        list.add(PratoDTO.from(row));  // Adiciona cada linha mapeada em PratoDTO
                    });
                    return list;  // Retorna a lista com todos os PratoDTOs
                });

    }

}
