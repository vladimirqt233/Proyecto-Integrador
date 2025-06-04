package com.example.msgestionenvio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class LibrocargaDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                    // Identificador único del registro de carga
    private String libro;                  // Descripción o identificador del "libro de carga", que puede ser una referencia interna del sistema de transporte o logística
    private String envio;                // Identificador o número de seguimiento del envío
    private String detalleenvio;            // Descripción detallada del envío, como instrucciones especiales, condiciones de transporte, etc.
    private LocalDateTime fecharegistro;    // Fecha y hora en que se registró el envío o carga
}
