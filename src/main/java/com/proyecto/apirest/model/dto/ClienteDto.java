package com.proyecto.apirest.model.dto;


import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClienteDto implements Serializable {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Date fechaRegistro;

}
