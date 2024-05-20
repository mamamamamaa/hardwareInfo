package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.VramCapacityEntity;
import com.hardwareInfo.hardwareInfo.entities.VramTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VramCapacityRepository  extends JpaRepository<VramCapacityEntity, Long> {
}
