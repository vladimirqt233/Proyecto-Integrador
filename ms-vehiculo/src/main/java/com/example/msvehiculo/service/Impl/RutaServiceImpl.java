package com.example.msvehiculo.service.Impl;

import com.example.msvehiculo.entity.Ruta;
import com.example.msvehiculo.entity.Vehiculo;
import com.example.msvehiculo.repository.RutaRepository;
import com.example.msvehiculo.repository.VehiculoRepository;
import com.example.msvehiculo.service.RutaService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RutaServiceImpl implements RutaService {
    @Autowired
private RutaRepository rutaRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Ruta> listar() {
        return rutaRepository.findAll();
    }

    @Override
    public Ruta guardar(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Override
    public Ruta actualizar(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Override
    public Optional<Ruta> ListarPorId(Integer id) {
        return rutaRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        rutaRepository.deleteById(id);
    }


}
