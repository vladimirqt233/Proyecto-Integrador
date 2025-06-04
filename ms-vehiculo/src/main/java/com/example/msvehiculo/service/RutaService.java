package com.example.msvehiculo.service;

import com.example.msvehiculo.entity.Ruta;
import com.example.msvehiculo.entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface RutaService {
    public List<Ruta> listar();

    public Ruta guardar(Ruta ruta);

    public Ruta actualizar(Ruta ruta);

    public Optional<Ruta> ListarPorId(Integer id);

    public void eliminarPorId(Integer id);

}
