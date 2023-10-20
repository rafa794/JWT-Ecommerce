package com.hiberus.controladores;

import com.hiberus.config.JwtUtils;
import com.hiberus.modelos.*;
import com.hiberus.servicios.RefreshTokenService;
import com.hiberus.servicios.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(UserController.Mappings.AUTH)
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder encoder;

    @PostMapping(Mappings.AUTH_REGISTER)
    public ResponseEntity<String> signup(@RequestBody AuthenticationRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        this.userService.saveUser(user);
        return ResponseEntity.ok("User created!");
    }

    @PostMapping(Mappings.AUTH_LOGIN)
    public ResponseEntity<JwtResponse> authenticate(@RequestBody AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            return ResponseEntity.ok(JwtResponse.builder().accessToken(jwtUtils.generateToken(userDetails)).token(refreshTokenService.createRefreshToken(userDetails.getUsername()).getToken()).build());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
    @PostMapping(Mappings.AUTH_TOKEN)
    public ResponseEntity<String> getToken(@RequestBody AuthenticationRequest request) {
        System.out.println("request:");
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authenticate.isAuthenticated()) {
            final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            return new ResponseEntity<>(jwtUtils.generateToken(userDetails), HttpStatus.CREATED);
        } else {
            throw new RuntimeException("invalid access");
        }
    }
    @GetMapping(Mappings.AUTH_VALIDATE)
    public String validateToken(@RequestParam("token") String token) {
        jwtUtils.validateToken(token);
        return "Token is valid";
    }

    @PostMapping(Mappings.AUTH_REFRESH_TOKEN)
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(refreshTokenService.findByToken(refreshTokenRequest.getToken()).map(refreshTokenService::verifyExpiration).map(RefreshToken::getUser).map(user -> {
            String accessToken = jwtUtils.generateToken(user);
            return JwtResponse.builder().accessToken(accessToken).token(refreshTokenRequest.getToken()).build();
        }).orElseThrow(() -> new RuntimeException(("Refresh token is not in database!"))));
    }

    @PostMapping(Mappings.AUTH_LOGOUT)
    public ResponseEntity<RefreshToken> logout(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(this.refreshTokenService.removeRefreshToken(refreshTokenService.findByToken(refreshTokenRequest.getToken()).get()));
    }

    static class Mappings {
        public static final String AUTH = "/auth";
        public static final String AUTH_LOGIN = "/signin";

        public static final String AUTH_REGISTER = "/register";
        public static final String AUTH_TOKEN = "/token";
        public static final String AUTH_VALIDATE = "/validate";


        // NO ES NECESARIO
        public static final String AUTH_LOGOUT = "/signout";
        public static final String AUTH_REFRESH_TOKEN = "/refreshToken";

    }
}
