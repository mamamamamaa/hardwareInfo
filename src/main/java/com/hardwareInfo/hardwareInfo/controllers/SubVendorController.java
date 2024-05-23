package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.SubVendorEntity;
import com.hardwareInfo.hardwareInfo.models.SubVendorDTO;
import com.hardwareInfo.hardwareInfo.services.SubVendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sub-vendors")
public class SubVendorController {
    private final SubVendorService subVendorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SubVendorDTO>> getAllSubVendors() {
        List<SubVendorEntity> subVendorEntities = subVendorService.getAllSubVendors();
        List<SubVendorDTO> subVendorDTOs = subVendorEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subVendorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubVendorDTO> getSubVendorById(@PathVariable Long id) {
        SubVendorEntity subVendorEntity = subVendorService.getSubVendorById(id);
        return ResponseEntity.ok(convertToDTO(subVendorEntity));
    }

    @PostMapping
    public ResponseEntity<SubVendorDTO> createSubVendor(@RequestBody SubVendorDTO subVendorDTO) {
        SubVendorEntity createdSubVendorEntity = subVendorService
                .createSubVendor(convertToEntity(subVendorDTO));
        return ResponseEntity.ok(convertToDTO(createdSubVendorEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubVendorDTO> updateSubVendor(@PathVariable Long id,
                                                              @RequestBody SubVendorDTO subVendorDTO) {
        SubVendorEntity subVendorEntity = convertToEntity(subVendorDTO);
        SubVendorEntity updatedEntity = subVendorService.updateSubVendor(id, subVendorEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubVendor(@PathVariable Long id) {
        subVendorService.deleteSubVendor(id);
        return ResponseEntity.noContent().build();
    }

    private SubVendorEntity convertToEntity(SubVendorDTO subVendorDTO) {
        return modelMapper.map(subVendorDTO, SubVendorEntity.class);
    }

    private SubVendorDTO convertToDTO(SubVendorEntity subVendorEntity) {
        return modelMapper.map(subVendorEntity, SubVendorDTO.class);
    }
}
