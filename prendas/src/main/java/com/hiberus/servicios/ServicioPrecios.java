package com.hiberus.servicios;

import com.hiberus.dto.PrecioDto;

public interface ServicioPrecios {

    PrecioDto obtenerPrecioPorPrenda(Integer idPrenda);

    String actualizarPrecio(PrecioDto precioDto);

    String crearPrecio(PrecioDto precioDto);

    void eliminarPrecio(Integer idPrecio);
}
