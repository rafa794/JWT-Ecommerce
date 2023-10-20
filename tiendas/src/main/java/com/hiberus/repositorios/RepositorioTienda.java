package com.hiberus.repositorios;

import com.hiberus.modelos.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioTienda extends JpaRepository<Tienda,Integer> {

}
