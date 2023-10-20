package com.hiberus.controladores;

import com.hiberus.dto.PrecioDto;
import com.hiberus.modelos.Precio;
import com.hiberus.servicios.ServicioPrecios;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/precios")
public class ControladorPrecio {

    @Autowired
    ServicioPrecios servicioPrecios;

    @ApiOperation("Obtiene la lista de precios")
    @GetMapping(value = "/obtenerPrecios")
    ResponseEntity<List<PrecioDto>> obtenerPrecios(){
        List<Precio> listaPrecios = servicioPrecios.obtenerPrecios();
        List<PrecioDto> listaPreciosDto = new ArrayList<>();
        for(Precio precio : listaPrecios){
            PrecioDto precioDto = PrecioDto.builder()
                    .id(precio.getId())
                    .precio(precio.getPrecio())
                    .idPrenda(precio.getIdPrenda())
                    .build();
            listaPreciosDto.add(precioDto);
        }
        return new ResponseEntity<>(listaPreciosDto, HttpStatus.OK);
    }

    @ApiOperation("Agrega o actualiza el precio")
    @PostMapping(value = "/crearPrecio")
    public ResponseEntity<String> crearPrecio(@RequestBody Precio precio) {
        servicioPrecios.crearPrecio(precio);
        return new ResponseEntity<>("Precio agregado con éxito", HttpStatus.CREATED);
    }

    @ApiOperation("Agrega o actualiza el precio")
    @PutMapping(value = "/actualizarPrecio")
    public ResponseEntity<String> actualizarPrecio(@RequestBody Precio precio) {
        servicioPrecios.actualizarPrecioPorPrenda(precio);
        return new ResponseEntity<>("Precio agregado con éxito", HttpStatus.CREATED);
    }



    @ApiOperation("Elimina un precio por su id de precio")
    @DeleteMapping(value = "/eliminarPrecio/{idPrecio}")
    public ResponseEntity<String> eliminarPrecio(@PathVariable Integer idPrecio) {
        // Supongo que tu servicio tiene un método para eliminar el precio por su ID.
        servicioPrecios.eliminarPrecio(idPrecio);
        return new ResponseEntity<>("Precio eliminado con éxito", HttpStatus.OK);
    }

    @ApiOperation("Obtiene un precio por prenda")
    @GetMapping(value = "/precioPorPrenda/{idPrenda}")
    public ResponseEntity<PrecioDto> obtenerPrecioPorPrenda(@PathVariable Integer idPrenda) {
        Precio precio = servicioPrecios.obtenerPrecioPorPrenda(idPrenda);
        if (precio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PrecioDto precioDto = PrecioDto.builder()
                .id(precio.getId())
                .precio(precio.getPrecio())
                .idPrenda(precio.getIdPrenda())
                .build();
        return new ResponseEntity<>(precioDto, HttpStatus.OK);
    }


}
