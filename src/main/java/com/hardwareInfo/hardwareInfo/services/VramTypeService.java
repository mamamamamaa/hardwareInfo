package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.VramTypeEntity;
import com.hardwareInfo.hardwareInfo.exceptions.VramTypeNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.VramTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VramTypeService {
    private final VramTypeRepository vramTypeRepository;
    private final ModelMapper modelMapper;

    public List<VramTypeEntity> getAllVramTypes() {
        return vramTypeRepository.findAll();
    }

    public VramTypeEntity getVramTypeById(Long id) {
        return vramTypeRepository.findById(id)
                .orElseThrow(() -> new VramTypeNotFoundException("Vram Type not found with id + " + id));
    }

    public VramTypeEntity createVramType(VramTypeEntity vramTypeEntity) {
        return vramTypeRepository.save(vramTypeEntity);
    }

    public VramTypeEntity updateVramType(Long id, VramTypeEntity newVramTypeEntity) {
        VramTypeEntity existingVramTypeEntity = vramTypeRepository.findById(id)
                .orElseThrow(() -> new VramTypeNotFoundException("Vram Type not found with id + " + id));

        modelMapper.map(newVramTypeEntity, existingVramTypeEntity);
        return vramTypeRepository.save(existingVramTypeEntity);
    }

    public void deleteVramType(Long id) {
        if(!vramTypeRepository.existsById(id)) {
            throw new VramTypeNotFoundException("Vram Type not found with id + " + id);
        }
        vramTypeRepository.deleteById(id);
    }
}
