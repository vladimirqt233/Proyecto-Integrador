package com.example.msgestionenvio.entity;

import com.example.msgestionenvio.dto.LibrocargaDto;
import com.example.msgestionenvio.dto.RegistroenvioDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Gestionenvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descripcion;
    private String categoria;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gestion_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<GestionDetalle> gestionDetalles;

    private Integer registroenvioId;
    @Transient
    private RegistroenvioDto registroenvioDto ;

    private Integer LibrocargaId;
    @Transient
    private LibrocargaDto librocargaDto;


}
