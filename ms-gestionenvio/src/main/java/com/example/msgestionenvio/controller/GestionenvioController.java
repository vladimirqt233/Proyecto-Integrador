package com.example.msgestionenvio.controller;

import com.example.msgestionenvio.dto.ErrorResponseDto;
import com.example.msgestionenvio.dto.LibrocargaDto;
import com.example.msgestionenvio.dto.RegistroenvioDto;
import com.example.msgestionenvio.entity.GestionDetalle;
import com.example.msgestionenvio.entity.Gestionenvio;
import com.example.msgestionenvio.feign.LibrocargaFeign;
import com.example.msgestionenvio.feign.RegistroenvioFeign;
import com.example.msgestionenvio.service.GestionenvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gestionenvio")
public class GestionenvioController {
    @Autowired
    private GestionenvioService gestionenvioService;

    @Autowired
    private RegistroenvioFeign registroenvioFeign;

    @Autowired
    private LibrocargaFeign librocargaFeign;

    @GetMapping
    public ResponseEntity<List<Gestionenvio>> listar(){
        return ResponseEntity.ok(gestionenvioService.listar());
    }


    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Gestionenvio gestionenvio) {
        RegistroenvioDto registroenvioDto = registroenvioFeign.getById(gestionenvio.getRegistroenvioId()).getBody();

        if (registroenvioDto == null || registroenvioDto.getId() == null) {
            String errorMessage = "Error: Registro no encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }
        for (GestionDetalle gestionDetalle : gestionenvio.getGestionDetalles()) {
            LibrocargaDto librocargaDto = librocargaFeign.getById(gestionenvio.getLibrocargaId()).getBody();

            if (librocargaDto == null || librocargaDto.getId() == null) {
                String errorMessage = "Error: producto no encontrado.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }
        Gestionenvio newGestionenvio = gestionenvioService.guardar(gestionenvio);
        return ResponseEntity.ok(newGestionenvio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gestionenvio> buscarPorId(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok(gestionenvioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestionenvio> actualizar(@PathVariable Integer id, @RequestBody Gestionenvio gestionenvio){
        return ResponseEntity.ok(gestionenvioService.actualizar(gestionenvio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Gestionenvio>> eliminar(@PathVariable(required = true) Integer id){
        gestionenvioService.eliminar(id);
        return ResponseEntity.ok(gestionenvioService.listar());
    }

}
