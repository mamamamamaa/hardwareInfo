package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.GraphicsCardEntity;
import com.hardwareInfo.hardwareInfo.repositories.GraphicsCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphicsCardService {
    private final GraphicsCardRepository graphicsCardRepository;

    public Page<GraphicsCardEntity> getGraphicsCards(String gpuNameFilter, Integer gpuVendorFilter,
                                                     Integer tdpFilter,
                                                     Integer vramTypeFilter,
                                                     Integer dieSizeFilter,
                                                     Integer architectureFilter,
                                                     Pageable pageable) {
        Specification<GraphicsCardEntity> spec = Specification.where(null);

        if (gpuNameFilter != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("gpuModel").get("gpuName"), "%" + gpuNameFilter + "%"));
        }

        if (gpuVendorFilter != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("gpuModel").get("gpuVendor").get("id"), gpuVendorFilter));
        }

        if (tdpFilter != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tdp").get("id"), tdpFilter));
        }

        if (vramTypeFilter != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("vramType").get("id"), vramTypeFilter));
        }

        if (dieSizeFilter != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("gpuModel").get("dieSize").get("id"), dieSizeFilter));
        }

        if (architectureFilter != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("gpuModel").get("architecture").get("id"), architectureFilter));
        }

        return graphicsCardRepository.findAll(spec, pageable);
    }
}
