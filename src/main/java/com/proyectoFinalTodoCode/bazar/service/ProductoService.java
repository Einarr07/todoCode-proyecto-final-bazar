package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.dto.ProductosStockBajaDTO;
import com.proyectoFinalTodoCode.bazar.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyectoFinalTodoCode.bazar.repository.IProductoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProducto(Producto producto) {
        if (producto.getCantidadDisponible() < 0){
            throw new IllegalArgumentException("La cantidad dispible no puede ser menor que 0");
        }
        productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Long codigoProducto, Producto producto) {
        Producto prod = this.getProductoById(codigoProducto);

        if (prod.getCantidadDisponible() != null && prod.getCantidadDisponible() < 0){
            throw new IllegalArgumentException("La cantidad disponible no puede ser menor que 0");
        }

        prod.setNombre(producto.getNombre());
        prod.setMarca(producto.getMarca());
        prod.setCosto(producto.getCosto());
        prod.setCantidadDisponible(producto.getCantidadDisponible());

        return productoRepository.save(prod);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public void actualizarStock(Long codigoProducto, Double cantidadVendida) {
        Producto prod = this.getProductoById(codigoProducto);
        if (prod != null) {
            Double nuevoStock = prod.getCantidadDisponible() - cantidadVendida;
            prod.setCantidadDisponible(nuevoStock);
            productoRepository.save(prod);
        }
    }

    @Override
    public List<ProductosStockBajaDTO> obtenerProductosStockBajaMenor5() {
        List<Producto> productos = productoRepository.findAll()
                .stream()
                .filter(p -> p.getCantidadDisponible() < 5)
                .collect(Collectors.toList());

        return productos.stream()
                .map(p -> {
                    ProductosStockBajaDTO dto = new ProductosStockBajaDTO();
                    dto.setCodigoProducto(p.getCodigoProducto());
                    dto.setNombre(p.getNombre());
                    dto.setMarca(p.getMarca());
                    dto.setCosto(p.getCosto());
                    dto.setCantidadDisponible(p.getCantidadDisponible());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
