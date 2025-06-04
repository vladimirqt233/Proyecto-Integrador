package com.example.mslibro_cargaservice.repository;

import com.example.mslibro_cargaservice.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
