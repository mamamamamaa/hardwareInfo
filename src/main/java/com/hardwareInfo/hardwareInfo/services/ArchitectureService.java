package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.ArchitectureEntity;
import com.hardwareInfo.hardwareInfo.exceptions.ApiSupportsNotFoundException;
import com.hardwareInfo.hardwareInfo.exceptions.ArchitectureNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.ArchitectureRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchitectureService {
    private final ArchitectureRepository architectureRepository;
    private final ModelMapper modelMapper;

    public List<ArchitectureEntity> getAllArchitectures() {
        return architectureRepository.findAll();
    }

    public ArchitectureEntity getArchitectureById(Long id) {
        return architectureRepository.findById(id)
                .orElseThrow(() -> new ArchitectureNotFoundException("Architecture not found with id + " + id));
    }

    public ArchitectureEntity createArchitectureEntity(ArchitectureEntity architectureEntity) {
        return architectureRepository.save(architectureEntity);
    }

    public ArchitectureEntity updateArchitecture(Long id, ArchitectureEntity newArchitectureEntity) {
        ArchitectureEntity existingArchitectureEntity = architectureRepository.findById(id)
                .orElseThrow(() -> new ArchitectureNotFoundException("Architecture not found with id + " + id));

        modelMapper.map(newArchitectureEntity, existingArchitectureEntity);
        return architectureRepository.save(existingArchitectureEntity);
    }

    public void deleteArchitecture(Long id) {
        if(!architectureRepository.existsById(id)) {
            throw new ArchitectureNotFoundException("Architecture not found with id + " + id);
        }
        architectureRepository.deleteById(id);
    }
}
