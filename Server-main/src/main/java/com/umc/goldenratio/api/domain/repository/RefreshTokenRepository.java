package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserId(String userId);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
