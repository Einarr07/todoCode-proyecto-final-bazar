package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyectoFinalTodoCode.bazar.repository.IProductoRepository;

import java.util.List;

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
        productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        this.saveProducto(producto);
        return producto;
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
