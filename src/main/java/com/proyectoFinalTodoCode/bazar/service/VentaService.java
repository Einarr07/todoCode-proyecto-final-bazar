package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.entity.Producto;
import com.proyectoFinalTodoCode.bazar.entity.Venta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyectoFinalTodoCode.bazar.repository.IProductoRepository;
import com.proyectoFinalTodoCode.bazar.repository.IVentaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta findVentaById(Long id) {
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada ID: " + id));
    }

    @Override
    @Transactional
    public Venta crearVentaConValidacion(Venta venta) {
        if (venta.getListaProductos() == null || venta.getListaProductos().isEmpty()) {
            throw new RuntimeException("La venta debe contener al menos 1 producto");
        }

        if (venta.getCliente() == null) {
            throw new RuntimeException("La venta debe estar asociado al cliente");
        }

        verificarDisponibilidadStock(venta.getListaProductos());

        actulizarStock(venta.getListaProductos());

        calcularTotalVenta(venta);

        venta.setFechaVenta(LocalDate.now());

        return ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public void saveVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public Venta updateVenta(Venta venta) {

        if(!ventaRepository.existsById(venta.getCodigoVenta())){
            throw new RuntimeException("Venta no encontrada ID: " + venta.getCodigoVenta());
        }

        return ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    private void verificarDisponibilidadStock(List<Producto> productos) {
        for (Producto productoVenta : productos) {
            Producto productoDB = productoRepository.findById(productoVenta.getCodigoProducto()).orElseThrow(
                    () -> new RuntimeException("Producto no encontrada ID: " + productoVenta.getCodigoProducto()));

            if (productoDB.getCantidadDisponible() < 1){
                throw new RuntimeException("Stock insuficiente para el producto: " + productoDB.getNombre() +
                        ". Disponible: " + productoDB.getCodigoProducto());
            }
        }
    }

    private void actulizarStock(List<Producto> productos) {
        for (Producto productoVenta : productos) {
            Producto productoDB = productoRepository.findById(productoVenta.getCodigoProducto()).get();

            Double cantidad = productoVenta.getCantidadVendida() != null ? productoVenta.getCantidadVendida() : 1;
            productoDB.setCantidadDisponible(productoDB.getCantidadDisponible() - cantidad);
            productoRepository.save(productoDB);
        }
    }

    private void calcularTotalVenta(Venta venta) {
        double total = venta.getListaProductos().stream()
                .mapToDouble(producto -> {
                    Producto productoDB = productoRepository.findById(producto.getCodigoProducto()).get();
                    return productoDB.getCosto();
                })
                .sum();

        venta.setTotal(total);
    }
}
