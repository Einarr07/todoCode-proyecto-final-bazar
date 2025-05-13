package com.proyectoFinalTodoCode.bazar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoProducto;

    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidadDisponible;

    public Producto() {

    }

    public Producto(Long codigoProducto, String nombre, String marca, Double costo, Double cantidadDisponible) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidadDisponible = cantidadDisponible;
    }
}
