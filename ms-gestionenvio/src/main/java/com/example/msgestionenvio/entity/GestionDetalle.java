package com.example.msgestionenvio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class GestionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime fechaCreacion; // Fecha y hora en que se creó el envío
    private LocalDateTime fechaEntrega; // Fecha y hora en que se entregó el envío (puede ser nula si aún no se ha entregado)

}
