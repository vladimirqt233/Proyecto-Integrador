package com.example.mslibro_cargaservice.service;


import com.example.mslibro_cargaservice.entity.Librocarga;

import java.util.List;
import java.util.Optional;

public interface LibrocargaService {
    public List<Librocarga> listar();

    public Librocarga guardar(Librocarga librocarga);

    public Optional<Librocarga> buscarPorId(Integer id);

    public Librocarga actualizar(Librocarga librocarga);

    public void eliminar(Integer id);
}
