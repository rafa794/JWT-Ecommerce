package com.hiberus.feign;

import com.hiberus.dto.PrecioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "precios")
public interface PreciosCliente {

    @GetMapping(value = "/precios/precioPorPrenda/{idPrenda}")
    ResponseEntity<PrecioDto> obtenerPrecioPorPrenda(@PathVariable Integer idPrenda);

    @PutMapping(value = "/precios/actualizarPrecio")
    ResponseEntity<String> actualizarPrecio(@RequestBody PrecioDto precioDto);
    @PostMapping(value = "/precios/crearPrecio")
    ResponseEntity<String> crearPrecio(@RequestBody PrecioDto precioDto);

    @DeleteMapping(value = "/precios/eliminarPrecio/{idPrecio}")
    ResponseEntity<Void> eliminarPrecio(@PathVariable Integer idPrecio);
}
