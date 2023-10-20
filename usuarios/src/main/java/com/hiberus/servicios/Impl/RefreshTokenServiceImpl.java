package com.hiberus.servicios.Impl;

import com.hiberus.modelos.RefreshToken;
import com.hiberus.repositorios.RefreshTokenRepository;
import com.hiberus.servicios.RefreshTokenService;
import com.hiberus.servicios.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.hiberus.config.JwtAuthConstants.REFRESH_TOKEN_EXPIRATION_DEFAULT;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userService.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION_DEFAULT)) // 10 mins
                .build();
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin " +
                    "request");
        }
        return token;
    }


    public RefreshToken removeRefreshToken(RefreshToken token) {
        this.refreshTokenRepository.delete(token);
        return token;
    }

}
