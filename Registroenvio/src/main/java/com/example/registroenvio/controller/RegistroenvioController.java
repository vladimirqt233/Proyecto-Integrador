package com.example.registroenvio.controller;

import com.example.registroenvio.dto.ClienteDto;
import com.example.registroenvio.dto.ErrorResponseDto;
import com.example.registroenvio.dto.VehiculoDto;
import com.example.registroenvio.entity.RegistroDetalle;
import com.example.registroenvio.entity.Registroenvio;
import com.example.registroenvio.feign.ClienteFeign;
import com.example.registroenvio.feign.VehiculoFeign;
import com.example.registroenvio.service.RegistroenvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registroenvio")
public class RegistroenvioController {
    @Autowired
    private RegistroenvioService registroenvioService;
    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private VehiculoFeign vehiculoFeign;

    @GetMapping
    public ResponseEntity<List<Registroenvio>> listar(){
        return ResponseEntity.ok(registroenvioService.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Registroenvio registroenvio) {
        ClienteDto clienteDto = clienteFeign.getById(registroenvio.getClienteId()).getBody();

        if (clienteDto == null || clienteDto.getId() == null) {
            String errorMessage = "Error: Registro no encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }
        for (RegistroDetalle registroDetalle: registroenvio.getRegistroDetalles()) {
            VehiculoDto vehiculoDto = vehiculoFeign.getById(registroenvio.getVehiculoId()).getBody();

            if (vehiculoDto == null || vehiculoDto.getId() == null) {
                String errorMessage = "Error: producto no encontrado.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }
        Registroenvio newRegistroenvio = registroenvioService.guardar(registroenvio);
        return ResponseEntity.ok(newRegistroenvio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registroenvio> buscarPorId(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok(registroenvioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registroenvio> actualizar(@PathVariable Integer id, @RequestBody Registroenvio registroenvio){
        return ResponseEntity.ok(registroenvioService.actualizar(registroenvio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Registroenvio>> eliminar(@PathVariable(required = true) Integer id){
        registroenvioService.eliminar(id);
        return ResponseEntity.ok(registroenvioService.listar());
    }
}
