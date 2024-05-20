package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.VramTypeEntity;
import com.hardwareInfo.hardwareInfo.repositories.VramTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VramTypeService {
    private final VramTypeRepository vramTypeRepository;

    public VramTypeEntity createVramType(VramTypeEntity vramTypeEntity) {

        return vramTypeRepository.save(vramTypeEntity);
    }
    public List<VramTypeEntity> getAllVramTypes(){

        return  vramTypeRepository.findAll();
    }

    public Optional<VramTypeEntity> getVramTypeByID(Long id){

        return vramTypeRepository.findById(id);
    }
    @PutMapping
    public Optional<VramTypeEntity> updateVramType(Long id, VramTypeEntity newVramType){
        return vramTypeRepository.findById(id).map( vramType -> {
            vramType.setVramType(newVramType.getVramType());
            return vramTypeRepository.save(vramType);
        });

    }


    @DeleteMapping
    public void deleteVramType(Long id){
         vramTypeRepository.deleteById(id);
    }

}
