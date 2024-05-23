package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.VramCapacityEntity;
import com.hardwareInfo.hardwareInfo.models.VramCapacityDTO;
import com.hardwareInfo.hardwareInfo.services.VramCapacityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vram-capacities")
public class VramCapacityController {
    private final VramCapacityService vramCapacityService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<VramCapacityDTO>> getAllVramCapacities() {
        List<VramCapacityEntity> vramCapacityEntities = vramCapacityService.getAllVramCapacities();
        List<VramCapacityDTO> vramCapacityDTOs = vramCapacityEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vramCapacityDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VramCapacityDTO> getVramCapacityById(@PathVariable Long id) {
        VramCapacityEntity vramCapacityEntity = vramCapacityService.getVramCapacityById(id);
        return ResponseEntity.ok(convertToDTO(vramCapacityEntity));
    }

    @PostMapping
    public ResponseEntity<VramCapacityDTO> createVramCapacity(@RequestBody VramCapacityDTO vramCapacityDTO) {
        VramCapacityEntity createdVramCapacityEntity = vramCapacityService
                .createVramCapacity(convertToEntity(vramCapacityDTO));
        return ResponseEntity.ok(convertToDTO(createdVramCapacityEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VramCapacityDTO> updateVramCapacity(@PathVariable Long id,
                                                      @RequestBody VramCapacityDTO vramCapacityDTO) {
        VramCapacityEntity vramCapacityEntity = convertToEntity(vramCapacityDTO);
        VramCapacityEntity updatedEntity = vramCapacityService.updateVramCapacity(id, vramCapacityEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVramCapacity(@PathVariable Long id) {
        vramCapacityService.deleteVramCapacity(id);
        return ResponseEntity.noContent().build();
    }

    private VramCapacityEntity convertToEntity(VramCapacityDTO vramCapacityDTO) {
        return modelMapper.map(vramCapacityDTO, VramCapacityEntity.class);
    }

    private VramCapacityDTO convertToDTO(VramCapacityEntity vramCapacityEntity) {
        return modelMapper.map(vramCapacityEntity, VramCapacityDTO.class);
    }
}
