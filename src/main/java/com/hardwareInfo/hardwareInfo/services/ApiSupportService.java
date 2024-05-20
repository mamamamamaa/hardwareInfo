package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.ApiSupportEntity;
import com.hardwareInfo.hardwareInfo.repositories.ApiSupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApiSupportService {
    private final ApiSupportRepository apiSupportRepository;

    public Optional<ApiSupportEntity> getApiSupportEntityById(Long id){

        return apiSupportRepository.findById(id);
    }
@PutMapping
    public Optional<ApiSupportEntity> updateApiSupportService(ApiSupportEntity newApiSupportEntity, Long id){

        return apiSupportRepository.findById(id).map( apiSupport-> {
            apiSupport.setCuda(newApiSupportEntity.getCuda());
            apiSupport.setVulcan(newApiSupportEntity.getVulcan());
            apiSupport.setDirectX(newApiSupportEntity.getDirectX());
            apiSupport.setGraphicsCard(newApiSupportEntity.getGraphicsCard());
            return apiSupport;
        });
    }
@DeleteMapping
    public void deleteApiSupportService(Long id){

        apiSupportRepository.deleteById(id);
    }

}
