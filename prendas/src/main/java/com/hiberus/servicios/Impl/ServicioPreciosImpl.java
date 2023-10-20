package com.hiberus.servicios.Impl;

import com.hiberus.dto.PrecioDto;
import com.hiberus.feign.PreciosCliente;
import com.hiberus.servicios.ServicioPrecios;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feign-precios")
@Slf4j
public class ServicioPreciosImpl implements ServicioPrecios {

    @Autowired
    PreciosCliente preciosCliente;

    @CircuitBreaker(name = "precios",fallbackMethod = "fallBackObtenerPrecioPorPrenda")
    @Override
    public PrecioDto obtenerPrecioPorPrenda(Integer idPrenda) {
        return preciosCliente.obtenerPrecioPorPrenda(idPrenda).getBody();
    }

    public PrecioDto fallBackObtenerPrecioPorPrenda(Integer idPrenda, Throwable exp) {
        return null;
    }

    @Override
    public String actualizarPrecio(PrecioDto precioDto) {
        return preciosCliente.actualizarPrecio(precioDto).getBody();
    }

    @Override
    public String crearPrecio(PrecioDto precioDto) {
        return preciosCliente.crearPrecio(precioDto).getBody();
    }

    @Override
    public void eliminarPrecio(Integer idPrecio) {
        preciosCliente.eliminarPrecio(idPrecio);
    }
}
