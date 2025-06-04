package com.example.registroenvio.dto;

import lombok.Data;

@Data
public class ClienteDto {
    private Integer id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correoElectronico;
    private String direccion;
}
