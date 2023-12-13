package es.lareira.denodo.adapter.api.mapper;

import es.lareira.denodo.application.domain.model.purchase.User;
import es.lareira.denodo.generated.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
  UserDTO toDto(User userEntity);
}
