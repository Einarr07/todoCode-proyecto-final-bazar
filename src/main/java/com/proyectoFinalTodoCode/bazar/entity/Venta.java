package com.proyectoFinalTodoCode.bazar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoVenta;

    private LocalDate fechaVenta;
    private Double total;

    @OneToMany
    private List<Producto> listaProductos;

    @OneToOne
    private Cliente cliente;
}
