package com.cipri.welcome.mapper;

import com.cipri.welcome.dto.UserDTO;
import com.cipri.welcome.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userEntityToDTO(UserEntity userEntity);
    UserEntity useDTOToEntity(UserDTO userDTO);
    List<UserDTO> userEntityToDTO(List<UserEntity> lstUserEntity);
}
