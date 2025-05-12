package com.proyectoFinalTodoCode.bazar.controller;

import com.proyectoFinalTodoCode.bazar.entity.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyectoFinalTodoCode.bazar.service.VentaService;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping()
    public ResponseEntity<List<Venta>> getAllVentas() {
        return ResponseEntity.ok(ventaService.getAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Venta venta = ventaService.findVentaById(id);

        if(venta == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        Venta ventaCreada = ventaService.crearVentaConValidacion(venta);
        return new ResponseEntity<>(ventaCreada, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Venta> editarVenta(@PathVariable Long id, @RequestBody Venta venta) {
        Venta ventaEdit = ventaService.findVentaById(id);

        if(ventaEdit == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ventaService.updateVenta(venta);

        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Venta> eliminarVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
