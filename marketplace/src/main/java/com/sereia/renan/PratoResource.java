package com.sereia.renan;

import com.sereia.renan.dto.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {
    @Inject
    PgPool pool;

    @GET
    @Path("/multi")
    public Multi<PratoDTO> buscarPratos(){
        return Prato.findAllMulti(pool);
    }

    @GET
    public Uni<List<PratoDTO>> buscarPratosMulti(){
        return Prato.findAllUnit(pool);
    }
}
