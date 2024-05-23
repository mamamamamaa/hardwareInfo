package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.BenchmarkResultEntity;
import com.hardwareInfo.hardwareInfo.entities.DieSizeEntity;
import com.hardwareInfo.hardwareInfo.exceptions.BenchmarkResultNotFoundException;
import com.hardwareInfo.hardwareInfo.exceptions.DieSizeNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.DieSizeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DieSizeService {
    private final DieSizeRepository dieSizeRepository;
    private final ModelMapper modelMapper;

    public List<DieSizeEntity> getAllDieSizes() {
        return dieSizeRepository.findAll();
    }

    public DieSizeEntity getDieSizeById(Long id) {
        return dieSizeRepository.findById(id)
                .orElseThrow(() -> new DieSizeNotFoundException("Die Size not found with id + " + id));
    }

    public DieSizeEntity createDieSize(DieSizeEntity dieSizeEntity){
        return dieSizeRepository.save(dieSizeEntity);
    }

    public DieSizeEntity updateDieSize(Long id, DieSizeEntity newDieSizeEntity) {
        DieSizeEntity existingDieSizeEntity = dieSizeRepository.findById(id)
                .orElseThrow(() -> new DieSizeNotFoundException("Die Size not found with id + " + id));

        modelMapper.map(newDieSizeEntity, existingDieSizeEntity);
        return dieSizeRepository.save(existingDieSizeEntity);
    }

    public void deleteDieSize(Long id) {
        if(!dieSizeRepository.existsById(id)) {
            throw new DieSizeNotFoundException("Die Size not found with id + " + id);
        }
        dieSizeRepository.deleteById(id);
    }
}
