package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.dto.MayorVentaDTO;
import com.proyectoFinalTodoCode.bazar.dto.VentaProductosDTO;
import com.proyectoFinalTodoCode.bazar.dto.VentasDelDiaDTO;
import com.proyectoFinalTodoCode.bazar.entity.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    public List<Venta> getAllVentas();

    public Venta findVentaById(Long id);

    public Venta crearVenta(Venta venta);

    public void saveVenta(Venta venta);

    public Venta updateVenta(Long codigoVenta, Venta venta);

    public void deleteVenta(Long id);

    public void procesarVenta(Venta venta);

    public VentaProductosDTO obtenerProductosDeVenta(Long codigoVenta);
    public VentasDelDiaDTO obetenerResumenVentaPorDia(LocalDate fecha);
    public MayorVentaDTO obtenerMayorVenta();
}
