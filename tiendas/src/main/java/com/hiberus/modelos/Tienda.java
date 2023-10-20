package com.hiberus.modelos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tiendas")
@Entity
@Getter
@Setter
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty("Id de la tienda")
    private Integer id;

    @Setter
    @Column(name = "nombre")
    @ApiModelProperty("Nombre de la tienda")
    private String nombre;

    @Setter
    @Column(name = "ubicacion")
    @ApiModelProperty("Descripción de la ubicacion de la tienda")
    private String ubicacion;

    @Setter
    @ElementCollection
    @ApiModelProperty("Lista de prendas de id de prendas")
    private List<Integer> idPrendas = new ArrayList<>();

    public boolean checkTienda(String nombre, String ubicacion){
        if(nombre == null || ubicacion == null)
            return false;
        return !nombre.isBlank() && !ubicacion.isBlank();
    }

    public void agregarPrenda(Integer idPrenda){
        if(idPrenda != null)
            idPrendas.add(idPrenda);
    }

    public void eliminarPrenda(Integer idPrenda){
        if(idPrenda != null) {
            idPrendas.remove(idPrenda);
        }
    }
}
