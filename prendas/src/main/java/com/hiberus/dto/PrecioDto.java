package com.hiberus.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecioDto {
    private Integer id;
    private Double precio;
    private Integer idPrenda;
}
