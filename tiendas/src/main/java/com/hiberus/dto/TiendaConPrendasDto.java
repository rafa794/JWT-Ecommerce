package com.hiberus.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TiendaConPrendasDto {
    private Integer id;
    private String nombre;
    private List<PrendaDto> prendas;
    private String ubicacion;
}
