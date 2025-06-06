package com.proyectoFinalTodoCode.bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoStockDTO {

    private Long codigoProducto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidadDisponible;
}
