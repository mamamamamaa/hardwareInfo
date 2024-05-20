package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.TdpEntity;
import com.hardwareInfo.hardwareInfo.repositories.TdpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TdpService {
    private final TdpRepository tdpRepository;

    public List<TdpEntity> getAllTdps(){
        return tdpRepository.findAll();
    }

    public Optional<TdpEntity> getTdpEntityById(Long id){
        return tdpRepository.findById(id);
    }

    @PutMapping
    public Optional<TdpEntity> updateTdp(Long id, TdpEntity newTdp){

        return tdpRepository.findById(id).map( tdp-> {
            tdp.setTdpValue(newTdp.getTdpValue());
            tdp.setGraphicsCards(newTdp.getGraphicsCards());
            return tdp;
        });

    }
    @DeleteMapping
    public void deleteTdp(Long id){
        tdpRepository.deleteById(id);
    }
}
