package com.hiberus.servicios;

import com.hiberus.dto.PrendaConPrecioDto;
import com.hiberus.modelos.Prenda;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioPrendas {
    List<PrendaConPrecioDto> obtenerPrendas();
    PrendaConPrecioDto obtenerPrendaPorId(Integer idPrenda);
    PrendaConPrecioDto actualizarPrenda(PrendaConPrecioDto prenda);

    PrendaConPrecioDto crearPrenda(PrendaConPrecioDto prenda);
    void eliminarPrenda(Integer id);

}
