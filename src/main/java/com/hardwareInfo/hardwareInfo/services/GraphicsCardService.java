package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.GraphicsCardEntity;
import com.hardwareInfo.hardwareInfo.exceptions.GraphicsCardNotFoundException;
import com.hardwareInfo.hardwareInfo.models.GraphicsCard;
import com.hardwareInfo.hardwareInfo.repositories.GraphicsCardRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GraphicsCardService {
    private final GraphicsCardRepository graphicsCardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GraphicsCardService(GraphicsCardRepository graphicsCardRepository, ModelMapper modelMapper) {
        this.graphicsCardRepository = graphicsCardRepository;
        this.modelMapper = modelMapper;
    }

    public List<GraphicsCard> getAllGraphicsCards() {
        return graphicsCardRepository.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public GraphicsCard getGraphicsCardById(Long id) {
        GraphicsCardEntity graphicsCardEntity = graphicsCardRepository.findById(id)
                .orElseThrow(() -> new GraphicsCardNotFoundException("Graphics card not found with id: " + id));
        return convertToModel(graphicsCardEntity);
    }

    public GraphicsCard createGraphicsCard(GraphicsCard graphicsCard) {
        GraphicsCardEntity graphicsCardEntity = convertToEntity(graphicsCard);
        return convertToModel(graphicsCardRepository.save(graphicsCardEntity));
    }

    public GraphicsCard updateGraphicsCard(Long id, GraphicsCard graphicsCard) {
        GraphicsCardEntity existingGraphicsCard = graphicsCardRepository.findById(id)
                .orElseThrow(() -> new GraphicsCardNotFoundException("Graphics card not found with id: " + id));

        modelMapper.map(graphicsCard, existingGraphicsCard);
        return convertToModel(graphicsCardRepository.save(existingGraphicsCard));
    }

    public void deleteGraphicsCard(Long id) {
        graphicsCardRepository.deleteById(id);
    }

    public Page<GraphicsCard> searchGraphicsCardsByModelName(String modelName, Pageable pageable) {
        return graphicsCardRepository.searchGraphicsCardsByModelName(modelName, pageable).map(this::convertToModel);
    }

    public Page<GraphicsCard> filterGraphicsCards(Map<String, Object> filterParams, Pageable pageable) {
        return graphicsCardRepository.filterGraphicsCards(
                (Long) filterParams.get("gpuModelId"),
                (Long) filterParams.get("vramTypeId"),
                (Integer) filterParams.get("vramCapacityValue"),
                (Double) filterParams.get("vramFrequency"),
                (Double) filterParams.get("gpuFrequency"),
                (Integer) filterParams.get("tdpValue"),
                pageable
        ).map(this::convertToModel);
    }

    public Page<GraphicsCardEntity> sortGraphicsCards(String orderBy, Pageable pageable) {
        return graphicsCardRepository.sortGraphicsCards(orderBy, pageable);
    }

    private GraphicsCard convertToModel(GraphicsCardEntity entity) {
        return modelMapper.map(entity, GraphicsCard.class);
    }

    private GraphicsCardEntity convertToEntity(GraphicsCard graphicsCard) {
        return modelMapper.map(graphicsCard, GraphicsCardEntity.class);
    }
}
