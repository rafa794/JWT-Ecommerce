package com.hiberus.config;

public class JwtAuthConstants {
    public static final Integer TOKEN_EXPIRATION_DEFAULT = 1000 * 60 * 60 * 2; // 120 min
    public static final Integer REFRESH_TOKEN_EXPIRATION_DEFAULT = 1000 * 60 * 60; // 60min
    public static final String JWT_SIGNING_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

}