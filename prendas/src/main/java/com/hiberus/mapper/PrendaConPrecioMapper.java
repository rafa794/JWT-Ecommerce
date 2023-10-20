package com.hiberus.mapper;

import com.hiberus.dto.PrendaConPrecioDto;
import com.hiberus.modelos.Prenda;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrendaConPrecioMapper {

    PrendaConPrecioDto prendaDtoToPrendaConPrecioDto(Prenda prenda);

    Prenda prendaConPrecioDtoToPrendaDto(PrendaConPrecioDto prendaConPrecioDto);

    List<PrendaConPrecioDto> prendaListDtoToPrendaConPrecioDtoList(List<Prenda> prenda);
}
