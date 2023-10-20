package com.hiberus.modelos;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prendas")
@Entity
@Getter
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("Id de la prenda")
    @Column(name = "id")
    private Integer id;
    @Setter
    @ApiModelProperty("Nombre de la prenda")
    @Column(name = "nombre")
    private String nombre;
    @Setter
    @ApiModelProperty("Talla de la prenda")
    @Column(name = "talla")
    private String talla;
    @Setter
    @ApiModelProperty("Color de la prenda")
    @Column(name = "color")
    private String color;

    public boolean checkPrenda(String nombre, String talla, String color){
        if(nombre == null || talla == null || color == null)
            return false;
        return !nombre.isBlank() && !talla.isBlank() && !color.isBlank();
    }
}
