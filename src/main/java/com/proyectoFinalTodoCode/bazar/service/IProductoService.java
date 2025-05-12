package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.entity.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getAllProductos();

    public Producto getProductoById(Long id);

    public void saveProducto(Producto producto);

    public Producto updateProducto(Producto producto);

    public void deleteProducto(Long id);
}
