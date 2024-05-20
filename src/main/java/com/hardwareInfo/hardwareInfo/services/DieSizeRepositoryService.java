package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.DieSizeEntity;
import com.hardwareInfo.hardwareInfo.repositories.DieSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DieSizeRepositoryService {
    private final DieSizeRepository dieSizeRepository;


    public DieSizeEntity createDieSize(DieSizeEntity dieSize){
       return dieSizeRepository.save(dieSize);
    }
    @PutMapping
    public Optional<DieSizeEntity> updateDieSize(DieSizeEntity newDieSize, Long id){

        return dieSizeRepository.findById(id).map( dieSize-> {
            dieSize.setDieSize(newDieSize.getDieSize());

            return dieSize;
        });
    }

    @DeleteMapping
    public void deleteDieSize(Long id){

        dieSizeRepository.deleteById(id);
    }

    public List<DieSizeEntity> getDieSizes(){
        return dieSizeRepository.findAll();
    }

    public Optional<DieSizeEntity> getDieSizeById(Long id){

         return dieSizeRepository.findById(id);
    }

}
