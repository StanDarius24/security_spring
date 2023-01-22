package com.stannis.backendauth.mappers

import com.stannis.backendauth.dto.UserDto
import com.stannis.backendauth.entity.AuthUser
import org.mapstruct.Mapper
import org.mapstruct.Mapping


@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.login", target = "login")
    @Mapping(source = "token", target = "token")
    @Mapping(target = "password", ignore = true)
    fun toUserDto(user: AuthUser?, token: String?): UserDto?

    @Mapping(source = "encodedPassword", target = "password")
    fun toAuthUser(userDto: UserDto, encodedPassword: String): AuthUser

}