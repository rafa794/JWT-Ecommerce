package com.hiberus.servicios;

import com.hiberus.modelos.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    public RefreshToken createRefreshToken(String username);

    public Optional<RefreshToken> findByToken(String token);

    public RefreshToken verifyExpiration(RefreshToken token);


    public RefreshToken removeRefreshToken(RefreshToken token);
}
