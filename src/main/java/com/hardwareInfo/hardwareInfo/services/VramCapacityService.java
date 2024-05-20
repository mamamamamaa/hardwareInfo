package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.VramCapacityEntity;
import com.hardwareInfo.hardwareInfo.repositories.VramCapacityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VramCapacityService {
    private final VramCapacityRepository vramCapacityRepository;


    public VramCapacityEntity createVramCapacity(VramCapacityEntity vramCapacityEntity) {

        return vramCapacityRepository.save(vramCapacityEntity);
    }
    public List<VramCapacityEntity> getAllVramCapacityTypes(){

        return  vramCapacityRepository.findAll();
    }

    public Optional<VramCapacityEntity> getVramCapacityByID(Long id){

        return vramCapacityRepository.findById(id);
    }
    @PutMapping
    public Optional<VramCapacityEntity> updateVramCapacity(Long id, VramCapacityEntity newVramCapacity){
        return vramCapacityRepository.findById(id).map( vramCapacity -> {
            vramCapacity.setVramCapacity(newVramCapacity.getVramCapacity());
            return vramCapacityRepository.save(vramCapacity);
        });

    }


    @DeleteMapping
    public void deleteVramCapacity(Long id){
        vramCapacityRepository.deleteById(id);
    }
}
