package es.lareira.denodo.infraestructure.db.relational.mapper;

import es.lareira.denodo.application.domain.model.purchase.User;
import es.lareira.denodo.infraestructure.db.relational.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toDomain(UserEntity userEntity);
}
