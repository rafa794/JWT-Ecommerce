package com.hiberus.servicios;

import com.hiberus.modelos.Precio;
import org.springframework.stereotype.Service;

import com.hiberus.modelos.Precio;
import java.util.List;

public interface ServicioPrecios {
    List<Precio> obtenerPrecios();
    void crearPrecio(Precio precio);

    void actualizarPrecioPorPrenda(Precio precio);
    void eliminarPrecio(Integer idPrecio);
    Precio obtenerPrecioPorPrenda(Integer idPrenda);
}
