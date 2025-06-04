package com.example.registroenvio.feign;

import com.example.registroenvio.dto.ClienteDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente-service", path ="/clientes")
public interface ClienteFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteListByIdCB", fallbackMethod = "clienteListById")
    public ResponseEntity<ClienteDto> getById(@PathVariable Integer id);
    default ResponseEntity<ClienteDto> clienteListById(Integer id, Exception e) {
        return ResponseEntity.ok(new ClienteDto());
    }
}
