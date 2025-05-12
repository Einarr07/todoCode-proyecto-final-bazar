package com.proyectoFinalTodoCode.bazar.service;

import com.proyectoFinalTodoCode.bazar.entity.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAllCliente();

    public Cliente findClienteById(Long id);

    public void saveCliente(Cliente cliente);

    public Cliente updateCliente(Cliente cliente);

    public void deleteCliente(Long id);
}
