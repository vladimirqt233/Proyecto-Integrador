package com.example.msgestionenvio.service;
import com.example.msgestionenvio.entity.Gestionenvio;

import java.util.List;

public interface GestionenvioService {
    public List<Gestionenvio> listar();

    public Gestionenvio guardar(Gestionenvio gestionenvio);

    public Gestionenvio buscarPorId(Integer id);

    public Gestionenvio actualizar(Gestionenvio gestionenvio);

    public void eliminar(Integer id);
}
