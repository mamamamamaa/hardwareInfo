package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.VramTypeEntity;
import com.hardwareInfo.hardwareInfo.models.VramTypeDTO;
import com.hardwareInfo.hardwareInfo.services.VramTypeService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vram-types")
public class VramTypeController {
    private final VramTypeService vramTypeService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<VramTypeDTO>> getAllVramTypes() {
        List<VramTypeEntity> vramTypeEntities = vramTypeService.getAllVramTypes();
        List<VramTypeDTO> vramTypeDTOs = vramTypeEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vramTypeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VramTypeDTO> getVramTypeById(@PathVariable Long id) {
        VramTypeEntity vramTypeEntity = vramTypeService.getVramTypeById(id);
        return ResponseEntity.ok(convertToDTO(vramTypeEntity));
    }

    @PostMapping
    public ResponseEntity<VramTypeDTO> createVramType(@RequestBody VramTypeDTO vramTypeDTO) {
        VramTypeEntity createdVramTypeEntity = vramTypeService
                .createVramType(convertToEntity(vramTypeDTO));
        return ResponseEntity.ok(convertToDTO(createdVramTypeEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VramTypeDTO> updateVramType(@PathVariable Long id,
                                                   @RequestBody VramTypeDTO vramTypeDTO) {
        VramTypeEntity vramTypeEntity = convertToEntity(vramTypeDTO);
        VramTypeEntity updatedEntity = vramTypeService.updateVramType(id, vramTypeEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVramType(@PathVariable Long id) {
        vramTypeService.deleteVramType(id);
        return ResponseEntity.noContent().build();
    }

    private VramTypeEntity convertToEntity(VramTypeDTO vramTypeDTO) {
        return modelMapper.map(vramTypeDTO, VramTypeEntity.class);
    }

    private VramTypeDTO convertToDTO(VramTypeEntity vramTypeEntity) {
        return modelMapper.map(vramTypeEntity, VramTypeDTO.class);
    }
}
