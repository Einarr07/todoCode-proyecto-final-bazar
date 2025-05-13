package com.proyectoFinalTodoCode.bazar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class VentaProductosDTO {
    private Long codigoVenta;
    private List<ProductoStockDTO> productoStock;
}
