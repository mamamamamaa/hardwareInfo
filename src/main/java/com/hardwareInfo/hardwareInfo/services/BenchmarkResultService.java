package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.ArchitectureEntity;
import com.hardwareInfo.hardwareInfo.entities.BenchmarkResultEntity;
import com.hardwareInfo.hardwareInfo.exceptions.ArchitectureNotFoundException;
import com.hardwareInfo.hardwareInfo.exceptions.BenchmarkResultNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.BenchmarkResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenchmarkResultService {
    private final BenchmarkResultRepository benchmarkResultRepository;
    private final ModelMapper modelMapper;

    public List<BenchmarkResultEntity> getAllBenchmarkResults() {
        return benchmarkResultRepository.findAll();
    }

    public BenchmarkResultEntity getBenchmarkResultById(Long id) {
        return benchmarkResultRepository.findById(id)
                .orElseThrow(() -> new BenchmarkResultNotFoundException("Benchmark Result not found with id + " + id));
    }

    public BenchmarkResultEntity createBenchmarkResult(BenchmarkResultEntity benchmarkResultEntity){
        return benchmarkResultRepository.save(benchmarkResultEntity);
    }

    public BenchmarkResultEntity updateBenchmarkResult(Long id, BenchmarkResultEntity newBenchmarkResultEntity) {
        BenchmarkResultEntity existingBenchmarkResultEntity = benchmarkResultRepository.findById(id)
                .orElseThrow(() -> new BenchmarkResultNotFoundException("Benchmark Result not found with id + " + id));

        modelMapper.map(newBenchmarkResultEntity, existingBenchmarkResultEntity);
        return benchmarkResultRepository.save(existingBenchmarkResultEntity);
    }

    public void deleteBenchmarkResult(Long id) {
        if(!benchmarkResultRepository.existsById(id)) {
            throw new BenchmarkResultNotFoundException("Benchmark Result not found with id + " + id);
        }
        benchmarkResultRepository.deleteById(id);
    }
}
