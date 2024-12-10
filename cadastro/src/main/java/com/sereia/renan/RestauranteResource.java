package com.sereia.renan;

import com.sereia.renan.cadastro.Prato;
import com.sereia.renan.cadastro.Restaurante;
import dto.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.*;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "restaurante")
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-auth", in = SecuritySchemeIn.HEADER, scheme = "ifood-auth", type = SecuritySchemeType.OAUTH2, flows =
@OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8001/realms/ifood/protocol/openid-connect/token")))
@SecurityRequirement(name = "ifood-auth")
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;
    @Inject
    PratoMapper pratoMapper;

    @GET
    public List<RestauranteDTO> listAll() {
        List<Restaurante> listAll = Restaurante.listAll();
        return listAll.stream()
                .map(restauranteMapper::toRestauranteDTO)
                .toList();
    }

    @POST
    @Transactional
    @APIResponse(responseCode = "400",content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))
    public Response adicionar(@Valid RestauranteDTO dto){
        Restaurante restaurente = restauranteMapper.toRestaurante(dto);
        restaurente.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, RestauranteDTO dto){
        Optional<Restaurante> byId = Restaurante.findByIdOptional(id);
        if (byId.isEmpty()){
            throw new NotFoundException();
        }

        restauranteMapper.toRestaurante(dto, byId.get());

        byId.get().persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        Optional<Restaurante> obj = Restaurante.findByIdOptional(id);

        obj.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        });

    }

    //Pratos

    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name = "prato")
    @Tag(name = "restaurante")
    public List<PratoDTO> buscarPrato(@PathParam("idRestaurante") Long idRestaurante){
        Optional<Restaurante> restaurenteId = Restaurante.findByIdOptional(idRestaurante);

        if (restaurenteId.isEmpty()) throw new NotFoundException("Restaurante não encontrado");

        List<Prato> restaurante = Prato.list("restaurante", restaurenteId.get());

       return restaurante.stream()
                .map(pratoMapper::toPratoDTO).toList();
    }

    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    @Tag(name = "prato")
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, PratoDTO dto){
        Optional<Restaurante> restaurenteId = Restaurante.findByIdOptional(idRestaurante);

        if (restaurenteId.isEmpty()) throw new NotFoundException("Restaurante não encontrado");

        Prato prato = pratoMapper.toPrato(dto);
        prato.restaurante = restaurenteId.get();
        prato.persist();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public void atualizar(@PathParam("idRestaurante")Long idRestaurante,@PathParam("id")Long idPrato, PratoDTO dto){
        Optional<Restaurante> restaurenteId = Restaurante.findByIdOptional(idRestaurante);

        if (restaurenteId.isEmpty()) throw new NotFoundException("Restaurante não encontrado");

        Optional<Prato> pratoId = Prato.findByIdOptional(idPrato);

        if (pratoId.isEmpty()) throw new NotFoundException("Prato não encontrado");

        Prato prato = pratoId.get();
        pratoMapper.toPrato(dto,prato );
        prato.restaurante = restaurenteId.get();
        prato.persist();

    }

    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public void deletePrato(@PathParam("idRestaurante")Long idRestaurante,@PathParam("id") Long id){
        Optional<Restaurante> restaurenteId = Restaurante.findByIdOptional(idRestaurante);

        if (restaurenteId.isEmpty()) throw new NotFoundException("Restaurante não encontrado");

        Optional<Prato> pratoId = Prato.findByIdOptional(id);

        if (pratoId.isEmpty()) throw new NotFoundException("Prato não encontrado");

        pratoId
                .ifPresentOrElse(Prato::delete,
                        ()-> {throw new NotFoundException();});
    }
}
