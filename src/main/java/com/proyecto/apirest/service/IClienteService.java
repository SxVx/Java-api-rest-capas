package com.proyecto.apirest.service;

import com.proyecto.apirest.model.dto.ClienteDto;
import com.proyecto.apirest.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listAll();

    /**
     * Save permite crear si no existe y actualiza si encuentra el dato
     * @param cliente
     * @return Cliente cliente
     */
    Cliente save(ClienteDto cliente);

    Cliente findById(Long id);

    void delete(Cliente cliente);

    boolean existById(Long id);
}
