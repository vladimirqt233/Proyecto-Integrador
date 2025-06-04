package com.example.registroenvio.entity;

import com.example.registroenvio.dto.VehiculoDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

public class RegistroDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime fechasalida;
    private LocalDateTime fechallegada;
    private Double precio;

    public RegistroDetalle(){
        this.precio = (double) 0;
    }

}
