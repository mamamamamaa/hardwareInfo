package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.GpuModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuModelRepository extends JpaRepository<GpuModelEntity, Long> {
}
