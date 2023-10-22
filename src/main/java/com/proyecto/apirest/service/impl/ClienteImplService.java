package com.proyecto.apirest.service.impl;

import com.proyecto.apirest.model.dao.ClienteDao;
import com.proyecto.apirest.model.dto.ClienteDto;
import com.proyecto.apirest.model.entity.Cliente;
import com.proyecto.apirest.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImplService implements IClienteService {

    /**
     * El @Autowired nos permite hacer una inyección de dependencias
     * Para usar el @Autowired la clase necesita implementar @Service
     * Cuando no se utiliza el @Autowired se crea un constructor para la inyeción de dependencias
     */
    @Autowired
    private ClienteDao clienteDao;

    // NOTA: @Transactional es para que las consultas se hagan en transacciones
    // hace por defecto el begin(), commit() y rollback();
    // Importante elegir el @Transactional de spring el notation

    @Override
    public List<Cliente> listAll() {
        return  (List)clienteDao.findAll();
    }

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
                .id(clienteDto.getId())
                .nombre(clienteDto.getApellido())
                .apellido(clienteDto.getApellido())
                .email(clienteDto.getEmail())
                .telefono(clienteDto.getTelefono())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .build();
        return clienteDao.save(cliente);
    }

    // En la transacción espeficamos que tipo de transacción es en este caso solo lectura

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public boolean existById(Long id) {
        return clienteDao.existsById(id);
    }
}
