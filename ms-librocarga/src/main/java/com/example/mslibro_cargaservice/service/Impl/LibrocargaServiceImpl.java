package com.example.mslibro_cargaservice.service.Impl;


import com.example.mslibro_cargaservice.repository.LibrocargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mslibro_cargaservice.service.LibrocargaService;
import com.example.mslibro_cargaservice.entity.Librocarga;

import java.util.List;
import java.util.Optional;

@Service
public class LibrocargaServiceImpl implements LibrocargaService {
    @Autowired
    private LibrocargaRepository LibrocargaRepository;

    @Override
    public List<Librocarga> listar() {
        return LibrocargaRepository.findAll();
    }

    @Override
    public Librocarga guardar(Librocarga librocarga) {
        return LibrocargaRepository.save(librocarga);
    }

    @Override
    public Optional<Librocarga> buscarPorId(Integer id) {
        return LibrocargaRepository.findById(id);
    }

    @Override
    public Librocarga actualizar(Librocarga librocarga) {
        return LibrocargaRepository.save(librocarga);
    }

    @Override
    public void eliminar(Integer id) {
        LibrocargaRepository.deleteById(id);
    }
}
