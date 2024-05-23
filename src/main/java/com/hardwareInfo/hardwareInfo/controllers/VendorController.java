package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.VendorEntity;
import com.hardwareInfo.hardwareInfo.models.VendorDTO;
import com.hardwareInfo.hardwareInfo.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vendors")
public class VendorController {
    private final VendorService vendorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        List<VendorEntity> vendorEntities = vendorService.getAllVendors();
        List<VendorDTO> vendorDTOs = vendorEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vendorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long id) {
        VendorEntity vendorEntity = vendorService.getVendorById(id);
        return ResponseEntity.ok(convertToDTO(vendorEntity));
    }

    @PostMapping
    public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
        VendorEntity createdVendorEntity = vendorService
                .createVendor(convertToEntity(vendorDTO));
        return ResponseEntity.ok(convertToDTO(createdVendorEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id,
                                                    @RequestBody VendorDTO vendorDTO) {
        VendorEntity vendorEntity = convertToEntity(vendorDTO);
        VendorEntity updatedEntity = vendorService.updateVendor(id, vendorEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

    private VendorEntity convertToEntity(VendorDTO vendorDTO) {
        return modelMapper.map(vendorDTO, VendorEntity.class);
    }

    private VendorDTO convertToDTO(VendorEntity vendorEntity) {
        return modelMapper.map(vendorEntity, VendorDTO.class);
    }
}
