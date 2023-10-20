package com.hiberus.controladores;

import com.hiberus.dto.PrendaDto;
import com.hiberus.dto.TiendaConPrendasDto;
import com.hiberus.dto.TiendaDto;
import com.hiberus.mapper.TiendaDtoMapper;
import com.hiberus.modelos.Tienda;
import com.hiberus.servicios.ServicioPrendas;
import com.hiberus.servicios.ServicioTiendas;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/tiendas")
public class ControladorTienda {

    @Autowired
    ServicioTiendas servicioTiendas;

    @Autowired
    ServicioPrendas servicioPrendas;

    @Autowired
    TiendaDtoMapper tiendaDtoMapper;

    @ApiOperation("Obtiene una lista de tiendas disponibles")
    @GetMapping(value = "/obtenerTiendas")
    ResponseEntity<List<TiendaDto>> obtenerPrendas(){
        List<Tienda> listaTiendas = servicioTiendas.obtenerTiendas();
        List<TiendaDto> listaTiendasDto = new ArrayList<>();
        for(Tienda tienda : listaTiendas){
            TiendaDto tiendaDto = TiendaDto.builder()
                    .id(tienda.getId())
                    .nombre(tienda.getNombre())
                    .idPrendas(tienda.getIdPrendas())
                    .ubicacion(tienda.getUbicacion())
                    .build();
            listaTiendasDto.add(tiendaDto);
        }
        return new ResponseEntity<>(listaTiendasDto, HttpStatus.OK);
    }

    @ApiOperation("Agrega una tienda nueva a la lista de tiendas")
    @PostMapping("/agregarTienda")
    public ResponseEntity<TiendaDto> agregarTienda(@RequestBody TiendaDto tiendaDto) {
        if(tiendaDto.getId() != null)
            tiendaDto.setId(-1);
        TiendaDto tiendaDto1 = servicioTiendas.guardarTienda(tiendaDto);
        if(tiendaDto1 == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(tiendaDto1, HttpStatus.CREATED);
    }

    @ApiOperation("Actualiza los datos de una tienda por su id")
    @PutMapping("/actualizarTienda/{id}")
    public ResponseEntity<TiendaDto> actualizarTienda(@PathVariable Integer id, @RequestBody TiendaDto tiendaDto) {
        if (!Objects.equals(id, tiendaDto.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        TiendaDto tienda = servicioTiendas.obtenerTiendaPorId(id);
        if (tienda == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(tiendaDto.getNombre() != null)
            tienda.setNombre(tiendaDto.getNombre());
        if(tiendaDto.getUbicacion() != null)
            tienda.setUbicacion(tiendaDto.getUbicacion());

        return new ResponseEntity<>(servicioTiendas.actualizarTienda(tienda), HttpStatus.OK);
    }

    @ApiOperation("Elimina una tienda por su id")
    @DeleteMapping("/eliminarTienda/{id}")
    public ResponseEntity<String> eliminarTienda(@PathVariable Integer id) {
        servicioTiendas.eliminarTienda(id);
        return new ResponseEntity<>("Tienda eliminada con éxito.", HttpStatus.OK);
    }


    // TODO obtener lista de prendas de una tienda

    @ApiOperation("Obtiene una lista de prendas de una tienda")
    @GetMapping(value = "/obtenerTiendas/{id}")
    ResponseEntity<TiendaConPrendasDto> obtenerPrendas(@PathVariable Integer id){
        TiendaDto tienda = servicioTiendas.obtenerTiendaPorId(id);
        if(tienda == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        TiendaConPrendasDto tiendaFinalDto = tiendaDtoMapper.tiendaDtoToTiendaConPrendasDto(tienda);
        List<PrendaDto> prendas = new ArrayList<>();
        for(Integer prendaId: tienda.getIdPrendas()){
            PrendaDto prenda = servicioPrendas.obtenerPrenda(prendaId);
            if(prenda != null)
                prendas.add(prenda);
        }
        tiendaFinalDto.setPrendas(prendas);
        return new ResponseEntity<>(tiendaFinalDto, HttpStatus.OK);
    }

    @ApiOperation("Agregar prenda a tienda")
    @PutMapping("/actualizarPrendasTienda/{id}/{idPrenda}")
    public ResponseEntity<TiendaDto> agregarPrendaTienda(@PathVariable Integer id, @PathVariable Integer idPrenda) {
        PrendaDto prendaDto = servicioPrendas.obtenerPrenda(idPrenda);
        if (prendaDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        TiendaDto tiendaDto = servicioTiendas.agregarPrendaATienda(id, idPrenda);
        if (tiendaDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tiendaDto, HttpStatus.OK);
    }

    @ApiOperation("Eliminar prenda de una tienda")
    @PutMapping("/eliminarPrendasTienda/{id}/{idPrenda}")
    public ResponseEntity<TiendaDto> eliminarPrendaTienda(@PathVariable Integer id, @PathVariable Integer idPrenda) {
        TiendaDto tiendaDto = servicioTiendas.eliminarPrendaATienda(id, idPrenda);
        if (tiendaDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tiendaDto, HttpStatus.OK);
    }
}
