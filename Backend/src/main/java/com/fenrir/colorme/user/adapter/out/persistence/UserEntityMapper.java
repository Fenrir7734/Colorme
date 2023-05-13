package com.fenrir.colorme.user.adapter.out.persistence;

import com.fenrir.colorme.user.domain.User;
import org.mapstruct.Mapper;

@Mapper
interface UserEntityMapper {
    UserEntity toUserEntity(User user);
    User toUser(UserEntity user);
}
