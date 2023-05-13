package com.fenrir.colorme.user.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.user.application.port.in.processuser.ProcessOAuth2UserCommand;
import com.fenrir.colorme.user.application.port.in.processuser.ProcessOAuth2UserResponse;
import com.fenrir.colorme.user.application.port.in.processuser.ProcessOAuth2UserUseCase;
import com.fenrir.colorme.user.application.port.out.GetUserByEmailPort;
import com.fenrir.colorme.user.application.port.out.SaveUserPort;
import com.fenrir.colorme.user.application.port.out.GetUserByProviderDataPort;
import com.fenrir.colorme.user.domain.Provider;
import com.fenrir.colorme.user.domain.Role;
import com.fenrir.colorme.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
@Transactional
class ProcessOAuth2UserService implements ProcessOAuth2UserUseCase {
    private final GetUserByEmailPort getUserByEmailPort;
    private final GetUserByProviderDataPort getUserByProviderDataPort;
    private final SaveUserPort saveUserPort;
    private final UserMapper userMapper;

    @Override
    public ProcessOAuth2UserResponse processUser(ProcessOAuth2UserCommand command) {
        Optional<User> optionalUser = getUserByEmailPort.getUser(command.getEmail());

        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            return this.userMapper.toOAuth2UserResponse(user);
        }

        Provider provider = getProvider(command.getProvider());
        optionalUser = getUserByProviderDataPort.getUser(provider, command.getId());

        if (optionalUser.isPresent()) {
            final User user = updateEmail(optionalUser.get(), command);
            return this.userMapper.toOAuth2UserResponse(user);
        }

        return this.userMapper.toOAuth2UserResponse(saveUser(command));
    }

    private Provider getProvider(String providerName) {
        return Provider.getProvider(providerName).orElseThrow(RuntimeException::new);
    }

    private User updateEmail(User user, ProcessOAuth2UserCommand command) {
        user.setEmail(command.getEmail());
        return saveUserPort.saveUser(user);
    }

    private User saveUser(ProcessOAuth2UserCommand command) {
        final Provider provider = getProvider(command.getProvider());
        final User user = new User();
        user.setProvider(provider);
        user.setExternalId(command.getId());
        user.setExternalName(command.getName());
        user.setEmail(command.getEmail());
        user.setRole(Role.USER);
        return saveUserPort.saveUser(user);
    }
}
