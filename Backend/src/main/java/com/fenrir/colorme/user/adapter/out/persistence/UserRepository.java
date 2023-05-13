package com.fenrir.colorme.user.adapter.out.persistence;

import com.fenrir.colorme.user.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> getUserEntityByEmail(String email);
    Optional<UserEntity> getUserEntityByProviderAndExternalId(Provider provider, String externalId);
}
