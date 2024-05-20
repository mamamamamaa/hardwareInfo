package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.ArchitectureEntity;
import com.hardwareInfo.hardwareInfo.repositories.ArchitectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArchitectureService {
    private final ArchitectureRepository architectureRepository;

    public ArchitectureEntity addArchitectutre(ArchitectureEntity architectureEntity){

       return architectureRepository.save(architectureEntity);
    }
    @PutMapping
    public Optional<ArchitectureEntity> updateArchitecure(ArchitectureEntity newArchitectureEntity, Long id){
        return architectureRepository.findById(id).map( architectureEntity-> {
            architectureEntity.setGpuArchitecture(newArchitectureEntity.getGpuArchitecture());
            architectureEntity.setGpuModels(newArchitectureEntity.getGpuModels());
            return architectureEntity;
        });
    }

    @DeleteMapping
    public void deleteArchitecture(Long id){


        architectureRepository.deleteById(id);
    }
}
