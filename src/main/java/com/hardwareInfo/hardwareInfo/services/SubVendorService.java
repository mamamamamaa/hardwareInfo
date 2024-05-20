package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.SubVendorEntity;
import com.hardwareInfo.hardwareInfo.entities.VendorEntity;
import com.hardwareInfo.hardwareInfo.repositories.SubVendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubVendorService {
    private final SubVendorRepository subVendorRepository;

    public SubVendorEntity createVendor(SubVendorEntity subVendorEntity){

        return subVendorRepository.save(subVendorEntity);
    }
    public Optional<SubVendorEntity> getVendorByID(Long id){
        return subVendorRepository.findById(id);
    }

    public List<SubVendorEntity> getAllVendors(){
        return subVendorRepository.findAll();
    }
    @PutMapping
    public Optional<SubVendorEntity> updateVendor(Long id, SubVendorEntity newVendor){

        return subVendorRepository.findById(id).map( subVendor-> {
            subVendor.setGpuVendor(newVendor.getGpuVendor());
            subVendor.setGraphicsCards(newVendor.getGraphicsCards());
            return subVendor;
        });
    }
    @DeleteMapping
    public void deleteVendor(Long id){

        subVendorRepository.deleteById(id);
    }

}
