package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyectoFinalTodoCode.bazar.repository.IClienteRepository;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAllCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findClienteById(Long id) {
        return  clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        this.saveCliente(cliente);
        return cliente;
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
