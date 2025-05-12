package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.entity.Venta;

import java.util.List;

public interface IVentaService {

    public List<Venta> getAllVentas();

    public Venta findVentaById(Long id);

    public Venta crearVentaConValidacion(Venta venta);

    public void saveVenta(Venta venta);

    public Venta updateVenta(Venta venta);

    public void deleteVenta(Long id);
}
