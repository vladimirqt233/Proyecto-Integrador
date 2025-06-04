package com.example.mslibro_cargaservice.service;

import com.example.mslibro_cargaservice.entity.Librocarga;
import com.example.mslibro_cargaservice.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> listar();

    public Producto guardar(Producto producto);

    public Optional<Producto> buscarPorId(Integer id);

    public Producto actualizar(Producto producto);

    public void eliminar(Integer id);
}
