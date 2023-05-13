package com.fenrir.colorme.user.adapter.out.persistence;

import com.fenrir.colorme.common.annotation.PersistenceAdapter;
import com.fenrir.colorme.user.application.port.out.SaveUserPort;
import com.fenrir.colorme.user.application.port.out.GetUserByEmailPort;
import com.fenrir.colorme.user.application.port.out.GetUserByProviderDataPort;
import com.fenrir.colorme.user.domain.Provider;
import com.fenrir.colorme.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
class UserPersistenceAdapter implements GetUserByEmailPort, GetUserByProviderDataPort, SaveUserPort {
    private final UserRepository userRepository;
    private final UserEntityMapper userMapper;

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.getUserEntityByEmail(email).map(userMapper::toUser);
    }

    @Override
    public Optional<User> getUser(Provider provider, String externalId) {
        return userRepository.getUserEntityByProviderAndExternalId(provider, externalId).map(userMapper::toUser);
    }

    @Override
    public User saveUser(User user) {
        final UserEntity userEntity = userMapper.toUserEntity(user);
        userRepository.save(userEntity);
        return userMapper.toUser(userEntity);
    }
}
