package com.hiberus.repositorios;

import com.hiberus.modelos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    /**
     * Finds a User
     *
     * @param username
     * @return true if exists, false if not.
     */
    boolean existsByUsername(String username);
}
