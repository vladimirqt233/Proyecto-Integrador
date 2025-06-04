package com.example.mslibro_cargaservice.controller;


import com.example.mslibro_cargaservice.entity.Librocarga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mslibro_cargaservice.service.LibrocargaService;

import java.util.List;

@RestController
@RequestMapping("/librocarga")
public class LibrocargaController {
    @Autowired
    private LibrocargaService librocargaService;

    @GetMapping()
    public ResponseEntity<List<Librocarga>> listar(){
        return ResponseEntity.ok(librocargaService.listar());
    }
    @PostMapping
    public ResponseEntity<Librocarga> guardar(@RequestBody Librocarga librocarga){
        return ResponseEntity.ok(librocargaService.guardar(librocarga));
    }
    @PutMapping
    public ResponseEntity<Librocarga> actualizar(@RequestBody Librocarga librocarga){
        return ResponseEntity.ok(librocargaService.actualizar(librocarga));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Librocarga> buscarPorId(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(librocargaService.buscarPorId(id).get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Librocarga>> eliminar(@PathVariable(required = true) Integer id) {
        librocargaService.eliminar(id);
        return ResponseEntity.ok(librocargaService.listar());
    }
}
