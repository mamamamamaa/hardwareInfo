package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.VramCapacityEntity;
import com.hardwareInfo.hardwareInfo.exceptions.VramCapacityNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.VramCapacityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VramCapacityService {
    private final VramCapacityRepository vramCapacityRepository;
    private final ModelMapper modelMapper;

    public List<VramCapacityEntity> getAllVramCapacities() {
        return vramCapacityRepository.findAll();
    }

    public VramCapacityEntity getVramCapacityById(Long id) {
        return vramCapacityRepository.findById(id)
                .orElseThrow(() -> new VramCapacityNotFoundException("Vram Capacity not found with id + " + id));
    }

    public VramCapacityEntity createVramCapacity(VramCapacityEntity vramCapacityEntity) {
        return vramCapacityRepository.save(vramCapacityEntity);
    }

    public VramCapacityEntity updateVramCapacity(Long id, VramCapacityEntity newVramCapacityEntity) {
        VramCapacityEntity existingVramCapacityEntity = vramCapacityRepository.findById(id)
                .orElseThrow(() -> new VramCapacityNotFoundException("Vram Capacity not found with id + " + id));

        modelMapper.map(newVramCapacityEntity, existingVramCapacityEntity);
        return vramCapacityRepository.save(existingVramCapacityEntity);
    }

    public void deleteVramCapacity(Long id) {
        if(!vramCapacityRepository.existsById(id)) {
            throw new VramCapacityNotFoundException("Vram Capacity not found with id + " + id);
        }
        vramCapacityRepository.deleteById(id);
    }
}
