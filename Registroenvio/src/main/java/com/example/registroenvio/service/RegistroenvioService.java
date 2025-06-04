package com.example.registroenvio.service;

import com.example.registroenvio.entity.Registroenvio;

import java.util.List;
import java.util.Optional;

public interface RegistroenvioService {
    public List<Registroenvio> listar();

    public Registroenvio guardar(Registroenvio registroenvio);

    public Registroenvio buscarPorId(Integer id);

    public Registroenvio actualizar(Registroenvio registroenvio);

    public void eliminar(Integer id);
}
