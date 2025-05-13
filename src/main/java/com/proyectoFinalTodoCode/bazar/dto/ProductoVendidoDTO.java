package com.proyectoFinalTodoCode.bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoVendidoDTO {

    private Long codigoProducto;
    private String nombreProducto;
    private String marcaProducto;
    private Double costo;

    public ProductoVendidoDTO(Long codigoProducto, String nombreProducto, String marcaProducto, Double costo) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.costo = costo;
    }

}
