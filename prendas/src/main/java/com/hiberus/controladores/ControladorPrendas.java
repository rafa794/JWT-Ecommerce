package com.hiberus.controladores;

import com.hiberus.dto.PrecioDto;
import com.hiberus.dto.PrendaConPrecioDto;
import com.hiberus.servicios.ServicioPrecios;
import com.hiberus.servicios.ServicioPrendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/prendas")
public class ControladorPrendas {

    @Autowired
    ServicioPrendas servicioPrendas;

    @Autowired
    ServicioPrecios servicioPrecios;

    @GetMapping
    public ResponseEntity<List<PrendaConPrecioDto>> obtenerPrendas() {
        List<PrendaConPrecioDto> prendas = servicioPrendas.obtenerPrendas();
        for(PrendaConPrecioDto prenda: prendas) {
            PrecioDto precio = servicioPrecios.obtenerPrecioPorPrenda(prenda.getId());
            prenda.setPrecio(precio.getPrecio());
        }
        return new ResponseEntity<>(prendas, HttpStatus.OK);
    }

    @GetMapping("/{idPrenda}")
    @ApiOperation("Obtiene prenda por su id")
    public ResponseEntity<PrendaConPrecioDto> obtenerPrendaPorId(@PathVariable Integer idPrenda) {
        PrendaConPrecioDto prenda = servicioPrendas.obtenerPrendaPorId(idPrenda);
        // TODO Feign para cada prenda sacar su precio
        if (prenda == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PrecioDto precio = servicioPrecios.obtenerPrecioPorPrenda(prenda.getId());
        prenda.setPrecio(precio.getPrecio());
        return new ResponseEntity<>(prenda, HttpStatus.OK);
    }


    @ApiOperation("Agrega una prenda a la lista de prendas")
    @PostMapping
    public ResponseEntity<PrendaConPrecioDto> agregarPrenda(@RequestBody PrendaConPrecioDto prenda) {
        try {
            PrendaConPrecioDto prendaConPrecioDto = servicioPrendas.crearPrenda(prenda);
            if(prendaConPrecioDto == null)
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            return new ResponseEntity<>(prenda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Modifica una prenda por su id")
    @PutMapping("/{idPrenda}")
    public ResponseEntity<PrendaConPrecioDto> actualizarPrenda(@PathVariable Integer idPrenda, @RequestBody PrendaConPrecioDto prenda) {
        if (!idPrenda.equals(prenda.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(servicioPrendas.actualizarPrenda(prenda), HttpStatus.OK);
    }

    @ApiOperation("Borra una prenda por su id")
    @DeleteMapping("/{idPrenda}")
    public ResponseEntity<Void> eliminarPrenda(@PathVariable Integer idPrenda) {
        servicioPrendas.eliminarPrenda(idPrenda);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
