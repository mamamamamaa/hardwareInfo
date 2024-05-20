package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.GraphicsCardEntity;
import com.hardwareInfo.hardwareInfo.exceptions.GraphicsCardNotFoundException;
import com.hardwareInfo.hardwareInfo.models.GraphicsCard;
import com.hardwareInfo.hardwareInfo.services.GraphicsCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/graphics-cards")
public class GraphicsCardController {

    private final GraphicsCardService graphicsCardService;

    @Autowired
    public GraphicsCardController(GraphicsCardService graphicsCardService) {
        this.graphicsCardService = graphicsCardService;
    }

    @GetMapping
    public ResponseEntity<List<GraphicsCard>> getAllGraphicsCards() {
        List<GraphicsCard> graphicsCards = graphicsCardService.getAllGraphicsCards();
        return ResponseEntity.ok(graphicsCards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GraphicsCard> getGraphicsCardById(@PathVariable Long id) {
        try {
            GraphicsCard graphicsCard = graphicsCardService.getGraphicsCardById(id);
            return ResponseEntity.ok(graphicsCard);
        } catch (GraphicsCardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GraphicsCard> createGraphicsCard(@RequestBody GraphicsCard graphicsCard) {
        GraphicsCard createdGraphicsCard = graphicsCardService.createGraphicsCard(graphicsCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGraphicsCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GraphicsCard> updateGraphicsCard(@PathVariable Long id, @RequestBody GraphicsCard graphicsCard) {
        try {
            GraphicsCard updatedGraphicsCard = graphicsCardService.updateGraphicsCard(id, graphicsCard);
            return ResponseEntity.ok(updatedGraphicsCard);
        } catch (GraphicsCardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGraphicsCard(@PathVariable Long id) {
        graphicsCardService.deleteGraphicsCard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<GraphicsCard>> searchGraphicsCardsByName(
            @RequestParam String modelName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(graphicsCardService.searchGraphicsCardsByModelName(modelName, PageRequest.of(page, size)));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<GraphicsCard>> filterGraphicsCards(
            @RequestParam Map<String, String> filterParams,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Map<String, Object> convertedFilterParams = filterParams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> {
                    String value = e.getValue();
                    try {
                        if (value.contains(".")) {
                            return Double.parseDouble(value);
                        } else {
                            return Long.parseLong(value);
                        }
                    } catch (NumberFormatException ex) {
                        return value;
                    }
                }));
        return ResponseEntity.ok(graphicsCardService.filterGraphicsCards(convertedFilterParams, PageRequest.of(page, size)));
    }

    @GetMapping("/sort")
    public ResponseEntity<Page<GraphicsCardEntity>> sortGraphicsCards(
            @RequestParam String orderBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GraphicsCardEntity> result = graphicsCardService.sortGraphicsCards(orderBy, pageable);
        return ResponseEntity.ok(result);
    }
}