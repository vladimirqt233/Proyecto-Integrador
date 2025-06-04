package com.example.msgestionenvio.service.Impl;

import com.example.msgestionenvio.dto.LibrocargaDto;
import com.example.msgestionenvio.dto.RegistroenvioDto;
import com.example.msgestionenvio.entity.Gestionenvio;
import com.example.msgestionenvio.exception.ResourceNotFoundException;
import com.example.msgestionenvio.feign.LibrocargaFeign;
import com.example.msgestionenvio.feign.RegistroenvioFeign;
import com.example.msgestionenvio.repository.GestionenvioRepository;
import com.example.msgestionenvio.service.GestionenvioService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionenvioServiceImpl implements GestionenvioService {
  @Autowired
  private GestionenvioRepository gestionenvioRepository;

  @Autowired
  private LibrocargaFeign librocargaFeign;

  @Autowired
  private RegistroenvioFeign registroenvioFeign;



    @Override
    public List<Gestionenvio> listar() {
        List<Gestionenvio> gestionenvios = gestionenvioRepository.findAll();

        // Recorremos cada Registroenvio y asignamos el VehiculoDto
        gestionenvios.forEach(gestionenvio -> {
            try {
                ResponseEntity<RegistroenvioDto> registroenvioDtoResponse = registroenvioFeign.getById(gestionenvio.getRegistroenvioId());
                if (registroenvioDtoResponse.getBody() == null) {
                    // Manejar el caso en el que el VehiculoDto no existe
                    throw new ResourceNotFoundException("VehiculoDto con ID " + gestionenvio.getRegistroenvioId() + " no existe");
                }
                gestionenvio.setRegistroenvioDto(registroenvioDtoResponse.getBody());

                ResponseEntity<LibrocargaDto> librocargaDtoResponse = librocargaFeign.getById(gestionenvio.getLibrocargaId());
                if (librocargaDtoResponse.getBody() == null) {
                    throw new ResourceNotFoundException("ClienteDto con ID " + gestionenvio.getLibrocargaId() + " no existe");
                }
                gestionenvio.setLibrocargaDto(librocargaDtoResponse.getBody());
            } catch (FeignException e) {
                // Manejar el error en el servidor de OpenFeign para VehiculoDto
                throw new RuntimeException("Error al obtener el VehiculoDto con ID " + gestionenvio.getRegistroenvioId(), e);
            }
        });

        return gestionenvios;
    }

    @Override
    public Gestionenvio guardar(Gestionenvio gestionenvio) {
        return gestionenvioRepository.save(gestionenvio);
    }

    @Override
    public Gestionenvio buscarPorId(Integer id) {
        // Buscar el registroenvio por ID en el repositorio
        Gestionenvio gestionenvio = gestionenvioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("registroenvio con ID " + id + " no existe"));

        try {
            // Obtener el VehiculoDto del registroenvio usando Feign
            ResponseEntity<RegistroenvioDto> registroenvioDtoResponse = registroenvioFeign.getById(gestionenvio.getRegistroenvioId());
            if (registroenvioDtoResponse.getBody() == null) {
                // Manejar el caso en el que el VehiculoDto no existe
                throw new ResourceNotFoundException("VehiculoDto con ID " + gestionenvio.getRegistroenvioId() + " no existe");
            }
            gestionenvio.setRegistroenvioDto(registroenvioDtoResponse.getBody());


            ResponseEntity<LibrocargaDto> librocargaDtoResponse = librocargaFeign.getById(gestionenvio.getLibrocargaId());
            if (librocargaDtoResponse.getBody() == null) {
                throw new ResourceNotFoundException("ClienteDto con ID " + gestionenvio.getLibrocargaId() + " no existe");
            }
            gestionenvio.setLibrocargaDto(librocargaDtoResponse.getBody());
        } catch (FeignException e) {
            // Manejar el error en el servidor de OpenFeign para VehiculoDto
            throw new RuntimeException("Error al obtener el VehiculoDto con ID " + gestionenvio.getLibrocargaId(), e);
        }

        return gestionenvio;
    }


    @Override
    public Gestionenvio actualizar(Gestionenvio gestionenvio) {
        return gestionenvioRepository.save(gestionenvio);
    }

    @Override
    public void eliminar(Integer id) {
        gestionenvioRepository.deleteById(id);
    }
}
