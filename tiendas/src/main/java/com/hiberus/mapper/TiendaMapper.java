package com.hiberus.mapper;

import com.hiberus.dto.TiendaDto;
import com.hiberus.modelos.Tienda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TiendaMapper {

    TiendaDto tiendaToTiendaDto(Tienda tienda);

    Tienda tiendaDtoToTienda(TiendaDto tiendaDto);
}
