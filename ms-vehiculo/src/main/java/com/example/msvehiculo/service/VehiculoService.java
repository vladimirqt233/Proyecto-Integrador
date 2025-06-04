package com.example.msvehiculo.service;

import com.example.msvehiculo.entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    public List<Vehiculo> listar();

    public Vehiculo guardar(Vehiculo vehiculo);

    public Vehiculo actualizar(Vehiculo vehiculo);

    public Optional<Vehiculo> ListarPorId(Integer id);

    public void eliminarPorId(Integer id);

    public void generarVehiculosFalsos(int cantidad);

    void generarRutasFalsas(int cantidad);
}
