package com.hiberus.repositorios;

import com.hiberus.modelos.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPrendas extends JpaRepository<Prenda,Integer> {
}
