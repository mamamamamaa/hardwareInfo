package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.TdpEntity;
import com.hardwareInfo.hardwareInfo.models.TdpDTO;
import com.hardwareInfo.hardwareInfo.services.TdpService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tdps")
public class TdpController {
    private final TdpService tdpService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<TdpDTO>> getAllTdps() {
        List<TdpEntity> tdpEntities = tdpService.getAllTdps();
        List<TdpDTO> tdpDTOs = tdpEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tdpDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TdpDTO> getTdpById(@PathVariable Long id) {
        TdpEntity tdpEntity = tdpService.getTdpById(id);
        return ResponseEntity.ok(convertToDTO(tdpEntity));
    }

    @PostMapping
    public ResponseEntity<TdpDTO> createTdp(@RequestBody TdpDTO tdpDTO) {
        TdpEntity createdTdpEntity = tdpService
                .createTdp(convertToEntity(tdpDTO));
        return ResponseEntity.ok(convertToDTO(createdTdpEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TdpDTO> updateTdp(@PathVariable Long id,
                                                        @RequestBody TdpDTO tdpDTO) {
        TdpEntity tdpEntity = convertToEntity(tdpDTO);
        TdpEntity updatedEntity = tdpService.updateTdp(id, tdpEntity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTdp(@PathVariable Long id) {
        tdpService.deleteTdp(id);
        return ResponseEntity.noContent().build();
    }

    private TdpEntity convertToEntity(TdpDTO tdpDTO) {
        return modelMapper.map(tdpDTO, TdpEntity.class);
    }

    private TdpDTO convertToDTO(TdpEntity tdpEntity) {
        return modelMapper.map(tdpEntity, TdpDTO.class);
    }
}
