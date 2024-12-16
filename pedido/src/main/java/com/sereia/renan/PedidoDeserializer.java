package com.sereia.renan;

import com.sereia.renan.dto.PedidoRealizadoDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PedidoDeserializer extends ObjectMapperDeserializer<PedidoRealizadoDTO> {
    public PedidoDeserializer() {
        super(PedidoRealizadoDTO.class);
    }
}
