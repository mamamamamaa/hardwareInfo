package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.GraphicsCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GraphicsCardRepository extends JpaRepository<GraphicsCardEntity, Long>,
        JpaSpecificationExecutor<GraphicsCardEntity> {
}
