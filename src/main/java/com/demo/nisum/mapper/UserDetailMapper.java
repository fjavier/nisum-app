package com.demo.nisum.mapper;

import com.demo.nisum.domain.UserEntity;
import com.demo.nisum.dto.UserDetailDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailMapper {
    UserDetailDto userEntityToUserDetailDto(UserEntity user);
}
