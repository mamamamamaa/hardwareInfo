package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.DieSizeEntity;
import com.hardwareInfo.hardwareInfo.models.DieSizeDTO;
import com.hardwareInfo.hardwareInfo.services.DieSizeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/die-sizes")
public class DieSizeController {
    private final DieSizeService dieSizeService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DieSizeDTO>> getAllDieSizes() {
        List<DieSizeEntity> dieSizeEntities = dieSizeService.getAllDieSizes();
        List<DieSizeDTO> dieSizeDTOs = dieSizeEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dieSizeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DieSizeDTO> getDieSizeById(@PathVariable Long id) {
        DieSizeEntity dieSizeEntity = dieSizeService.getDieSizeById(id);
        return ResponseEntity.ok(convertToDTO(dieSizeEntity));
    }

    @PostMapping
    public ResponseEntity<DieSizeDTO> createDieSize(@RequestBody DieSizeDTO dieSizeDTO) {
        DieSizeEntity createdDieSizeEntity = dieSizeService
                .createDieSize(convertToEntity(dieSizeDTO));
        return ResponseEntity.ok(convertToDTO(createdDieSizeEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DieSizeDTO> updateDieSize(@PathVariable Long id,
                                                                    @RequestBody DieSizeDTO dieSizeDTO) {
        DieSizeEntity dieSizeEntity = convertToEntity(dieSizeDTO);
        DieSizeEntity updatedEntity = dieSizeService.updateDieSize(id, dieSizeEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDieSize(@PathVariable Long id) {
        dieSizeService.deleteDieSize(id);
        return ResponseEntity.noContent().build();
    }

    private DieSizeEntity convertToEntity(DieSizeDTO dieSizeDTO) {
        return modelMapper.map(dieSizeDTO, DieSizeEntity.class);
    }

    private DieSizeDTO convertToDTO(DieSizeEntity dieSizeEntity) {
        return modelMapper.map(dieSizeEntity, DieSizeDTO.class);
    }
}
