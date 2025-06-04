package com.example.registroenvio.service.Impl;

import com.example.registroenvio.dto.ClienteDto;
import com.example.registroenvio.dto.VehiculoDto;
import com.example.registroenvio.entity.RegistroDetalle;
import com.example.registroenvio.entity.Registroenvio;
import com.example.registroenvio.exception.ResourceNotFoundException;

import com.example.registroenvio.feign.ClienteFeign;
import com.example.registroenvio.feign.VehiculoFeign;
import com.example.registroenvio.repository.RegistroenvioRepository;
import com.example.registroenvio.service.RegistroenvioService;
import com.netflix.discovery.converters.Auto;
import feign.FeignException;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroenvioServiceImpl implements RegistroenvioService {
    @Autowired
    private RegistroenvioRepository registroenvioRepository;
    @Autowired
    private VehiculoFeign vehiculoFeign;
    @Autowired
    private ClienteFeign clienteFeign;

    @Override
    public List<Registroenvio> listar() {
        List<Registroenvio> registroenvios = registroenvioRepository.findAll();

        // Recorremos cada Registroenvio y asignamos el VehiculoDto
        registroenvios.forEach(registroenvio -> {
            try {
                ResponseEntity<VehiculoDto> vehiculoDtoResponse = vehiculoFeign.getById(registroenvio.getVehiculoId());
                if (vehiculoDtoResponse.getBody() == null) {
                    // Manejar el caso en el que el VehiculoDto no existe
                    throw new ResourceNotFoundException("VehiculoDto con ID " + registroenvio.getVehiculoId() + " no existe");
                }
                registroenvio.setVehiculoDto(vehiculoDtoResponse.getBody());
                ResponseEntity<ClienteDto> clienteDtoResponse = clienteFeign.getById(registroenvio.getClienteId());
                if (clienteDtoResponse.getBody() == null) {
                    throw new ResourceNotFoundException("ClienteDto con ID " + registroenvio.getClienteId() + " no existe");
                }
                registroenvio.setClienteDto(clienteDtoResponse.getBody());

            } catch (FeignException e) {
                // Manejar el error en el servidor de OpenFeign para VehiculoDto
                throw new RuntimeException("Error al obtener el VehiculoDto con ID " + registroenvio.getVehiculoId(), e);
            }
        });

        return registroenvios;
    }

    @Override
    public Registroenvio guardar(Registroenvio registroenvio) {
        return registroenvioRepository.save(registroenvio);
    }

    @Override
    public Registroenvio buscarPorId(Integer id) {
        // Buscar el registroenvio por ID en el repositorio
        Registroenvio registroenvio = registroenvioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("registroenvio con ID " + id + " no existe"));

        try {
            // Obtener el VehiculoDto del registroenvio usando Feign
            ResponseEntity<VehiculoDto> vehiculoDtoResponse = vehiculoFeign.getById(registroenvio.getVehiculoId());
            if (vehiculoDtoResponse.getBody() == null) {
                // Manejar el caso en el que el VehiculoDto no existe
                throw new ResourceNotFoundException("VehiculoDto con ID " + registroenvio.getVehiculoId() + " no existe");
            }
            registroenvio.setVehiculoDto(vehiculoDtoResponse.getBody());

            ResponseEntity<ClienteDto> clienteDtoResponse = clienteFeign.getById(registroenvio.getClienteId());
            if (clienteDtoResponse.getBody() == null) {
                throw new ResourceNotFoundException("ClienteDto con ID " + registroenvio.getClienteId() + " no existe");
            }
            registroenvio.setClienteDto(clienteDtoResponse.getBody());
        } catch (FeignException e) {
            // Manejar el error en el servidor de OpenFeign para VehiculoDto
            throw new RuntimeException("Error al obtener el VehiculoDto con ID " + registroenvio.getVehiculoId(), e);
        }

        return registroenvio;
    }

    @Override
    public Registroenvio actualizar(Registroenvio registroenvio) {
            return registroenvioRepository.save(registroenvio);
    }

    @Override
    public void eliminar(Integer id) {
        registroenvioRepository.deleteById(id);
    }
}
