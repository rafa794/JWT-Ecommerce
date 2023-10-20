package com.hiberus.servicios.Impl;

import com.hiberus.modelos.Precio;
import com.hiberus.repositorios.RepositorioPrecios;
import com.hiberus.servicios.ServicioPrecios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioPreciosImpl implements ServicioPrecios {

    @Autowired
    RepositorioPrecios repositorioPrecios;

    @Override
    public List<Precio> obtenerPrecios() {
        return repositorioPrecios.findAll();
    }

    @Override
    public void crearPrecio(Precio precio) {
        precio.setId(null);
        repositorioPrecios.save(precio);
    }

    @Override
    public void actualizarPrecioPorPrenda(Precio precio) {
        if(repositorioPrecios.findByIdPrenda(precio.getIdPrenda()).isPresent())
            repositorioPrecios.save(precio);
    }

    @Override
    public void eliminarPrecio(Integer idPrecio) {
        repositorioPrecios.deleteById(idPrecio);
    }

    @Override
    public Precio obtenerPrecioPorPrenda(Integer idPrenda) {
        Optional<Precio> resultado = repositorioPrecios.findByIdPrenda(idPrenda);  // Corrección aquí
        return resultado.orElse(null);
    }
}
