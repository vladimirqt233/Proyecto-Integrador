package com.example.msvehiculo.service.Impl;

import com.example.msvehiculo.entity.Ruta;
import com.example.msvehiculo.entity.Vehiculo;
import com.example.msvehiculo.repository.RutaRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.msvehiculo.repository.VehiculoRepository;
import com.example.msvehiculo.service.VehiculoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private RutaRepository rutaRepository;

    @Override
    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo actualizar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Optional<Vehiculo> ListarPorId(Integer id) {
        return vehiculoRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        vehiculoRepository.deleteById(id);
    }

    @Override
    public void generarVehiculosFalsos(int cantidad) {
        List<String> marcas = List.of("KIA", "Toyota");
        Map<String, List<String>> modelosPorMarca = Map.of(
                "KIA", List.of("Sportage", "Sorento", "Rio", "Cerato"),
                "Toyota", List.of("Corolla", "Camry", "Rav4", "Hilux")
        );
        List<String> colores = List.of("Rojo", "Blanco", "Negro", "Azul", "Gris");

        List<Vehiculo> vehiculos = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < cantidad; i++) {
            String marca = marcas.get(faker.random().nextInt(marcas.size()));
            String modelo = modelosPorMarca.get(marca).get(faker.random().nextInt(modelosPorMarca.get(marca).size()));
            String color = colores.get(faker.random().nextInt(colores.size()));

            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            vehiculo.setPlaca(faker.bothify("???-####"));
            vehiculo.setColor(color);
            vehiculo.setTipo(faker.options().option("SUV", "Sedán", "Camioneta", "Coupé"));

            vehiculos.add(vehiculo);
        }

        vehiculoRepository.saveAll(vehiculos);
    }

    @Override
    public void generarRutasFalsas(int cantidad) {
        Faker faker = new Faker();
        List<Ruta> rutas = new ArrayList<>();

        // Obtener vehículos existentes para asignarlos a las rutas
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        if (vehiculos.isEmpty()) {
            throw new RuntimeException("No se pueden generar rutas porque no hay vehículos disponibles.");
        }

        for (int i = 0; i < cantidad; i++) {
            // Generar datos ficticios
            String rutaInicio = faker.address().cityName();
            String rutaDestino = faker.address().cityName();
            while (rutaDestino.equals(rutaInicio)) {
                rutaDestino = faker.address().cityName();
            }
            LocalDateTime fechaSalida = faker.date().future(10, java.util.concurrent.TimeUnit.DAYS)
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
            LocalDateTime fechaLlegada = fechaSalida.plusHours(faker.random().nextInt(2, 24));

            // Asociar con un vehículo existente
            Vehiculo vehiculo = vehiculos.get(faker.random().nextInt(vehiculos.size()));

            Ruta ruta = new Ruta();
            ruta.setRutainicio(rutaInicio);
            ruta.setRutadestino(rutaDestino);
            ruta.setFechasalida(fechaSalida);
            ruta.setFechallegada(fechaLlegada);
            ruta.setVehiculo(vehiculo);

            rutas.add(ruta);
        }

        rutaRepository.saveAll(rutas);
    }


}

