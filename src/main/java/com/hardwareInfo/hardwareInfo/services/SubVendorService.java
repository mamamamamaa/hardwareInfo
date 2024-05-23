package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.SubVendorEntity;
import com.hardwareInfo.hardwareInfo.exceptions.SubVendorNotFoundException;
import com.hardwareInfo.hardwareInfo.repositories.SubVendorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubVendorService {
    private final SubVendorRepository subVendorRepository;
    private final ModelMapper modelMapper;

    public List<SubVendorEntity> getAllSubVendors() {
        return subVendorRepository.findAll();
    }

    public SubVendorEntity getSubVendorById(Long id) {
        return subVendorRepository.findById(id)
                .orElseThrow(() -> new SubVendorNotFoundException("Sub Vendor not found with id + " + id));
    }

    public SubVendorEntity createSubVendor(SubVendorEntity subVendorEntity) {
        return subVendorRepository.save(subVendorEntity);
    }

    public SubVendorEntity updateSubVendor(Long id, SubVendorEntity newSubVendorEntity) {
        SubVendorEntity existingSubVendorEntity = subVendorRepository.findById(id)
                .orElseThrow(() -> new SubVendorNotFoundException("Sub Vendor not found with id + " + id));

        modelMapper.map(newSubVendorEntity, existingSubVendorEntity);
        return subVendorRepository.save(existingSubVendorEntity);
    }

    public void deleteSubVendor(Long id) {
        if(!subVendorRepository.existsById(id)) {
            throw new SubVendorNotFoundException("Sub Vendor not found with id + " + id);
        }
        subVendorRepository.deleteById(id);
    }
}
