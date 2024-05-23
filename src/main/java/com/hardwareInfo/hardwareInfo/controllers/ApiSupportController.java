package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.ApiSupportEntity;
import com.hardwareInfo.hardwareInfo.models.ApiSupportDTO;
import com.hardwareInfo.hardwareInfo.services.ApiSupportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-supports")
public class ApiSupportController {
    private final ApiSupportService apiSupportService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ApiSupportDTO>> getAllApiSupports() {
        List<ApiSupportEntity> apiSupportEntities = apiSupportService.getAllApiSupports();
        List<ApiSupportDTO> apiSupportDTOs = apiSupportEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(apiSupportDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiSupportDTO> getApiSupportById(@PathVariable Long id) {
        ApiSupportEntity apiSupportEntity = apiSupportService.getApiSupportById(id);
        return ResponseEntity.ok(convertToDTO(apiSupportEntity));
    }

    @PostMapping
    public ResponseEntity<ApiSupportDTO> createApiSupport(@RequestBody ApiSupportDTO apiSupportDTO) {
        ApiSupportEntity createdApiSupportEntity = apiSupportService.createApiSupport(convertToEntity(apiSupportDTO));
        return ResponseEntity.ok(convertToDTO(createdApiSupportEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiSupportDTO> updateApiSupport(@PathVariable Long id, @RequestBody ApiSupportDTO apiSupportDTO) {
        ApiSupportEntity apiSupportEntity = convertToEntity(apiSupportDTO);
        ApiSupportEntity updatedEntity = apiSupportService.updateApiSupport(id, apiSupportEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApiSupport(@PathVariable Long id) {
        apiSupportService.deleteApiSupport(id);
        return ResponseEntity.noContent().build();
    }

    private ApiSupportEntity convertToEntity(ApiSupportDTO apiSupportDTO) {
        return modelMapper.map(apiSupportDTO, ApiSupportEntity.class);
    }

    private ApiSupportDTO convertToDTO(ApiSupportEntity apiSupportEntity) {
        return modelMapper.map(apiSupportEntity, ApiSupportDTO.class);
    }
}
