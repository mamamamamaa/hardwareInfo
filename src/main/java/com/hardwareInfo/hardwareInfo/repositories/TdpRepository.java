package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.TdpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TdpRepository extends JpaRepository<TdpEntity, Long> {
}
