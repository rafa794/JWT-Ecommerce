package com.hiberus.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TiendaDto {
    private Integer id;
    private String nombre;
    private List<Integer> idPrendas = new ArrayList<>();
    private String ubicacion;
}
