package com.sereia.renan;

import com.sereia.renan.dto.PedidoRealizadoDTO;
import com.sereia.renan.dto.PratoDTO;
import com.sereia.renan.dto.PratoPedidoDTO;
import com.sereia.renan.dto.RestauranteDTO;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.List;
import java.util.stream.Collectors;

@Path( "carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {
    private static final String CLIENTE = "a";

    @Inject
    PgPool pgPool;

    @Inject
    @Channel("pedidos")
    Emitter<PedidoRealizadoDTO> realizadoDTOEmitter;

    @GET
    public Uni<List<PratoCarrinho>> buscarCarrinho(){
        return PratoCarrinho.findCarrinho(pgPool,CLIENTE);
    }

    @POST
    @Path("/prato/{idPrato}")
    public Uni<Long> adicionarPrato(@PathParam("idPrato") Long idPrato) {
        PratoCarrinho pc = new PratoCarrinho();
        pc.cliente = CLIENTE;
        pc.prato = idPrato;
        return PratoCarrinho.save(pgPool, CLIENTE, idPrato);
    }

    @POST
    @Path("/realizar-pedido")
    public Uni<Boolean> realizarPedido(){
        PedidoRealizadoDTO pedidoRealizadoDTO = new PedidoRealizadoDTO();
        pedidoRealizadoDTO.cliente = CLIENTE;

        List<PratoCarrinho> pratoCarrinho = PratoCarrinho.findCarrinho(pgPool, CLIENTE).await().indefinitely();
        //Utilizar mapstruts
        pedidoRealizadoDTO.pratos = pratoCarrinho.stream().map(this::from).collect(Collectors.toList());

        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.nome = "nome restaurante";
        pedidoRealizadoDTO.restaurante = restaurante;
        realizadoDTOEmitter.send(pedidoRealizadoDTO);
        return PratoCarrinho.delete(pgPool, CLIENTE);

    }

    private PratoPedidoDTO from(PratoCarrinho pratoCarrinho) {
        PratoDTO dto = Prato.findById(pgPool, pratoCarrinho.prato).await().indefinitely();
        return new PratoPedidoDTO(dto.nome, dto.descricao, dto.preco);
    }

}
