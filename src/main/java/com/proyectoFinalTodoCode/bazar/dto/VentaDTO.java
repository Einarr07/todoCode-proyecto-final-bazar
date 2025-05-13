package com.proyectoFinalTodoCode.bazar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class VentaDTO {

    private Long codigoVenta;
    private Double total;
    private int cantidadProductos;
    private String nombreCliente;
    private List<ProductoVendidoDTO> listaProductos;
}
