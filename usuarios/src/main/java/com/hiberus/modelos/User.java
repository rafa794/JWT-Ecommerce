package com.hiberus.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@Table(name = "APPLICATION_USER")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * User username
     */
    @Column(name = "USERNAME", length = 50, nullable = false)
    private String username;

    /**
     * User password
     */
    @Column(name = "PASSWORD", nullable = false)
    @JsonIgnoreProperties
    private String password;

    /**
     * Flag that indicates whether user is enabled or not.
     */
    @Column(name = "ENABLED")
    private boolean enabled;

    /**
     * Flag that indicates whether credentials are expired or not.
     */
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired;

    /**
     * Flag that indicates whether account are expired or not.
     */
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private boolean accountNonExpired;

    /**
     * Flag that indicates whether account is expired or not.
     */
    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}