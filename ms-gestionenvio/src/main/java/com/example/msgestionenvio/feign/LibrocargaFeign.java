package com.example.msgestionenvio.feign;

import com.example.msgestionenvio.dto.LibrocargaDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-librocarga-service", path ="/librocarga")
public interface LibrocargaFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "librocargaListByIdCB", fallbackMethod = "librocargaListById")
    public ResponseEntity<LibrocargaDto> getById(@PathVariable Integer id);
    default ResponseEntity<LibrocargaDto> librocargaListById(Integer id, Exception e) {
        return ResponseEntity.ok(new LibrocargaDto());
    }

}
