package com.example.websockets.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO mapToDTO(User user);
}
