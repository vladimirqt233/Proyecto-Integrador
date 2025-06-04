package com.example.registroenvio.dto;

import lombok.Data;

@Data
public class VehiculoDto {
    private Integer id;
    private String marca;
    private String modelo;
    private String placa;
    private String color;
    private String tipo;
}
