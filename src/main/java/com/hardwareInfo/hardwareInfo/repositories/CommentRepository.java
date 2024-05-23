package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
