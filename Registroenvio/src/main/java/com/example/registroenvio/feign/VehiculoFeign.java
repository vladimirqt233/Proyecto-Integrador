package com.example.registroenvio.feign;

import com.example.registroenvio.dto.VehiculoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-vehiculo-service", path = "/vehiculo")
public interface VehiculoFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "vehiculoListByIdCB", fallbackMethod = "vehiculoListById")
    public ResponseEntity<VehiculoDto> getById(@PathVariable Integer id);
    default ResponseEntity<VehiculoDto> vehiculoListById(Integer id, Exception e) {
        return ResponseEntity.ok(new VehiculoDto());
    }
}
