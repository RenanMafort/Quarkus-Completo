package dto;

import com.sereia.renan.cadastro.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target = "nome", source = "nomeFantasia")
    Restaurante toRestaurante(RestauranteDTO dto);

    @Mapping(target = "nome", source = "nomeFantasia")
    void toRestaurante(RestauranteDTO dto, @MappingTarget Restaurante restaurante);

    @Mapping(target = "nomeFantasia", source = "nome")
    @Mapping(target = "dataCriacao",dateFormat = "dd/MM/yyyy HH:mm:ss")
    RestauranteDTO toRestauranteDTO(Restaurante restaurante);

}
