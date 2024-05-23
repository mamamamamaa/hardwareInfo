package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.GraphicsCardEntity;
import com.hardwareInfo.hardwareInfo.models.GraphicsCardDTO;
import com.hardwareInfo.hardwareInfo.services.GraphicsCardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/graphics-cards")
public class GraphicsCardController {
    private final ModelMapper modelMapper;
    private final GraphicsCardService graphicsCardService;

    @GetMapping
    public ResponseEntity<Page<GraphicsCardDTO>> getGraphicsCards(
            @RequestParam(required = false) String gpuNameFilter,
            @RequestParam(required = false) Integer gpuVendorFilter,
            @RequestParam(required = false) Integer tdpFilter,
            @RequestParam(required = false) Integer vramTypeFilter,
            @RequestParam(required = false) Integer dieSizeFilter,
            @RequestParam(required = false) Integer architectureFilter,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<GraphicsCardEntity> graphicsCards = graphicsCardService
                .getGraphicsCards(
                        gpuNameFilter,
                        gpuVendorFilter,
                        tdpFilter,
                        vramTypeFilter,
                        dieSizeFilter,
                        architectureFilter,
                        pageable
                );
        return ResponseEntity.ok(graphicsCards.map(this::convertToDTO));
    }

    private GraphicsCardDTO convertToDTO(GraphicsCardEntity graphicsCardEntity) {
        return modelMapper.map(graphicsCardEntity, GraphicsCardDTO.class);
    }

    private GraphicsCardEntity convertToEntity(GraphicsCardDTO graphicsCardDTO) {
        return modelMapper.map(graphicsCardDTO, GraphicsCardEntity.class);
    }
}
