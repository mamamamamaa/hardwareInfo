package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.ArchitectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchitectureRepository extends JpaRepository<ArchitectureEntity, Long> {
}
