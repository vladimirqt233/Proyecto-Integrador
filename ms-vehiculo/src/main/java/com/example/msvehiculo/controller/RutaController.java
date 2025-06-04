package com.example.msvehiculo.controller;

import com.example.msvehiculo.entity.Ruta;
import com.example.msvehiculo.entity.Vehiculo;
import com.example.msvehiculo.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.msvehiculo.service.VehiculoService;


import java.util.List;

@RestController
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    private RutaService rutaService;
    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Ruta>> list() {
        return ResponseEntity.ok().body(rutaService.listar());
    }
    @PostMapping
    public ResponseEntity<Ruta> add(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaService.guardar(ruta));
    }
    @PutMapping
    public ResponseEntity<Ruta> update(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaService.actualizar(ruta));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ruta> listById(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok().body(rutaService.ListarPorId(id).get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Ruta>> delete(@PathVariable(required = true) Integer id) {
        rutaService.eliminarPorId(id);
        return ResponseEntity.ok(rutaService.listar());
    }

}
