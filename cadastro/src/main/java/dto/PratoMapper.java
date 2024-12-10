package dto;

import com.sereia.renan.cadastro.Prato;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface PratoMapper {

    Prato toPrato(PratoDTO dto);

    void toPrato(PratoDTO dto, @MappingTarget Prato prato);

    PratoDTO toPratoDTO(Prato restaurante);

}
