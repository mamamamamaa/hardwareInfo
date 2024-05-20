package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.BenchmarkResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenchmarkResultRepository extends JpaRepository<BenchmarkResultEntity, Long> {
}
