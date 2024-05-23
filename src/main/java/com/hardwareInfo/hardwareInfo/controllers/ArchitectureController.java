package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.ArchitectureEntity;
import com.hardwareInfo.hardwareInfo.models.ArchitectureDTO;
import com.hardwareInfo.hardwareInfo.services.ArchitectureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/architectures")
public class ArchitectureController {
    private final ArchitectureService architectureService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ArchitectureDTO>> getAllArchitectures() {
        List<ArchitectureEntity> architectureEntities = architectureService.getAllArchitectures();
        List<ArchitectureDTO> architectureDTOs = architectureEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(architectureDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchitectureDTO> getArchitectureById(@PathVariable Long id) {
        ArchitectureEntity architectureEntity = architectureService.getArchitectureById(id);
        return ResponseEntity.ok(convertToDTO(architectureEntity));
    }

    @PostMapping
    public ResponseEntity<ArchitectureDTO> createArchitectureEntity(@RequestBody ArchitectureDTO architectureDTO) {
        ArchitectureEntity createdArchitectureEntity = architectureService
                .createArchitectureEntity(convertToEntity(architectureDTO));
        return ResponseEntity.ok(convertToDTO(createdArchitectureEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchitectureDTO> updateArchitecture(@PathVariable Long id,
                                                            @RequestBody ArchitectureDTO architectureDTO) {
        ArchitectureEntity architectureEntity = convertToEntity(architectureDTO);
        ArchitectureEntity updatedEntity = architectureService.updateArchitecture(id, architectureEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArchitecture(@PathVariable Long id) {
        architectureService.deleteArchitecture(id);
        return ResponseEntity.noContent().build();
    }

    private ArchitectureEntity convertToEntity(ArchitectureDTO architectureDTO) {
        return modelMapper.map(architectureDTO, ArchitectureEntity.class);
    }

    private ArchitectureDTO convertToDTO(ArchitectureEntity architectureEntity) {
        return modelMapper.map(architectureEntity, ArchitectureDTO.class);
    }
}
