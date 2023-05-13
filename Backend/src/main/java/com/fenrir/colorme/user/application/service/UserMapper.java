package com.fenrir.colorme.user.application.service;

import com.fenrir.colorme.user.application.port.in.processuser.ProcessOAuth2UserResponse;
import com.fenrir.colorme.user.domain.User;
import org.mapstruct.Mapper;

@Mapper
interface UserMapper {
    ProcessOAuth2UserResponse toOAuth2UserResponse(User user);
}
