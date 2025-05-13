package com.proyectoFinalTodoCode.bazar.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class VentasDelDiaDTO {
    private LocalDate fecha;
    private Integer catidadVentas;
    private Double montoTotal;
}
