package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.BenchmarkResultEntity;
import com.hardwareInfo.hardwareInfo.repositories.BenchmarkResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BenchmarkResultService {
    private final BenchmarkResultRepository benchmarkResultRepository;

    public BenchmarkResultEntity createBenchmarkResult(BenchmarkResultEntity benchmarkResultEntity){
        return benchmarkResultRepository.save(benchmarkResultEntity);
    }

    @PutMapping
    public Optional<BenchmarkResultEntity> updateBenchmarkResult(BenchmarkResultEntity newBenchmark,
                                                                 Long id){

        return benchmarkResultRepository.findById(id).map( oldEntity ->{
            oldEntity.setBenchmark(newBenchmark.getBenchmark());
            oldEntity.setGraphicsCard(newBenchmark.getGraphicsCard());
            oldEntity.setScore(newBenchmark.getScore());
            return oldEntity;
        });
    }
    public List<BenchmarkResultEntity> getAllBenchmarkResults(){
        return benchmarkResultRepository.findAll();
    }

    public Optional<BenchmarkResultEntity> getBenchMarkById(Long id){

        return benchmarkResultRepository.findById(id);
    }

    @DeleteMapping
    public void deleteBenchmarkResult(Long id){
        benchmarkResultRepository.deleteById(id);
    }
}
