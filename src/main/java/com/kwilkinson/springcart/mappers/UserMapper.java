package com.kwilkinson.springcart.mappers;

import com.kwilkinson.springcart.dtos.RegisterUserRequest;
import com.kwilkinson.springcart.dtos.UpdateUserRequest;
import com.kwilkinson.springcart.dtos.UserDto;
import com.kwilkinson.springcart.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);

    void update(UpdateUserRequest request, @MappingTarget User user);
}
