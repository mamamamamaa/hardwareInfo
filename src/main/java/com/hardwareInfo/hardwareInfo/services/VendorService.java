package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.VendorEntity;

import com.hardwareInfo.hardwareInfo.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VendorService {
    private final VendorRepository vendorRepository;

    public VendorEntity createVendor(VendorEntity vendorEntity){

        return vendorRepository.save(vendorEntity);
    }
    public Optional<VendorEntity> getVendorByID(Long id){
        return vendorRepository.findById(id);
    }

    public List<VendorEntity> getAllVendors(){
        return vendorRepository.findAll();
    }
    @PutMapping
    public Optional<VendorEntity> updateVendor(Long id, VendorEntity newVendor){

        return vendorRepository.findById(id).map( vendor-> {
            vendor.setGpuVendor(newVendor.getGpuVendor());
            vendor.setGpuModels(newVendor.getGpuModels());
            return vendor;
        });
    }
    @DeleteMapping
    public void deleteVendor(Long id){

        vendorRepository.deleteById(id);
    }

}
