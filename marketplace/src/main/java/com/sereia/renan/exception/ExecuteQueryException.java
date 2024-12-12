package com.sereia.renan.exception;

import io.vertx.pgclient.PgException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExecuteQueryException implements ExceptionMapper<PgException> {

    @Override
    public Response toResponse(PgException e) {
        return Response.status(500).entity("message: ".concat(e.getErrorMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
