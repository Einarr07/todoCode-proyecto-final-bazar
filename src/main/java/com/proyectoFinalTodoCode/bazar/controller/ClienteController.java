package com.proyectoFinalTodoCode.bazar.controller;

import com.proyectoFinalTodoCode.bazar.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyectoFinalTodoCode.bazar.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAllClientes() {
        return ResponseEntity.ok(clienteService.findAllCliente());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.findClienteById(id);

        if(cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteExistente = clienteService.findClienteById(id);

        if(clienteExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setDni(cliente.getDni());

        Cliente clienteActualizado = clienteService.updateCliente(clienteExistente);

        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Cliente> eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
