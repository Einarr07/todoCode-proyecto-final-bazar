package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.dto.*;
import com.proyectoFinalTodoCode.bazar.entity.Cliente;
import com.proyectoFinalTodoCode.bazar.entity.Producto;
import com.proyectoFinalTodoCode.bazar.entity.Venta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyectoFinalTodoCode.bazar.repository.IVentaRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IClienteService clienteService;

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta findVentaById(Long id) {
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada ID: " + id));
    }

    @Override
    public Venta crearVenta(Venta venta) {
        // Validar que la venta tenga productos
        if (venta.getListaProductos() == null || venta.getListaProductos().isEmpty()) {
            throw new IllegalArgumentException("La venta debe contener al menos un producto");
        }

        // Validar que tenga cliente asociado
        if (venta.getCliente() == null || venta.getCliente().getIdCliente() == null) {
            throw new IllegalArgumentException("La venta debe tener un cliente asociado");
        }

        // Obtener productos completos de la base de datos
        List<Producto> productosCompletos = venta.getListaProductos().stream()
                .map(producto -> productoService.getProductoById(producto.getCodigoProducto()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Validar que todos los productos existan
        if (productosCompletos.size() != venta.getListaProductos().size()) {
            throw new IllegalArgumentException("Uno o más productos no existen");
        }

        // Calcular el total
        double total = productosCompletos.stream()
                .mapToDouble(Producto::getCosto)
                .sum();

        // Actualizar la venta con los datos correctos
        venta.setListaProductos(productosCompletos);
        venta.setTotal(total);
        venta.setFechaVenta(LocalDate.now()); // Opcional: usar fecha actual si no viene especificada

        // Procesar venta (actualizar stocks)
        procesarVenta(venta);

        return ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public void saveVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public Venta updateVenta(Long codigoVenta, Venta venta) {
        Venta ven = this.findVentaById(codigoVenta);

        // Actualizar solo campos modificables
        ven.setFechaVenta(venta.getFechaVenta());

        // Cargar productos completos desde la base de datos
        List<Producto> productosCompletos = venta.getListaProductos().stream()
                .map(p -> productoService.getProductoById(p.getCodigoProducto()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        ven.setListaProductos(productosCompletos);

        // Cargar cliente completo
        Cliente clienteCompleto = clienteService.findClienteById(venta.getCliente().getIdCliente());
        ven.setCliente(clienteCompleto);

        // Recalcular total
        double nuevoTotal = productosCompletos.stream()
                .mapToDouble(Producto::getCosto)
                .sum();
        ven.setTotal(nuevoTotal);

        return ventaRepository.save(ven);
    }

    @Override
    @Transactional
    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public void procesarVenta(Venta venta) {
        venta.getListaProductos().forEach(producto -> {
            productoService.actualizarStock(producto.getCodigoProducto(), 1.0);
        });
    }

    @Override
    public VentaProductosDTO obtenerProductosDeVenta(Long codigoVenta) {
        Venta venta = findVentaById(codigoVenta);
        if (venta == null) {
            throw new IllegalArgumentException("Venta no encontrada");
        }

        VentaProductosDTO dto = new VentaProductosDTO();
        dto.setCodigoVenta(venta.getCodigoVenta());

        List<ProductoStockDTO> productosDTO = venta.getListaProductos().stream()
                .map(p -> {
                    ProductoStockDTO productoDTO = new ProductoStockDTO();
                    productoDTO.setCodigoProducto(p.getCodigoProducto());
                    productoDTO.setNombre(p.getNombre());
                    productoDTO.setMarca(p.getMarca());
                    productoDTO.setCosto(p.getCosto());
                    productoDTO.setCantidadDisponible(p.getCantidadDisponible());
                    return productoDTO;
                })
                .collect(Collectors.toList());

        dto.setProductoStock(productosDTO);
        return dto;
    }

    @Override
    public VentasDelDiaDTO obetenerResumenVentaPorDia(LocalDate fecha) {
        List<Venta> ventasDelDia = ventaRepository.findAllByFechaVenta(fecha);

        VentasDelDiaDTO dto = new VentasDelDiaDTO();
        dto.setFecha(fecha);
        dto.setCatidadVentas(ventasDelDia.size());
        dto.setMontoTotal(ventasDelDia.stream()
                .mapToDouble(Venta::getTotal)
                .sum());

        return dto;
    }

    @Override
    public MayorVentaDTO obtenerMayorVenta() {
        List<Venta> ventas = ventaRepository.findAll();
        if (ventas.isEmpty()) {
            throw new IllegalStateException("No hay ventas registradas");
        }

        Venta mayorVenta = ventas.stream()
                .max(Comparator.comparingDouble(Venta::getTotal))
                .orElseThrow(() -> new IllegalStateException("No se pudo determinar la mayor venta"));

        // Mapear a MayorVentaDTO
        MayorVentaDTO dto = new MayorVentaDTO();
        dto.setCodigoVenta(mayorVenta.getCodigoVenta());
        dto.setTotal(mayorVenta.getTotal());
        dto.setCantidadProductos(mayorVenta.getListaProductos().size());
        dto.setNombreCliente(mayorVenta.getCliente().getNombre());
        dto.setApellidoCliente(mayorVenta.getCliente().getApellido());

        // Mapear productos si es necesario
        List<ProductoStockDTO> productosDTO = mayorVenta.getListaProductos().stream()
                .map(p -> {
                    ProductoStockDTO productoDTO = new ProductoStockDTO();
                    productoDTO.setCodigoProducto(p.getCodigoProducto());
                    productoDTO.setNombre(p.getNombre());
                    productoDTO.setMarca(p.getMarca());
                    productoDTO.setCosto(p.getCosto());
                    productoDTO.setCantidadDisponible(p.getCantidadDisponible());
                    return productoDTO;
                })
                .collect(Collectors.toList());

        dto.setProductos(productosDTO);

        return dto;
    }
}