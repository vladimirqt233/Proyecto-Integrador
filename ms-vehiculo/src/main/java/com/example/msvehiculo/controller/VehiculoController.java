package com.example.msvehiculo.controller;

import com.example.msvehiculo.entity.Vehiculo;
import com.example.msvehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> list() {
        return ResponseEntity.ok().body(vehiculoService.listar());
    }

    @PostMapping
    public ResponseEntity<Vehiculo> save(@RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(vehiculoService.guardar(vehiculo));
    }

    @PutMapping
    public ResponseEntity<Vehiculo> update(@RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(vehiculoService.actualizar(vehiculo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> listById(@PathVariable Integer id) {
        Vehiculo vehiculo = vehiculoService.ListarPorId(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
        return ResponseEntity.ok(vehiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Vehiculo>> delete(@PathVariable Integer id) {
        vehiculoService.eliminarPorId(id);
        return ResponseEntity.ok(vehiculoService.listar());
    }

    @GetMapping("/generar")
    public String generarVehiculos(@RequestParam(defaultValue = "10") int cantidad) {
        vehiculoService.generarVehiculosFalsos(cantidad);
        return "Se generaron " + cantidad + " vehículos correctamente.";
    }

    @GetMapping("/generar-rutas")
    public String generarRutas(@RequestParam(defaultValue = "10") int cantidad) {
        vehiculoService.generarRutasFalsas(cantidad);
        return "Se generaron " + cantidad + " rutas correctamente.";
    }

}
