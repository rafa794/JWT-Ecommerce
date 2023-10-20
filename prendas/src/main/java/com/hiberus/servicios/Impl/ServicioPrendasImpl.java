package com.hiberus.servicios.Impl;

import com.hiberus.dto.PrecioDto;
import com.hiberus.dto.PrendaConPrecioDto;
import com.hiberus.mapper.PrendaConPrecioMapper;
import com.hiberus.modelos.Prenda;
import com.hiberus.repositorios.RepositorioPrendas;
import com.hiberus.servicios.ServicioPrecios;
import com.hiberus.servicios.ServicioPrendas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServicioPrendasImpl implements ServicioPrendas {

    @Autowired
    RepositorioPrendas repositorioPrendas;

    @Autowired
    PrendaConPrecioMapper prendaConPrecioMapper;

    @Autowired
    ServicioPrecios servicioPrecios;

    @Override
    public List<PrendaConPrecioDto> obtenerPrendas() {
        return prendaConPrecioMapper.prendaListDtoToPrendaConPrecioDtoList(repositorioPrendas.findAll());
    }

    @Override
    public PrendaConPrecioDto obtenerPrendaPorId(Integer idPrenda) {
        if(repositorioPrendas.findById(idPrenda).isPresent()) {
            return prendaConPrecioMapper.prendaDtoToPrendaConPrecioDto(repositorioPrendas.findById(idPrenda).get());
        }
        return null;
    }

    @Override
    public PrendaConPrecioDto actualizarPrenda(PrendaConPrecioDto prendaConPrecioDto) {
        if(repositorioPrendas.findById(prendaConPrecioDto.getId()).isPresent()) {
            Prenda prenda = repositorioPrendas.findById(prendaConPrecioDto.getId()).get();
            Prenda prendaDto = prendaConPrecioMapper.prendaConPrecioDtoToPrendaDto(prendaConPrecioDto);
            if(prendaDto.getNombre() != null)
                prenda.setNombre(prendaDto.getNombre());
            if(prendaDto.getTalla() != null)
                prenda.setTalla(prendaDto.getTalla());
            if(prendaDto.getColor() != null)
                prenda.setColor(prendaDto.getColor());
            repositorioPrendas.save(prenda);
            PrecioDto precio = servicioPrecios.obtenerPrecioPorPrenda(prenda.getId());
            precio.setPrecio(prendaConPrecioDto.getPrecio());
            servicioPrecios.actualizarPrecio(precio);
            return prendaConPrecioMapper.prendaDtoToPrendaConPrecioDto(prenda);
        }
        return null;
    }

    @Override
    public PrendaConPrecioDto crearPrenda(PrendaConPrecioDto prendaConPrecioDto) {
        prendaConPrecioDto.setId(null);
        Prenda prenda = prendaConPrecioMapper.prendaConPrecioDtoToPrendaDto(prendaConPrecioDto);
        if(!prenda.checkPrenda(prendaConPrecioDto.getNombre(), prendaConPrecioDto.getTalla(), prendaConPrecioDto.getColor()))
            return null;
        repositorioPrendas.save(prenda);
        servicioPrecios.crearPrecio(new PrecioDto(null, prendaConPrecioDto.getPrecio(), prenda.getId()));
        return prendaConPrecioMapper.prendaDtoToPrendaConPrecioDto(prenda);
    }

    @Override
    public void eliminarPrenda(Integer idPrenda) {
        if(servicioPrecios.obtenerPrecioPorPrenda(idPrenda) != null){
            PrecioDto precio = servicioPrecios.obtenerPrecioPorPrenda(idPrenda);
            servicioPrecios.eliminarPrecio(precio.getId());
        }
        repositorioPrendas.deleteById(idPrenda);
    }


}
