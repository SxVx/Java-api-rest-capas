package com.proyecto.apirest.controller;

import com.proyecto.apirest.model.dto.ClienteDto;
import com.proyecto.apirest.model.entity.Cliente;
import com.proyecto.apirest.model.payload.MensajeResponse;
import com.proyecto.apirest.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ RestController
 * La anotación @RestController aplica a una clase para contralador de solicitudes
 * Esta anotación se usa para crear servicios RestFul usando Spring MVC
 * @ RequestMapping
 * Se utiliza para asingnar solicitudes web a clases especificas y o a metodos
 * Se puede aplicar tanto para la clase como para el controlador
 */

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("clientes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showAll(){
        List<Cliente> list = clienteService.listAll();
        return new ResponseEntity<>(MensajeResponse.builder().mensaje("").object(list).build(),HttpStatus.OK);
    }

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto){
        Cliente clienteSave = null;
        try{
            clienteSave = clienteService.save(clienteDto);
            clienteDto = ClienteDto.builder()
                    .id(clienteSave.getId())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .email(clienteSave.getEmail())
                    .telefono(clienteSave.getTelefono())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje("Guardo correctamente")
                            .object(clienteDto)
                            .build(),
                    HttpStatus.CREATED);
        }catch (DataAccessException exception){
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje(exception.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto,@PathVariable Long id){
        Cliente clienteUpdate = null;

        if(!clienteService.existById(id)){
            return new ResponseEntity<>(MensajeResponse
                    .builder()
                    .mensaje("Record not found")
                    .object(null)
                    .build(),
            HttpStatus.METHOD_NOT_ALLOWED);
        }

        try{
            clienteUpdate = clienteService.save(clienteDto);
            clienteDto = ClienteDto.builder()
                    .id(clienteUpdate.getId())
                    .nombre(clienteUpdate.getNombre())
                    .apellido(clienteUpdate.getApellido())
                    .email(clienteUpdate.getEmail())
                    .telefono(clienteUpdate.getTelefono())
                    .fechaRegistro(clienteUpdate.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje("Guardo correctamente")
                            .object(clienteDto)
                            .build(),
                    HttpStatus.CREATED);
        }catch (DataAccessException exception){
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje(exception.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable long id){
        /*
         * ResponseEntity maneja toda la respuesta HTTP incluyendo el cuerpo,
         * cabecera y codigos, permite definir la respuesta de nuestros endpoints
         */
        //Map<String, Object> response = new HashMap<>();
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exception){
            // opcion 1
            //response.put("mensaje", exception.getMessage());
            //response.put("clientge", null);
            //return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .object(null)
                            .mensaje(exception.getMessage())
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable long id){
        Cliente cliente = clienteService.findById(id);
        if(cliente ==null){
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje("Record not found")
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
         ClienteDto clienteResponse = ClienteDto.builder()
                .id(cliente.getId())
                .nombre(cliente.getApellido())
                .apellido(cliente.getApellido())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
        return new ResponseEntity<>(
                MensajeResponse
                        .builder()
                        .mensaje("Record founded")
                        .object(clienteResponse)
                        .build(),
                HttpStatus.OK);
    }


}
