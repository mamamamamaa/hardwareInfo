package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.VramTypeEntity;
import com.hardwareInfo.hardwareInfo.services.VramTypeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vram_types")
public class VramTypeController {
    private final VramTypeService vramTypeService;

    @GetMapping
    public List<VramTypeEntity> getAllVramTypes(){
        return vramTypeService.getAllVramTypes();
    }

    @PostMapping("/{id}")
    public ResponseEntity<VramTypeEntity> updateVramType(@PathVariable Long id,
                                                         @RequestBody VramTypeEntity vramTypeEntity){
        Optional<VramTypeEntity> updatedVramType = vramTypeService.updateVramType(id, vramTypeEntity);

        return updatedVramType.map( ResponseEntity::ok ).orElseGet( ()-> ResponseEntity.notFound().build());
    }
}
