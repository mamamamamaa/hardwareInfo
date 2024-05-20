package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.ApiSupportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiSupportRepository extends JpaRepository<ApiSupportEntity, Long> {
}
