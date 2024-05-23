package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.BenchmarkResultEntity;
import com.hardwareInfo.hardwareInfo.models.BenchmarkResultDTO;
import com.hardwareInfo.hardwareInfo.services.BenchmarkResultService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/benchmark-results")
public class BenchmarkResultController {
    private final BenchmarkResultService benchmarkResultService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BenchmarkResultDTO>> getAllBenchmarkResults() {
        List<BenchmarkResultEntity> benchmarkResultEntities = benchmarkResultService.getAllBenchmarkResults();
        List<BenchmarkResultDTO> benchmarkResultDTOs = benchmarkResultEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(benchmarkResultDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenchmarkResultDTO> getBenchmarkResultById(@PathVariable Long id) {
        BenchmarkResultEntity benchmarkResultEntity = benchmarkResultService.getBenchmarkResultById(id);
        return ResponseEntity.ok(convertToDTO(benchmarkResultEntity));
    }

    @PostMapping
    public ResponseEntity<BenchmarkResultDTO> createBenchmarkResult(@RequestBody BenchmarkResultDTO benchmarkResultDTO) {
        BenchmarkResultEntity createdBenchmarkResultEntity = benchmarkResultService
                .createBenchmarkResult(convertToEntity(benchmarkResultDTO));
        return ResponseEntity.ok(convertToDTO(createdBenchmarkResultEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BenchmarkResultDTO> updateBenchmarkResult(@PathVariable Long id,
                                                                    @RequestBody BenchmarkResultDTO benchmarkResultDTO) {
        BenchmarkResultEntity benchmarkResultEntity = convertToEntity(benchmarkResultDTO);
        BenchmarkResultEntity updatedEntity = benchmarkResultService.updateBenchmarkResult(id, benchmarkResultEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenchmarkResult(@PathVariable Long id) {
        benchmarkResultService.deleteBenchmarkResult(id);
        return ResponseEntity.noContent().build();
    }

    private BenchmarkResultEntity convertToEntity(BenchmarkResultDTO benchmarkResultDTO) {
        return modelMapper.map(benchmarkResultDTO, BenchmarkResultEntity.class);
    }

    private BenchmarkResultDTO convertToDTO(BenchmarkResultEntity benchmarkResultEntity) {
        return modelMapper.map(benchmarkResultEntity, BenchmarkResultDTO.class);
    }
}
