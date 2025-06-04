package com.example.msgestionenvio.feign;

import com.example.msgestionenvio.dto.RegistroenvioDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-registroenvio-service", path = "/registroenvio")
public interface RegistroenvioFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "registroListByIdCB", fallbackMethod = "registroListById")
    public ResponseEntity<RegistroenvioDto> getById(@PathVariable Integer id);
    default ResponseEntity<RegistroenvioDto> registroListById(Integer id, Exception e) {
        return ResponseEntity.ok(new RegistroenvioDto());
    }
}
