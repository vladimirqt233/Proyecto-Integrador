package com.example.mslibro_cargaservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Librocarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                    // Identificador único del registro de carga
    private String libro;                  // Descripción o identificador del "libro de carga", que puede ser una referencia interna del sistema de transporte o logística
    private String envio;                // Identificador o número de seguimiento del envío
    private String detalleenvio;          // Descripción detallada del envío, como instrucciones especiales, condiciones de transporte, etc.
    private LocalDateTime fecharegistro;    // Fecha y hora en que se registró el envío o carga
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;// Relación con la entidad Producto, indicando qué producto se está enviando

    @Enumerated(EnumType.STRING)  // 0:pendiente 1:encamino 2:entregadoPara almacenar el nombre del estado como texto en la base de datos
    private EstadoEnvio estadoEnvio;
    public enum EstadoEnvio {
        PENDIENTE,
        EN_CAMINO,
        ENTREGADO
    }
}

