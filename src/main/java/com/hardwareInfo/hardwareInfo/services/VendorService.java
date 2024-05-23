package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.VendorEntity;

import com.hardwareInfo.hardwareInfo.exceptions.VendorNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VendorService {
    private final VendorRepository vendorRepository;
    private final ModelMapper modelMapper;

    public List<VendorEntity> getAllVendors() {
        return vendorRepository.findAll();
    }

    public VendorEntity getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id + " + id));
    }

    public VendorEntity createVendor(VendorEntity vendorEntity) {
        return vendorRepository.save(vendorEntity);
    }

    public VendorEntity updateVendor(Long id, VendorEntity newVendorEntity) {
        VendorEntity existingVendorEntity = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id + " + id));

        modelMapper.map(newVendorEntity, existingVendorEntity);
        return vendorRepository.save(existingVendorEntity);
    }

    public void deleteVendor(Long id) {
        if(!vendorRepository.existsById(id)) {
            throw new VendorNotFoundException("Vendor not found with id + " + id);
        }
        vendorRepository.deleteById(id);
    }
}
