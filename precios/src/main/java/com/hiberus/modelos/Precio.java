package com.hiberus.modelos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "precios")
@Entity
@Getter
@Setter
public class Precio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("Id del precio")
    @Column(name = "id")
    private Integer id;
    @Setter
    @ApiModelProperty("Precio de la prenda")
    @Column(name = "precio")
    private Double precio;
    @ApiModelProperty("Id de la prenda")
    @Column(name = "idPrenda")
    private Integer idPrenda;

}
