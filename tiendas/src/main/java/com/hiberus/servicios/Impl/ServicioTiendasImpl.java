package com.hiberus.servicios.Impl;

import com.hiberus.dto.TiendaDto;
import com.hiberus.mapper.TiendaMapper;
import com.hiberus.modelos.Tienda;
import com.hiberus.repositorios.RepositorioTienda;
import com.hiberus.servicios.ServicioTiendas;
import com.netflix.discovery.converters.Auto;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioTiendasImpl implements ServicioTiendas {

    @Autowired
    RepositorioTienda repositorioTienda;

    @Autowired
    TiendaMapper tiendaMapper;

    @Override
    public List<Tienda> obtenerTiendas() {
        return repositorioTienda.findAll();
    }

    @Override
    public TiendaDto guardarTienda(TiendaDto tienda) {
        tienda.setIdPrendas(new ArrayList<Integer>());
        Tienda tien = tiendaMapper.tiendaDtoToTienda(tienda);
        if(!tien.checkTienda(tienda.getNombre(), tienda.getUbicacion()))
            return null;
        return tiendaMapper.tiendaToTiendaDto(repositorioTienda.save(tien));
    }

    @Override
    public TiendaDto actualizarTienda(TiendaDto tienda) {
        Tienda tien = tiendaMapper.tiendaDtoToTienda(tienda);
        return tiendaMapper.tiendaToTiendaDto(repositorioTienda.save(tien));
    }
    @Override
    public TiendaDto obtenerTiendaPorId(Integer id) {
        if(repositorioTienda.findById(id).isPresent()) {
            return tiendaMapper.tiendaToTiendaDto(repositorioTienda.findById(id).get());
        }
        return null;
    }

    @Override
    public void eliminarTienda(Integer id) {
        repositorioTienda.deleteById(id);
    }

    @Override
    public TiendaDto agregarPrendaATienda(Integer id, Integer idPrenda) {
        if(repositorioTienda.findById(id).isPresent()) {
            Tienda tienda = repositorioTienda.findById(id).get();
            if(!tienda.getIdPrendas().contains(idPrenda)) {
                tienda.agregarPrenda(idPrenda);
                repositorioTienda.save(tienda);
            }
            return tiendaMapper.tiendaToTiendaDto(tienda);
        }
        return null;
    }

    @Override
    public TiendaDto eliminarPrendaATienda(Integer id, Integer idPrenda) {
        if(repositorioTienda.findById(id).isPresent()) {
            Tienda tienda = repositorioTienda.findById(id).get();
            tienda.eliminarPrenda(idPrenda);
            repositorioTienda.save(tienda);
            return tiendaMapper.tiendaToTiendaDto(tienda);
        }
        return null;
    }
}
