package com.hiberus.servicios.Impl;

import com.hiberus.dto.PrendaDto;
import com.hiberus.feign.PrendasCliente;
import com.hiberus.servicios.ServicioPrendas;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feign-prendas")
public class ServicioPrendasImpl implements ServicioPrendas {

    @Autowired
    PrendasCliente prendasCliente;


    @CircuitBreaker(name = "prendas", fallbackMethod = "fallBackObtenerPrenda")
    @Override
    public PrendaDto obtenerPrenda(Integer idPrenda) {
        try{
            return prendasCliente.obtenerPrenda(idPrenda).getBody();
        } catch (Exception e){
            return null;
        }
    }

    public PrendaDto fallBackObtenerPrenda(Integer idPrenda, Throwable exp) {
        return null;
    }
}
