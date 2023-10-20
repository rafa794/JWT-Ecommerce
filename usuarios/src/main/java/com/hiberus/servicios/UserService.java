package com.hiberus.servicios;

import com.hiberus.modelos.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public User findByUsername(String username) throws UsernameNotFoundException;

    public boolean existsByUsername(String username);

    public User saveUser(User user);
}
