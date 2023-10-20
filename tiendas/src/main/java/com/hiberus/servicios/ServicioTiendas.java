package com.hiberus.servicios;

import com.hiberus.dto.TiendaDto;
import com.hiberus.modelos.Tienda;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ServicioTiendas {

    List<Tienda> obtenerTiendas();
    TiendaDto guardarTienda(TiendaDto tienda);

    TiendaDto actualizarTienda(TiendaDto tienda);
    TiendaDto obtenerTiendaPorId(Integer id);
    void eliminarTienda(Integer id);

    TiendaDto agregarPrendaATienda(Integer id, Integer idPrenda);

    TiendaDto eliminarPrendaATienda(Integer id, Integer idPrenda);


}
