package com.proyecto.apirest.model.dao;

import com.proyecto.apirest.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 * NOTA:
 * JpaRepository - Es para un crud muy basico sin manejar mucha información, maneja algunos deprecados
 * CrudRepository - Es para algo más complejo, mucha información y metodos mas recientes
 * PackingAndSortingRepository - Manaja pagina y busquedas elaboradas, incluye los dos anteriores
 */

public interface ClienteDao extends CrudRepository<Cliente,Long> {

}
