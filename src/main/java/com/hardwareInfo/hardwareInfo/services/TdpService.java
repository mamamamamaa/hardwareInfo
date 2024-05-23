package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.TdpEntity;
import com.hardwareInfo.hardwareInfo.exceptions.TdpNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.TdpRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TdpService {
    private final TdpRepository tdpRepository;
    private final ModelMapper modelMapper;

    public List<TdpEntity> getAllTdps() {
        return tdpRepository.findAll();
    }

    public TdpEntity getTdpById(Long id) {
        return tdpRepository.findById(id)
                .orElseThrow(() -> new TdpNotFoundException("Tdp not found with id + " + id));
    }

    public TdpEntity createTdp(TdpEntity tdpEntity) {
        return tdpRepository.save(tdpEntity);
    }

    public TdpEntity updateTdp(Long id, TdpEntity newTdpEntity) {
        TdpEntity existingTdpEntity = tdpRepository.findById(id)
                .orElseThrow(() -> new TdpNotFoundException("Tdp not found with id + " + id));

        modelMapper.map(newTdpEntity, existingTdpEntity);
        return tdpRepository.save(existingTdpEntity);
    }

    public void deleteTdp(Long id) {
        if(!tdpRepository.existsById(id)) {
            throw new TdpNotFoundException("Tdp not found with id + " + id);
        }
        tdpRepository.deleteById(id);
    }
}
