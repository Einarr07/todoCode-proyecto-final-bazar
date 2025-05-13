package com.proyectoFinalTodoCode.bazar.controller;

import com.proyectoFinalTodoCode.bazar.dto.ProductoStockDTO;
import com.proyectoFinalTodoCode.bazar.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyectoFinalTodoCode.bazar.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id);

        if (producto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(producto);
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<List<ProductoStockDTO>> obtenerProductosConStockBajo() {
        List<ProductoStockDTO> productos = productoService.obtenerProductosConStockMenorA5();
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        productoService.saveProducto(producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Producto> editProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoExistente = productoService.getProductoById(id);

        if (productoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setMarca(producto.getMarca());
        productoExistente.setCosto(producto.getCosto());
        productoExistente.setCantidadDisponible(producto.getCantidadDisponible());

        Producto productoActualizado = productoService.updateProducto(id, productoExistente);

        return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
