package com.example.mslibro_cargaservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreproducto;
    private String descripcionproducto;
    private Integer cantidadproducto;
    private LocalDateTime fechaproducto;
}
