package dto;

import com.sereia.renan.cadastro.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface RestauranteMapper {

    @Mapping(target = "nome", source = "nomeFantasia")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "localizacao.id", ignore = true)
    Restaurante toRestaurante(RestauranteDTO dto);

    @Mapping(target = "nome", source = "nomeFantasia")
    @Mapping(target = "localizacao.id", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    void toRestaurante(RestauranteDTO dto, @MappingTarget Restaurante restaurante);

    @Mapping(target = "nomeFantasia", source = "nome")
    //Exemplo de formatação.
    @Mapping(target = "dataCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
    RestauranteDTO toRestauranteDTO(Restaurante r);

}
