package com.hiberus.feign;

import com.hiberus.dto.PrendaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("prendas")
public interface PrendasCliente {

    @GetMapping(value = "/prendas/{idPrenda}")
    ResponseEntity<PrendaDto> obtenerPrenda(@PathVariable Integer idPrenda);
}
