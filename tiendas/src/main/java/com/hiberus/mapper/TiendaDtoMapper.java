package com.hiberus.mapper;

import com.hiberus.dto.TiendaConPrendasDto;
import com.hiberus.dto.TiendaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TiendaDtoMapper {

    TiendaConPrendasDto tiendaDtoToTiendaConPrendasDto(TiendaDto tienda);
}
