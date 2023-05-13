package com.fenrir.colorme.user.application.port.in.processuser;

public interface ProcessOAuth2UserUseCase {
    ProcessOAuth2UserResponse processUser(ProcessOAuth2UserCommand command);
}
