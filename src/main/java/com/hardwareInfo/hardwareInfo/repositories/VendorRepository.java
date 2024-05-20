package com.hardwareInfo.hardwareInfo.repositories;


import com.hardwareInfo.hardwareInfo.entities.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
}
