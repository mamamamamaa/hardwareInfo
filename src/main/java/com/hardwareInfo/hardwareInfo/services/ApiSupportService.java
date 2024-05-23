package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.ApiSupportEntity;
import com.hardwareInfo.hardwareInfo.exceptions.ApiSupportsNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.ApiSupportRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApiSupportService {
    private final ApiSupportRepository apiSupportRepository;
    private final ModelMapper modelMapper;

    public List<ApiSupportEntity> getAllApiSupports() {
        return apiSupportRepository.findAll();
    }

    public ApiSupportEntity getApiSupportById(Long id) {
        return apiSupportRepository.findById(id)
                .orElseThrow(() -> new ApiSupportsNotFoundException("API Support not found with id + " + id));
    }

    public ApiSupportEntity createApiSupport(ApiSupportEntity apiSupport) {
        return apiSupportRepository.save(apiSupport);
    }

    public ApiSupportEntity updateApiSupport(Long id, ApiSupportEntity newApiSupport) {
        ApiSupportEntity existingApiSupport = apiSupportRepository.findById(id)
                .orElseThrow(() -> new ApiSupportsNotFoundException("API Support not found with id + " + id));

        modelMapper.map(newApiSupport, existingApiSupport);
        return apiSupportRepository.save(existingApiSupport);
    }

    public void deleteApiSupport(Long id) {
        if(!apiSupportRepository.existsById(id)) {
            throw new ApiSupportsNotFoundException("API Support not found with id + " + id);
        }
        apiSupportRepository.deleteById(id);
    }
}
